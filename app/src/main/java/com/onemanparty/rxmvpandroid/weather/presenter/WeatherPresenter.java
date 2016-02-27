package com.onemanparty.rxmvpandroid.weather.presenter;

import com.onemanparty.rxmvpandroid.core.presenter.BasePresenter;
import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

/**
 * Weather: Presenter
 */
public interface WeatherPresenter extends Presenter<WeatherView> {
	void loadWeather();
}