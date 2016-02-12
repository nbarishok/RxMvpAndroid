package com.onemanparty.rxmvpandroid.core.persistance;

import com.onemanparty.rxmvpandroid.core.presenter.Presenter;

/**
 * Base interface for dagger components for fragments with presenters
 */
public interface HasPresenter<P extends Presenter> {
    P getPresenter();
}
