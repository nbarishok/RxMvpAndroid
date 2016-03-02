package com.onemanparty.rxmvpandroid.weather.view.mapper;

import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;

/**
 * Interface for data mapping for {@link com.onemanparty.rxmvpandroid.weather.view.WeatherView}
 */
public interface WeatherMapper {
    WeatherViewModel map(WeatherResponse weather);
}
