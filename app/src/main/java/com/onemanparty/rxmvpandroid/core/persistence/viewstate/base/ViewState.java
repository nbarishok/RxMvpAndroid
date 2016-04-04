package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

/**
 * Concept used for tracking state of the view
 */
public interface ViewState<V> {

    void apply(V view);

}
