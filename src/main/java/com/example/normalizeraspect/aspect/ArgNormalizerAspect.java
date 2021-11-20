package com.example.normalizeraspect.aspect;

import com.example.normalizeraspect.aspect.argnormalizers.ArgNormalizer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Aspect
public class ArgNormalizerAspect {

    private final List<ArgNormalizer<?>> normalizers;

    @Pointcut("@annotation(com.example.normalizeraspect.aspect.NormalizeArgs)")
    public void isNormalizeArgAnnotated() {
    }

    @Around("isNormalizeArgAnnotated()")
    public Object normalizeArgs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("Before normalize: " + Arrays.toString(args));

        List<Object> newArgsList = new ArrayList<>();

        for (Object arg : args) {

            Object newArg = this.normalizers.stream()
                    .filter(normalizer -> canNormalize(arg, normalizer))
                    .reduce(arg, ArgNormalizerAspect::callArgNormalizer, ArgNormalizerAspect::combiner);

            newArgsList.add(newArg);
        }

        Object[] newArgs = newArgsList.toArray();
        System.out.println("After normalize: " + Arrays.toString(newArgs));

        return joinPoint.proceed(newArgs);
    }

    @SuppressWarnings("unchecked")
    public static <T> Object callArgNormalizer(Object obj, ArgNormalizer<T> normalizer) {
        return normalizer.normalize((T) obj);
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean canNormalize(Object obj, ArgNormalizer<T> normalizer) {
        return getArgNormalizerType(normalizer) == obj.getClass()
                && normalizer.canNormalize((T)obj);
    }

    public static Type getArgNormalizerType(ArgNormalizer<?> normalizer) {
        return ((ParameterizedType)normalizer.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    public static Object combiner(Object obj1, Object obj2) {
        return null;
    }

}
