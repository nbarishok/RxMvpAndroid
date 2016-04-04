package com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.NavigationViewState;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.ViewNavigationResolver;

import java.io.Serializable;

/**
 * Implementation of helper class to abstract away details of view-based navigation with Serializable data
 */
public class SerializableViewNavigationResolver<V> extends ViewNavigationResolver<V, Serializable> {

    public SerializableViewNavigationResolver(NavigationViewState<V, Serializable> viewState) {
        super(viewState);
    }
}
