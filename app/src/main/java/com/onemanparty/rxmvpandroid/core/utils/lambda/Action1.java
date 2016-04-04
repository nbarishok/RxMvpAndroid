package com.onemanparty.rxmvpandroid.core.utils.lambda;


import java.io.Serializable;

/**
 * Serializable lambda with 1 param
 */
public interface Action1<V> extends Serializable {
    void apply(V v);
}
