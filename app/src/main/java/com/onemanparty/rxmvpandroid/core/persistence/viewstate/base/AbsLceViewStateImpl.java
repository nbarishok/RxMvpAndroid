package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.view.LceView;

/**
 * Base ViewState implementation for {@link LceView}
 * @param <D> type of data view operates on
 * @param <E> type of errors view can handle
 * @param <V> type of view
 */
public abstract class AbsLceViewStateImpl<D, E extends Enum<E>, V extends LceView<D, E>> implements LceViewState<D, E, V> {

    protected int currentState;
    protected E error;
    protected D data;

    public AbsLceViewStateImpl() {
        currentState = STATE_UNINITIALIZED;
    }

    public void setStateShowLoading() {
        currentState = STATE_SHOW_LOADING;
        error = null;
    }

    public void setStateHideLoading() {
        if (currentState != STATE_SHOW_ERROR) {
            currentState = STATE_SHOW_CONTENT;
            error = null;
        }
    }

    public void setStateShowContent() {
        currentState = STATE_SHOW_CONTENT;
        error = null;
    }

    public void setStateShowError(E storeError) {
        error = storeError;
        currentState = STATE_SHOW_ERROR;
    }

    public void setData(D data) {
        this.data = data;
    }

    @Override
    public void apply(V view) {
        restoreModel(view);
        switch (currentState) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;
            case STATE_SHOW_ERROR:
                view.showError(error);
                break;
            default:
                break;
        }
    }

    private void restoreModel(V view) {
        if (data != null) {
            view.setData(data);
            view.hideLoading();
            view.showContent();
        }
    }
}
