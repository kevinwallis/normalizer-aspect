package com.example.normalizeraspect.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Aspect
public class NormalizerAspect {

    private final List<Normalizer<?>> normalizers;

    @Pointcut("@annotation(Normalize)")
    public void isNormalizeAnnotated() {
    }

    @Around("isNormalizeAnnotated()")
    public Object normalize(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("Before normalize: " + Arrays.toString(args));

        List<Object> newArgsList = new ArrayList<>();

        for (Object arg : args) {

            Object newArg = this.normalizers.stream()
                    .filter(normalizer -> normalizer.getNormalizedType() == arg.getClass())
                    .reduce(arg, NormalizerAspect::callNormalizer, NormalizerAspect::combiner);

            newArgsList.add(newArg);
        }

        Object[] newArgs = newArgsList.toArray();
        System.out.println("After normalize: " + Arrays.toString(newArgs));

        return joinPoint.proceed(newArgs);
    }

    public static <T> Object callNormalizer(Object obj, Normalizer<T> normalizer) {
        return normalizer.normalize((T) obj);
    }

    public static Object combiner(Object obj1, Object obj2) {
        return null;
    }

}
