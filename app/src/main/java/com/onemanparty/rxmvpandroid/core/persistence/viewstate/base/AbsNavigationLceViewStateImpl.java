package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.annimon.stream.Stream;
import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Base ViewState implementation for {@link LceView} with view-based navigation
 */
public abstract class AbsNavigationLceViewStateImpl<D extends EmptyViewModel, E extends Enum<E>, V extends LceView<D, E>, T>
                        extends AbsLceViewStateImpl<D, E, V>
                        implements NavigationViewState<V, T> {

    protected List<PendingStateChange<V>> pendingStateChangesList;

    public AbsNavigationLceViewStateImpl() {
        pendingStateChangesList = new ArrayList<>();
    }

    protected void addPendingStateChange(PendingStateChange<V> dto) {
        pendingStateChangesList.add(dto);
        onPendingStateChangesListAdded();
    }

    protected void onPendingStateChangesListAdded() {
        // custom logic on item added
        // override if necessary
    }

    protected void setPendingStateChangeList(List<PendingStateChange<V>> list) {
        pendingStateChangesList = new ArrayList<>(list);
    }

    @Override
    public void apply(V view) {
        super.apply(view);
        runPendingOperations(view);
    }

    private void runPendingOperations(V view) {
        Stream.of(pendingStateChangesList).forEach(pendingChange -> pendingChange.apply(view));
        pendingStateChangesList.clear();
    }
}
