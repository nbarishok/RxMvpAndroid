package com.onemanparty.rxmvpandroid.weather.presenter;

import com.onemanparty.rxmvpandroid.core.presenter.Presenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

public interface WeatherPresenter extends Presenter<WeatherView> {
    void loadWeather();

    void someInsaneActionClicked();
}