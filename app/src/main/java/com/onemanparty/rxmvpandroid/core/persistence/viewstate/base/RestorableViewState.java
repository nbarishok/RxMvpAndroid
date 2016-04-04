package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

/**
 * ViewState view ability to restore
 */
public interface RestorableViewState {
    void save();
    void restore();
}
