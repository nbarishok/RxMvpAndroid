package com.onemanparty.rxmvpandroid.core.utils.lambda;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Serializable lambda with 2 params, one is parcelable
 */
public interface ParcelableTransactionAction2<V, D extends Parcelable> extends Action2<V, D>, Serializable {

}
