package com.onemanparty.rxmvpandroid.core.view;

/**
 * Interface all view states should implement
 */
public interface ViewState<V> {

    /**
     * Restore view with it's actual state
     * @param view view which state to restore
     */
    void apply(V view);

}
