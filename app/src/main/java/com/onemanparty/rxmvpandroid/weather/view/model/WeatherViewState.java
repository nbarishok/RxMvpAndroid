package com.onemanparty.rxmvpandroid.weather.view.model;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.StorageBackedNavigationLceViewStateImpl;
import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.storage.ViewStateStorage;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

/**
 * Alias name for Weather ViewState
 */
public class WeatherViewState extends StorageBackedNavigationLceViewStateImpl<WeatherViewModel, WeatherView.WeatherError, WeatherView> {

    public WeatherViewState(ViewStateStorage storage) {
        super(storage);
    }
}
