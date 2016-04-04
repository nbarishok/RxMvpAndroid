package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.view.LceView;

/**
 * Base ViewState implementation for {@link LceView} with view-based navigation and ability to restore
 */
public abstract class AbsRestorableNavigationLceViewStateImpl<D, E extends Enum<E>, V extends LceView<D, E>, T>
                        extends AbsNavigationLceViewStateImpl<D, E, V, T>
                        implements RestorableViewState {
}
