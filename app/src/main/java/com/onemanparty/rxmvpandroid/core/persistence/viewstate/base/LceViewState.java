package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

/**
 * ViewState for {@link LceView}
 */
public interface LceViewState<D extends EmptyViewModel, E extends Enum<E>, V extends LceView<D, E>> extends ViewState<V> {

    int STATE_UNINITIALIZED = -1;

    int STATE_SHOW_CONTENT = 1;
    int STATE_SHOW_LOADING = 2;
    int STATE_SHOW_ERROR = 3;

    void setStateShowLoading();
    void setStateHideLoading();
    void setStateShowError(E error, boolean isShown);
    void setStateShowContent();
    void setData(D data);
    D getData();
}
