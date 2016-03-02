package com.onemanparty.rxmvpandroid.weather.view;

import com.onemanparty.rxmvpandroid.core.view.LCEView;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;

/**
 * View: Weather
 */
public interface WeatherView extends LCEView<WeatherViewModel, WeatherView.WeatherError> {

	enum WeatherError {
		GENERAL
	}

}
