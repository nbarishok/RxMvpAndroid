package com.onemanparty.rxmvpandroid.base.ui;

import android.view.View;

import com.onemanparty.rxmvpandroid.core.persistence.HasPresenter;
import com.onemanparty.rxmvpandroid.core.view.AbsLceFragment;
import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

import butterknife.ButterKnife;

/**
 * {link AbsLceFragment} with ButterKnife support
 * Move outside core because one can not make lib with butterknife
 */
public abstract class AbsButterLceFragment<C extends HasPresenter, M extends EmptyViewModel, E extends Enum<E>, V extends LceView<M, E>> extends AbsLceFragment<C, M, E, V> {

    @Override
    protected void preSetupViews(View view) {
        super.preSetupViews(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
