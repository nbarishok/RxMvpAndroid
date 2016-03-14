package com.onemanparty.rxmvpandroid.core.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Presenter interface
 *
 * Presenter acts like a middle-man between View and Model.
 * Usually implementations represented as a set of interactors
 *
 * Presenter is aware of fragment / activity - lifecycle.
 * Lifecycle methods can be used to init presenter state, restore state, free resources etc.
 */
public abstract class Presenter<V> {

	private V mView;

	/**
	 * attach view
	 * @param view view
     */
	public void attachView(V view) {
		mView = view;
	}

	/**
	 * detach view
	 */
	public void detachView() {
		mView = null;
	}

	public V getView() {
		return mView;
	}

	/**
	 * Provide presenter with some input
	 * @param arguments intent bundle
	 * @param savedInstanceState saved state
	 *                           (data to restore after low-memory is supposed to be here)
     */
	public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {

	}

	/**
	 * Save presenter state
	 * @param bundle bundle
     */
	public void onSaveInstanceState(Bundle bundle) {

	}

	/**
	 * Presenter is about to be destroyed
	 * Free resources etc.
	 */
	public void onDestroy() {

	}
}
