package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.PendingStateChange;
import com.onemanparty.rxmvpandroid.core.utils.lambda.SerializableTransactionAction2;

import java.io.Serializable;

/**
 * Implementation of pending view-based navigation with ability to be serialized with param
 * Param should also be serializable
 */
public class SerializablePendingStateChangeWithDataImpl<V, D extends Serializable> implements PendingStateChange<V>, Serializable{

    private D data;
    private SerializableTransactionAction2<V, D> op;

    public SerializablePendingStateChangeWithDataImpl() {
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setOperation(SerializableTransactionAction2<V, D> op) {
        this.op = op;
    }

    @Override
    public void apply(V view) {
        op.invoke(view, data);
    }

}
