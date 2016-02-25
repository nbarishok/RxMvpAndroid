 package com.onemanparty.rxmvpandroid.weather.proxy;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;
import com.onemanparty.rxmvpandroid.weather.view.WeatherViewModel;
import com.onemanparty.rxmvpandroid.weather.view.WeatherViewState;

import javax.inject.Inject;

/**
 * Created by NBarishok on 24.02.2016.
 */
@PerFragment
public class WeatherProxy implements WeatherView, WeatherPresenter {

    private static final String VIEW_STATE_KEY = "VIEW_STATE";
    private final WeatherPresenter mPresenter;
    private WeatherView mView;
    private WeatherViewState mViewState;

    private WeatherViewState createViewState() {
        return new WeatherViewState();
    }

    @Inject
    public WeatherProxy(WeatherPresenter presenter) {
        mPresenter = presenter;
        mViewState = createViewState();
        // we are ready to listen for presenter events
        mPresenter.attachView(this);
    }

    @Override
    public void showLoading() {
        mViewState.setStateShowLoading();
        mView.showLoading();
    }

    @Override
    public void hideLoading() {
        mViewState.setStateHideLoading();
        mView.hideLoading();
    }

    @Override
    public void setData(WeatherViewModel data) {
        mView.setData(data);
    }

    @Override
    public void showContent() {
        mViewState.setStateShowContent();
        mView.showContent();
    }

    @Override
    public void showError(WeatherError error) {
        mViewState.setStateShowError(error);
        mView.showError(error);
    }


    // presenter
    @Override
    public void loadWeather() {
        mPresenter.loadWeather();
    }

    @Override
    public void attachView(WeatherView view) {
        mView = view;
        // proxy in action
        // has data --> push it to view
        mViewState.apply(view);
    }

    @Override
    public void detachView() {
        mView = DetachedWeatherView.instance();
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        mPresenter.onCreate(arguments, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(VIEW_STATE_KEY)) {
                mViewState = savedInstanceState.getParcelable(VIEW_STATE_KEY);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        mPresenter.onSaveInstanceState(bundle);
        bundle.putParcelable(VIEW_STATE_KEY, mViewState);
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        mPresenter.onDestroy();
    }

    private static class DetachedWeatherView implements WeatherView {

        private static final DetachedWeatherView view = new DetachedWeatherView();

        public static DetachedWeatherView instance() {
            return view;
        }
        @Override
        public void showLoading() {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void setData(WeatherViewModel data) {

        }

        @Override
        public void showContent() {

        }

        @Override
        public void showError(WeatherError error) {

        }
    }
}
