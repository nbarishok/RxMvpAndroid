package com.onemanparty.rxmvpandroid.core.proxy;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.base.LceViewState;
import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.core.view.LceView;

/**
 * Base classes for all communication buses for {@link LceView}
 * Handles ViewState restore, tracking ViewState for loading - content - error related operations
 */
public class LceCommunicationBus<D, E extends Enum<E>, V extends LceView<D, E>, P extends Presenter<V>, VS extends LceViewState<D, E, V>>
        extends CommunicationBus<V, P>
        implements LceView<D, E> {

    VS viewState;

    public LceCommunicationBus(P presenter, VS viewState) {
        super(presenter);
        this.viewState = viewState;
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        viewState.apply(view);
    }

    @Override
    public void showLoading() {
        viewState.setStateShowLoading();
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        viewState.setStateHideLoading();
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void setData(D data) {
        viewState.setData(data);
        if (view != null) {
            view.setData(data);
        }
    }

    @Override
    public void showContent() {
        viewState.setStateShowContent();
        if (view != null) {
            view.showContent();
        }
    }

    @Override
    public void showError(E error) {
        boolean isViewAttached = view != null;
        viewState.setStateShowError(error, isViewAttached);
        if (isViewAttached) {
            view.showError(error);
        }
    }

    @Override
    public void loadData() {
        if (view != null) {
            view.loadData();
        }
    }
}
