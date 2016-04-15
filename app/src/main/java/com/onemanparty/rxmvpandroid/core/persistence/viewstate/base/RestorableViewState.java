package com.onemanparty.rxmvpandroid.core.persistence.viewstate.base;

import android.os.Bundle;

/**
 * Saving / restoration view state by providing an object responsible for these actions
 */
public interface RestorableViewState<S> {

    void save(S out);

    RestorableViewState restore(S in);

}
