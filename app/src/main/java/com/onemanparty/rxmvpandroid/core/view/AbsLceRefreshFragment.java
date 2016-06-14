package com.onemanparty.rxmvpandroid.core.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.onemanparty.rxmvpandroid.R;
import com.onemanparty.rxmvpandroid.core.persistence.HasPresenter;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

/**
 * Base class with swipe-to-refresh ability
 * To work properly layout, provided by {@link AbsLceFragment#getContentResId()}, should have {@link SwipeRefreshLayout} with id = R.id.swipe
 */
public abstract class AbsLceRefreshFragment<C extends HasPresenter, M extends EmptyViewModel, E extends Enum<E>, V extends LceView<M, E>> extends AbsLceFragment<C, M, E, V> implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe;

    @Override
    protected void setupViews(View view) {
        super.setupViews(view);
        swipe = (SwipeRefreshLayout)view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        if (hasData()) {
            refreshTrue();
        }
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        refreshFalse();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override public void showContent() {
        super.showContent();
        refreshFalse();
    }

    @Override
    protected int getLayoutId() {
        return isStandardSwipe() ? R.layout.refresh_lce_view : super.getLayoutId();
    }

    protected boolean isStandardSwipe() {
        return true;
    }

    public void enableSwipe() {
        swipe.setEnabled(true);
    }

    public void disableSwipe() {
        swipe.setEnabled(false);
    }

    private void refreshTrue() {
        swipe.post(() -> swipe.setRefreshing(true));
    }

    private void refreshFalse() {
        swipe.post(() -> swipe.setRefreshing(false));
    }
}
