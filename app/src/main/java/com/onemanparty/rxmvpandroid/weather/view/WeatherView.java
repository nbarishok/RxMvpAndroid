package com.onemanparty.rxmvpandroid.weather.view;

import com.onemanparty.rxmvpandroid.core.view.LceView;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;

public interface WeatherView extends LceView<WeatherViewModel, WeatherView.WeatherError> {

    void showCautionDialog(CautionDialogData data);

    enum WeatherError {
        GENERAL
    }

}
