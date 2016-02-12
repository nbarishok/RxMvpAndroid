package com.onemanparty.rxmvpandroid.weather.view.di;

import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowInteractor;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowUseCase;
import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRepository;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenterImpl;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapper;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapperImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Module with weather screen dependencies
 */
@Module
public class WeatherModule {

    @Provides @PerFragment
    GetWeatherInMoscowInteractor provideGetWeatherInterator(WeatherRepository repo) {
        return new GetWeatherInMoscowUseCase(repo);
    }

    @Provides @PerFragment
    WeatherMapper provideWeatherMapper() {
        return new WeatherMapperImpl();
    }

    @Provides
    @PerFragment
    WeatherPresenter provideWeatherPresenter(GetWeatherInMoscowInteractor getWeather, WeatherMapper mapper) {
        return new WeatherPresenterImpl(getWeather, mapper);
    }

}
