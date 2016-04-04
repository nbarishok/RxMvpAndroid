package com.onemanparty.rxmvpandroid.core.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.onemanparty.rxmvpandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Base activity implementation
 */
public class BaseActivity extends AppCompatActivity {

	@Bind(R.id.container)
	ViewGroup mContainer;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView();
		ButterKnife.bind(this);
	}

	public void setContentView() {
		super.setContentView(getLayoutId());
	}

	protected int getLayoutId() {
		return R.layout.single_fragment_activity;
	}

	protected void setSingleFragment(final Fragment fragment, final String tag) {
		final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(mContainer.getId(), fragment, tag);
		transaction.commit();
	}

}
