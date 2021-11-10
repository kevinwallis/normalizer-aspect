package com.example.normalizeraspect.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

            System.out.println(newArg);

            newArgsList.add(newArg);
        }

        Object[] newArgs = newArgsList.toArray();
        System.out.println("After normalize: " + Arrays.toString(newArgs));

        return joinPoint.proceed(newArgs);
    }

    public static Object callNormalizer(Object obj, Normalizer<?> normalizer) {
        try {
            Method normalize = Normalizer.class.getMethod("normalize", Object.class);
            return normalize.invoke(normalizer, obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return obj;
        }
    }

    public static Object combiner(Object obj1, Object obj2) {
        return null;
    }

}
