package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.error_declaration.ErrorType;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.error_declaration.ErrorTypes;
import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

/**
 * Base ViewState implementation for {@link LceView}
 *
 * @param <D> type of data view operates on
 * @param <E> type of errors view can handle
 * @param <V> type of view
 */
public abstract class AbsLceViewStateImpl<D extends EmptyViewModel, E extends Enum<E>, V extends LceView<D, E>> implements LceViewState<D, E, V> {

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

    @Override
    public void setStateShowError(E storeError, boolean isShown) {
        boolean isOneShot = isOneShot(storeError);
        if (isOneShot && isShown) {
            return;
        }

        error = storeError;
        currentState = STATE_SHOW_ERROR;
    }

    public void setData(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
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
                cleanIfOneShot();
                break;
            default:
                break;
        }
    }

    private void cleanIfOneShot() {
        if (isOneShot(error)) {
            error = null;
            currentState = STATE_SHOW_CONTENT;
        }
    }

    private void restoreModel(V view) {
        if (data != null && !data.isEmpty()) {
            view.setData(data);
            view.hideLoading();
            view.showContent();
        } else {
            view.loadData();
        }
    }

    // TODO generify things there will be new error types
    private boolean isOneShot(E error) {
        boolean hasAnnotation = false;
        try {
            ErrorType type = error.getDeclaringClass().getField(error.name()).getAnnotation(ErrorType.class);
            if (type != null) {
                hasAnnotation = type.type().equals(ErrorTypes.ONE_SHOT);
            }
        } catch (NoSuchFieldException e) {
            // assume false
        }
        return hasAnnotation;
    }
}
