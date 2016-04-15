package com.onemanparty.rxmvpandroid.core.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.core.view.View;

/**
 * Base class for all communication buses
 * Encapsulates base logic of working with {@link Presenter}: attach / detach, destroy, save
 */
public abstract class CommunicationBus<V extends View, P extends Presenter<V>>
        implements Presenter<V>, View {

    P presenter;
    V view;

    public CommunicationBus(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        presenter.attachView((V) this);
        presenter.onCreate(arguments, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        presenter.onSaveInstanceState(bundle);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        presenter.onDestroy();
    }

    protected P getPresenter() {
        return presenter;
    }
}
