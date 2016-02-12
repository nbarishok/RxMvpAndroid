package com.onemanparty.rxmvpandroid.weather.presenter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
public class WeatherPresenterImpl extends WeatherPresenter {

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
     * Subject to publish results about weather in Moscow
     */
    private Subject<WeatherViewModel, WeatherViewModel> dataProvider = BehaviorSubject.create();
    private Subscription dataProviderSubscription;
    /**
     * Subject to publish results about errors during fetching weather for Moscow
     */
    private Subject<WeatherView.WeatherError, WeatherView.WeatherError> errorProvider = PublishSubject.create();
    private Subscription errorProviderSubscription;

    //~~ experimental area
    /**
     * Subject to publish general events (now used only to show / hide loading)
     */
    private Subject<Integer, Integer> weatherEventProvider = PublishSubject.create();
    private Subscription generalEventSubscription;
    final int SHOW_LOADING = 1;
    final int HIDE_LOADING = 2;
    private Runnable mShowLoading = () -> {
        weatherEventProvider.onNext(SHOW_LOADING);
    };
    private Runnable mHideLoading = () -> {
        weatherEventProvider.onNext(HIDE_LOADING);
    };
    //~~ experimental area end
    
    /**
     * ViewModel
     */
    private WeatherViewModel mViewModel;

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
                dataProvider.onNext(mViewModel);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(DATA, mViewModel);
    }

    @Override
    public void attachView(WeatherView view) {
        super.attachView(view);
        dataProviderSubscription = dataProvider.subscribe(this::updateUi);
        errorProviderSubscription = errorProvider.subscribe(this::showError);
        generalEventSubscription = weatherEventProvider.subscribe(this::propagateEvent);
    }

    @Override
    public void detachView() {
        tryUnsubscribe(dataProviderSubscription);
        tryUnsubscribe(errorProviderSubscription);
        tryUnsubscribe(generalEventSubscription);
        super.detachView();
    }

    @Override
    public void loadWeather() {
        mSubscriptions.add(mGetWeather
                .get()
                .subscribeOn(Schedulers.io())               // inject for testing
                .observeOn(AndroidSchedulers.mainThread())  // inject for testing
                .compose(RxTransformers.applyOpBeforeAndAfter(mShowLoading, mHideLoading))
                .subscribe(weather -> {
                    mViewModel = mMapper.map(weather);
                    dataProvider.onNext(mViewModel);
                }, throwable -> {
                    errorProvider.onNext(WeatherView.WeatherError.GENERAL);
                }, () -> {
                    // do nothing
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null && !mSubscriptions.isUnsubscribed()) {
            mSubscriptions.unsubscribe();
        }
    }

    private void updateUi(WeatherViewModel weather) {
        WeatherView view = getView();
        view.setData(weather);
        view.showContent();
    }

    private void showError(WeatherView.WeatherError error) {
        WeatherView view = getView();
        view.showError(error);
    }

    private void propagateEvent(int event) {
        WeatherView view = getView();
        switch (event) {
            case SHOW_LOADING:
                view.showLoading();
                break;
            case HIDE_LOADING:
                view.hideLoading();
                break;
        }
    }

    private void tryUnsubscribe(Subscription s) {
        if (s != null && !s.isUnsubscribed()) {
            s.unsubscribe();
        }
    }
}
