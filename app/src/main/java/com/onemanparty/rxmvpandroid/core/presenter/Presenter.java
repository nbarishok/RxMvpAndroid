package com.onemanparty.rxmvpandroid.core.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.view.View;

/**
 * Presenter interface
 *
 * Presenter acts like a middle-man between View and Model.
 * Usually implementations represented as a set of interactors
 *
 * Presenter is aware of fragment / activity - lifecycle.
 * Lifecycle methods can be used to init presenter state, restore state, free resources etc.
 *
 * By contract presenter is free to communicate with view without null-checks between onCreate and onDestroy
 */
public interface Presenter<V extends View> {

	/**
	 * attach view
	 * @param view view
     */
	void attachView(V view);

	/**
	 * detach view
	 */
	void detachView();

	/**
	 * Provide presenter with some input
	 * @param arguments intent bundle
	 * @param savedInstanceState saved state
	 *                           (data to restore after low-memory is supposed to be here)
     */
	void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState);

	/**
	 * Save presenter state
	 * @param bundle bundle
     */
	void onSaveInstanceState(Bundle bundle);

	/**
	 * Presenter is about to be destroyed
	 * Free resources etc.
	 */
	void onDestroy();
}
