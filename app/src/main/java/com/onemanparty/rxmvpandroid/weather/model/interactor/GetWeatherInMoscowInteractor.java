package com.onemanparty.rxmvpandroid.weather.model.interactor;

import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;

import rx.Observable;

/**
 * Use case: get weather in Moscow
 */
public interface GetWeatherInMoscowInteractor {

	Observable<WeatherResponse> get();

}
