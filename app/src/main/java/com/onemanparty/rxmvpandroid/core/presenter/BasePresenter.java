package com.onemanparty.rxmvpandroid.core.presenter;

import android.os.Bundle;

/**
 * Base presenter implementation
 * One should inherit from this class to build own presenters
 *
 * Abstracts away attachment and detachment of view + stub override of lifecycle methods
 */
public abstract class BasePresenter<V> implements Presenter<V> {

	private V mView;

	@Override
	public void attachView(V view) {
		mView = view;
	}

	@Override
	public void detachView() {
		mView = null;
	}

	public V getView() {
		return mView;
	}

	@Override
	public void onCreate(Bundle arguments, Bundle savedInstanceState) {

	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {

	}

	@Override
	public void onDestroy() {

	}

}
