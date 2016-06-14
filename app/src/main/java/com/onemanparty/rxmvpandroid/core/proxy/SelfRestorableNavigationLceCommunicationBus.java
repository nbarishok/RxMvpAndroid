package com.onemanparty.rxmvpandroid.core.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.LceViewState;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.NavigationViewState;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.SelfRestorableViewState;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.SerializableViewNavigationResolver;
import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

import java.io.Serializable;

/**
 * Base class for all communication buses for {@link LceView} with view-based navigation(i.e. showing dialog, transition to another Activity etc.)
 * And also it inherits the ability to save / restore ViewState(but with pending view state navigations this time) automatically
 */
public class SelfRestorableNavigationLceCommunicationBus<D extends EmptyViewModel,
                                                        E extends Enum<E>,
                                                        V extends LceView<D, E>,
                                                        P extends Presenter<V>,
                                                        VS extends LceViewState<D, E, V> & SelfRestorableViewState & NavigationViewState<V, Serializable>>
        extends SelfRestorableLceCommunicationBus<D, E, V, P ,VS> {

    private SerializableViewNavigationResolver<V> navigationResolver;

    public SelfRestorableNavigationLceCommunicationBus(P presenter, VS viewState) {
        super(presenter, viewState);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        navigationResolver = new SerializableViewNavigationResolver<>(viewState);
        super.onCreate(arguments, savedInstanceState);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        navigationResolver.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        navigationResolver.detachView();
    }

    public SerializableViewNavigationResolver<V> getNavigationResolver() {
        return navigationResolver;
    }
}
