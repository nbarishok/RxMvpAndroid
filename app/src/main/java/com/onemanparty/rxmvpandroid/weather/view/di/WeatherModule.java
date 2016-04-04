package com.onemanparty.rxmvpandroid.weather.view.di;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.storage.FileViewStateStorage;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.storage.ViewStateStorage;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.communication.WeatherCommunicationBus;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowInteractor;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowUseCase;
import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRepository;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenterImpl;
import com.onemanparty.rxmvpandroid.weather.utils.PathManager;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapper;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapperImpl;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewState;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

    private final static String VIEW_STATE_FILE_NAME = WeatherModule.class.getSimpleName();

    @Provides @PerFragment
    GetWeatherInMoscowInteractor provideGetWeatherInterator(WeatherRepository repo) {
        return new GetWeatherInMoscowUseCase(repo);
    }

    @Provides @PerFragment
    WeatherMapper provideWeatherMapper() {
        return new WeatherMapperImpl();
    }

    @Provides
    ViewStateStorage provideViewStateStorage(PathManager manager) {
        String fullPath = manager.getCachePath() + VIEW_STATE_FILE_NAME;
        return new FileViewStateStorage(fullPath);
    }

    @Provides
    WeatherViewState provideViewState(ViewStateStorage storage) {
        return new WeatherViewState(storage);
    }

    @Provides
    @PerFragment
    WeatherPresenter provideCommunicationBus(@Named("presenter") WeatherPresenter presenter, WeatherViewState viewState) {
        return new WeatherCommunicationBus(presenter, viewState);
    }

    @Provides
    @Named("presenter")
    @PerFragment
    WeatherPresenter provideWeatherPresenter(GetWeatherInMoscowInteractor getWeather, WeatherMapper mapper) {
        return new WeatherPresenterImpl(getWeather, mapper);
    }
}
