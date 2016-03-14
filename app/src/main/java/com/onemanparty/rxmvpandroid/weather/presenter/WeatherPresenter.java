package com.onemanparty.rxmvpandroid.weather.presenter;

import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

/**
 * Weather: Presenter
 */
public abstract class WeatherPresenter extends Presenter<WeatherView> {
	public abstract void loadWeather();
}