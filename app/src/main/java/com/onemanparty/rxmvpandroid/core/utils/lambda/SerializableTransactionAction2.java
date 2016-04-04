package com.onemanparty.rxmvpandroid.core.utils.lambda;

import java.io.Serializable;

/**
 * Serializable lambda with 2 params, one is serializable
 */
public interface SerializableTransactionAction2<V, D extends Serializable> extends Action2<V, D>, Serializable {

}