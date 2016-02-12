package com.onemanparty.rxmvpandroid.weather.model.repository.di;

import com.onemanparty.rxmvpandroid.BuildConfig;
import com.onemanparty.rxmvpandroid.weather.model.repository.Api;
import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRepository;
import com.onemanparty.rxmvpandroid.weather.model.repository.WeatherRetrofitRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Module with Open Weather Api dependencies
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public Api provideApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.WEATHER_API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    public WeatherRepository provideWeatherRepository(Api api) {
        WeatherRepository repo = new WeatherRetrofitRepository(api);
        return repo;
    }
}
