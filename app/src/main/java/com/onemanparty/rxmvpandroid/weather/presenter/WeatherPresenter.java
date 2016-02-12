package com.onemanparty.rxmvpandroid.weather.presenter;

import com.onemanparty.rxmvpandroid.core.presenter.BasePresenter;
import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

/**
 * Weather: Presenter
 */
public abstract class WeatherPresenter extends BasePresenter<WeatherView> {

	public abstract void loadWeather();

}
