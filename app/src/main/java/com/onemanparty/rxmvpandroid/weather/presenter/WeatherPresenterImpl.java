package com.onemanparty.rxmvpandroid.weather.presenter;

import android.os.Bundle;

import com.onemanparty.rxmvpandroid.core.utils.RxTransformers;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowInteractor;
import com.onemanparty.rxmvpandroid.weather.view.CautionDialogData;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapper;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@PerFragment
public class WeatherPresenterImpl implements WeatherPresenter {

    private WeatherMapper mMapper;
    private final GetWeatherInMoscowInteractor mGetWeather;
    private CompositeSubscription mSubscriptions;
    private WeatherView mView;

    private Runnable mShowLoading = () -> {
        mView.showLoading();
    };
    private Runnable mHideLoading = () -> {
        mView.hideLoading();
    };

    @Inject
    public WeatherPresenterImpl(GetWeatherInMoscowInteractor getWeather, WeatherMapper mapper) {
        mGetWeather = getWeather;
        mMapper = mapper;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onCreate(Bundle arguments, Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    // todo abstract away attach / detach of view
    @Override
    public void attachView(WeatherView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public void loadWeather() {
        mSubscriptions.add(mGetWeather
                .get()
                .subscribeOn(Schedulers.io())               // inject for testing
                .observeOn(AndroidSchedulers.mainThread())  // inject for testing
                .compose(RxTransformers.applyOpBeforeAndAfter(mShowLoading, mHideLoading))
                .subscribe(weather -> {
                    updateUi(mMapper.map(weather));
                }, throwable -> {
                    showError(WeatherView.WeatherError.GENERAL);
                }));
    }

    @Override
    public void someInsaneActionClicked() {
        Observable
                .just(new CautionDialogData(101))
                .subscribeOn(Schedulers.io())               // inject for testing
                .delay(3000, TimeUnit.MILLISECONDS)         // simulate network request
                .observeOn(AndroidSchedulers.mainThread())  // inject for testing
                .subscribe(data -> {
                    mView.showCautionDialog(data);
                });
    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null && !mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
    }

    private void updateUi(WeatherViewModel weather) {
        mView.setData(weather);
        mView.showContent();
    }

    private void showError(WeatherView.WeatherError error) {
        mView.showError(error);
    }
}
