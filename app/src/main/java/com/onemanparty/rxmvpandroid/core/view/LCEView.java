package com.onemanparty.rxmvpandroid.core.view;

/**
 * Interface for Load-Content-Error view
 */
public interface LceView<D, E> {

	void showLoading();
	void hideLoading();

	void setData(D data);
	void showContent();

	void showError(E error);

}
