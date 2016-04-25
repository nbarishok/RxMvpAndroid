package com.onemanparty.rxmvpandroid.core.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.onemanparty.rxmvpandroid.R;


public abstract class AbsSingleFragmentActivity extends AbsActivity {

	ViewGroup container;

	@Override
	protected void setupViews() {
		super.setupViews();
		container = (ViewGroup) findViewById(R.id.container);
	}

	protected int getLayoutId() {
		return R.layout.single_fragment_activity;
	}

	protected void setSingleFragment(final Fragment fragment, final String tag) {
		final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(container.getId(), fragment, tag);
		transaction.commit();
	}

}
