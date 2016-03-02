package com.onemanparty.rxmvpandroid.weather.view.mapper;

import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;

/**
 * Data mapper implementation (domain -> ui) for {@link com.onemanparty.rxmvpandroid.weather.view.WeatherView}
 */
public class WeatherMapperImpl implements WeatherMapper {

    private static final double KELVIN_CONST = 273.15;

    public WeatherViewModel map(WeatherResponse weather) {
        WeatherViewModel result = new WeatherViewModel();
        int celsiusTemp = toCelsius((int) weather.getMain().getTemp());
        result.setTemperature(celsiusTemp);
        return result;
    }

    private int toCelsius(int temp) {
        return (int) (temp - KELVIN_CONST);
    }
}
