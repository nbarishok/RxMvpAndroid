package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import com.onemanparty.rxmvpandroid.core.utils.lambda.Action1;
import com.onemanparty.rxmvpandroid.core.utils.lambda.Action2;

/**
 * ViewState with view-based navigation
 */
public interface NavigationViewState<V, T> {

    <S extends T> void addToPending(Action2<V, S> op, S data);

    void addToPending(Action1<V> op);
}
