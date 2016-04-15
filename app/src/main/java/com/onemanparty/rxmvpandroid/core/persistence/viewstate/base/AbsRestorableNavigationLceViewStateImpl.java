package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.view.LceView;

/**
 * Base ViewState implementation for {@link LceView} with view-based navigation and ability to restore it by object,
 * provided in {@link RestorableViewState#save(Object)} and {@link RestorableViewState#restore(Object)} methods
 */
public abstract class AbsRestorableNavigationLceViewStateImpl<D, E extends Enum<E>, V extends LceView<D, E>, T, S>
        extends AbsNavigationLceViewStateImpl<D, E, V, T>
        implements RestorableViewState<S> {
}