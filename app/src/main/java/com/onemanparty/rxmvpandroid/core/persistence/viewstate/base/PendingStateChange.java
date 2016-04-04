package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

/**
 * Pending view-based navigation
 */
public interface PendingStateChange<V> {
    void apply(V view);
}
