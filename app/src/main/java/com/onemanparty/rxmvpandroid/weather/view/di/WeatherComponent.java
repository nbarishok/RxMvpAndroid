package com.onemanparty.rxmvpandroid.weather.view.di;

import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.core.persistence.HasPresenter;
import com.onemanparty.rxmvpandroid.weather.AppComponent;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherFragment;

import dagger.Component;

/**
 * Component for weather screen
 */
@Component(
        dependencies = AppComponent.class,
        modules = WeatherModule.class
)
@PerFragment
public interface WeatherComponent extends HasPresenter<WeatherPresenter> {
    void inject(WeatherFragment fragment);
}
