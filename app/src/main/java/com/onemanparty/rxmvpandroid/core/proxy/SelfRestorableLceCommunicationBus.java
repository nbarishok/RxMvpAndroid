package com.onemanparty.rxmvpandroid.core.proxy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.LceViewState;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.SelfRestorableViewState;
import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.core.view.LceView;

/**
 * Base class for all communication buses for {@link LceView} with ability to save / restore ViewState automatically
 */
public abstract class SelfRestorableLceCommunicationBus<D, E extends Enum<E>, V extends LceView<D, E>, P extends Presenter<V>, VS extends LceViewState<D, E, V> & SelfRestorableViewState>
        extends LceCommunicationBus<D, E, V, P ,VS> {

    public SelfRestorableLceCommunicationBus(P presenter, VS viewState) {
        super(presenter, viewState);
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            viewState.restore();
        } else {
            viewState.clean();
        }
        super.onCreate(arguments, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewState.clean();
    }

}
