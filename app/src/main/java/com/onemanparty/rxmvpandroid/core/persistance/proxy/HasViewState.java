package com.onemanparty.rxmvpandroid.core.persistance.proxy;

/**
 * Interface for communication between view and presenter
 */
public interface HasViewState<S> {
    /**
     * get view state instance for view
     * @return view state instance
     */
    S createViewState();
}
