package com.onemanparty.rxmvpandroid;

import android.content.Context;

import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRepository;
import com.onemanparty.rxmvpandroid.weather.model.repository.di.ApiModule;
import com.onemanparty.rxmvpandroid.weather.utils.PathManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Base app component
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    WeatherRepository repository();
    Context context();
    PathManager pathManager();
}
