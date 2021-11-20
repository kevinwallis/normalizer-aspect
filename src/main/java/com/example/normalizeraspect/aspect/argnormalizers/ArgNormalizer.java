package com.example.normalizeraspect.aspect.argnormalizers;

import java.lang.reflect.Type;

public interface ArgNormalizer<T> {

    T normalize(T obj);

    Type getNormalizedType();

}
