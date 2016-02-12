package com.onemanparty.rxmvpandroid.weather.model.repository;

import com.onemanparty.rxmvpandroid.BuildConfig;
import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Implementation of {@link WeatherRepository} that fetch data from Open Weather Api
 */
public class WeatherRetrofitRepository
		implements WeatherRepository {

	Api api;

	public WeatherRetrofitRepository(Api api) {
		this.api = api;
	}

	@Override
	public Observable<WeatherResponse> getWeather(String city) {
		return api.get(BuildConfig.WEATHER_API_KEY, city);
	}
}
