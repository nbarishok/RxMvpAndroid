package com.onemanparty.rxmvpandroid.weather.model.interactor;

import com.onemanparty.rxmvpandroid.core.model.Interactor;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;
import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Implementation of {@link GetWeatherInMoscowInteractor}
 */
public class GetWeatherInMoscowUseCase extends Interactor
								implements GetWeatherInMoscowInteractor {

	private final WeatherRepository mRepository;

	public GetWeatherInMoscowUseCase(WeatherRepository repository) {
		mRepository = repository;
	}

	@Override
	public Observable<WeatherResponse> get() {
		return mRepository.getWeather("Moscow");
	}
}
