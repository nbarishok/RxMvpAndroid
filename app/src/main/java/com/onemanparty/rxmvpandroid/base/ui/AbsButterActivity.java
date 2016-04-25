package com.onemanparty.rxmvpandroid.base.ui;

import android.os.Bundle;

import com.onemanparty.rxmvpandroid.core.view.AbsActivity;

import butterknife.ButterKnife;

/**
 * {link AbsActivity} with ButterKnife support
 * Move outside core because one can not make lib with butterknife
 */
public abstract class AbsButterActivity extends AbsActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
