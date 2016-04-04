package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.PendingStateChange;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;

import java.io.Serializable;

/**
 * Implementation of pending view-based navigation with ability to put be serialized
 */
public class SerializablePendingStateChangeImpl<V> implements PendingStateChange<V>, Serializable {

    private Action1<V> op;

    public void setOperation(Action1<V> op) {
        this.op = op;
    }

    @Override
    public void apply(V view) {
        op.apply(view);
    }
}
