package com.onemanparty.rxmvpandroid.weather.communication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.persistence.viewstate.impl.serializable.SerializableViewNavigationResolver;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.CautionDialogData;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewState;

import javax.inject.Inject;

@PerFragment
public class WeatherCommunicationBus implements WeatherPresenter, WeatherView {

    private static final String VIEW_STATE_KEY = "VIEW_STATE";
    private final WeatherPresenter presenter;
    private WeatherView view;

    private SerializableViewNavigationResolver<WeatherView> navigationResolver;
    private WeatherViewState viewState;

    @Inject
    public WeatherCommunicationBus(WeatherPresenter presenter, WeatherViewState viewState) {
        this.presenter = presenter;
        this.viewState = viewState;
    }

    @Override
    public void showLoading() {
        viewState.setStateShowLoading();
        WeatherView view = getView();
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        viewState.setStateHideLoading();
        WeatherView view = getView();
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void setData(WeatherViewModel data) {
        viewState.setData(data);
        WeatherView view = getView();
        if (view != null) {
            view.setData(data);
        }
    }

    @Override
    public void showContent() {
        viewState.setStateShowContent();
        WeatherView view = getView();
        if (view != null) {
            view.showContent();
        }
    }

    @Override
    public void showError(WeatherError error) {
        viewState.setStateShowError(error);
        WeatherView view = getView();
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public void showCautionDialog(CautionDialogData data) {
        navigationResolver.resolveNavigation(WeatherView::showCautionDialog, data);
    }
    // presenter
    @Override
    public void loadWeather() {
        presenter.loadWeather();
    }

    @Override
    public void someInsaneActionClicked() {
        presenter.someInsaneActionClicked();
    }

    @Override
    public void attachView(WeatherView view) {
        this.view = view;
        navigationResolver.attachView(view);
        viewState.apply(view);
    }

    @Override
    public void detachView() {
        this.view = null;
        navigationResolver.detachView();
    }

    private WeatherView getView() {
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        presenter.onCreate(arguments, savedInstanceState);
        if (savedInstanceState != null) {
            viewState.restore();
        } else {
            viewState.clean();
        }
        navigationResolver = new SerializableViewNavigationResolver<>(viewState);
        presenter.attachView(this);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        presenter.onSaveInstanceState(bundle);
        bundle.putString(VIEW_STATE_KEY, "123");
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        presenter.onDestroy();
        viewState.clean();
    }
}
