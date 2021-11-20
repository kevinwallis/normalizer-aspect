package com.example.normalizeraspect.aspect.argnormalizers;

public interface ArgNormalizer<T> {

    T normalize(T obj);

    boolean canNormalize(T obj);

}
