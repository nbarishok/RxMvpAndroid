package com.onemanparty.rxmvpandroid.core.view.presenter;

import com.onemanparty.rxmvpandroid.core.view.view_model.EmptyViewModel;

/**
 * Add presenter ability to restore view model after low - memory
 *
 * Can be useful with complex screens where separation of one state-full presenter into several
 * state-less presenters seems like overkill
 */
public interface RestorablePresenter<M extends EmptyViewModel> {
    void restoreViewModel(M viewModel);
}
