package com.onemanparty.rxmvpandroid.weather.model.repository;

import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;

import rx.Observable;


/**
 * Interface for weather repository
 */
public interface WeatherRepository {

	Observable<WeatherResponse> getWeather(String city);

}
