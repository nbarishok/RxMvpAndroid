package com.onemanparty.rxmvpandroid.weather.presenter;

import android.os.Bundle;

import com.onemanparty.rxmvpandroid.core.presenter.BasePresenter;
import com.onemanparty.rxmvpandroid.core.utils.RxTransformers;
import com.onemanparty.rxmvpandroid.core.view.PerFragment;
import com.onemanparty.rxmvpandroid.weather.model.interactor.GetWeatherInMoscowInteractor;
import com.onemanparty.rxmvpandroid.weather.view.WeatherView;
import com.onemanparty.rxmvpandroid.weather.view.WeatherViewModel;
import com.onemanparty.rxmvpandroid.weather.view.mapper.WeatherMapper;


import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

/**
 * Implementation of {@link WeatherPresenter}
 */
@PerFragment
public class WeatherPresenterImpl implements WeatherPresenter {

    /**
     * Key to save view model
     */
    private static final String DATA = "DATA";

    /**
     * Data mapper for UI
     */
    private WeatherMapper mMapper;
    /**
     * Use case: get weather in Moscow
     */
    private final GetWeatherInMoscowInteractor mGetWeather;
    private CompositeSubscription mSubscriptions;

    /**
     * ViewModel
     */
    private WeatherViewModel mViewModel;
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
        if (savedInstanceState != null) {
            mViewModel = savedInstanceState.getParcelable(DATA);
            if (mViewModel != null) {
                updateUi(mViewModel);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(DATA, mViewModel);
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
                    mViewModel = mMapper.map(weather);
                    updateUi(mViewModel);
                }, throwable -> {
                    showError(WeatherView.WeatherError.GENERAL);
                }, () -> {
                    // do nothing
                }));
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
