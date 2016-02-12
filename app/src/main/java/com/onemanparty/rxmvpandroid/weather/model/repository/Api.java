package com.onemanparty.rxmvpandroid.weather.model.repository;

import com.onemanparty.rxmvpandroid.weather.model.domain.WeatherResponse;


import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Retrofit interface for Open Weather Api
 */
public interface Api {

    @GET("weather")
    Observable<WeatherResponse> get(@Query("APPID") String key, @Query("q") String q);

}
