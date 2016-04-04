package com.onemanparty.rxmvpandroid.core.utils.lambda;

import java.io.Serializable;

/**
 * Serializable lambda with 2 params
 */
public interface Action2<V, D> extends Serializable {
    void invoke(V view, D data);
}
