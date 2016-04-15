package com.onemanparty.rxmvpandroid.weather.communication;

import com.onemanparty.rxmvpandroid.core.proxy.SelfRestorableNavigationLceCommunicationBus;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.CautionDialogData;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewState;

import javax.inject.Inject;

@PerFragment
public class WeatherCommunicationBus
        extends SelfRestorableNavigationLceCommunicationBus<WeatherViewModel, WeatherView.WeatherError, WeatherView, WeatherPresenter, WeatherViewState>
        implements WeatherPresenter, WeatherView {

    @Inject
    public WeatherCommunicationBus(WeatherPresenter presenter, WeatherViewState viewState) {
        super(presenter, viewState);
    }

    @Override
    public void showCautionDialog(CautionDialogData data) {
        getNavigationResolver().resolveNavigation(WeatherView::showCautionDialog, data);
    }

    // presenter
    @Override
    public void loadWeather() {
        getPresenter().loadWeather();
    }

    @Override
    public void someInsaneActionClicked() {
        getPresenter().someInsaneActionClicked();
    }
}
