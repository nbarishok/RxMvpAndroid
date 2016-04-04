package com.onemanparty.rxmvpandroid;

import android.content.Context;

import com.onemanparty.rxmvpandroid.weather.utils.PathManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Base app module
 */
@Module
public class AppModule {

    private final WeatherApplication mApp;

    public AppModule(WeatherApplication app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApp;
    }


    @Provides
    @Singleton
    PathManager providePathManager(Context context) {
        return new PathManager(context);
    }
}
