package com.example.normalizeraspect.aspect;

import java.lang.reflect.Type;

public interface Normalizer<T> {

    T normalize(T obj);

    Type getNormalizedType();

}
