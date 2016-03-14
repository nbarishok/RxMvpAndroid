package com.onemanparty.rxmvpandroid.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.onemanparty.rxmvpandroid.R;
import com.onemanparty.rxmvpandroid.core.persistence.ComponentManagerFragment;
import com.onemanparty.rxmvpandroid.weather.WeatherApplication;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.di.DaggerWeatherComponent;
import com.onemanparty.rxmvpandroid.weather.view.di.WeatherComponent;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Fragment: weather
 */
public class WeatherFragment extends ComponentManagerFragment<WeatherComponent, WeatherView> implements WeatherView {

    public static final String TAG = WeatherFragment.class.getSimpleName();

    @Inject
    WeatherPresenter presenter;

    @Bind(R.id.weather_tv_temp)
    TextView currentTemperature;

    private WeatherViewModel weather;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    protected WeatherComponent createComponent() {
        return DaggerWeatherComponent
                .builder()
                .appComponent(WeatherApplication.getAppComponent(getActivity()))
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public void setupViews(View view) {
        super.setupViews(view);
        getComponent().getPresenter().loadWeather();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.weather;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(),"start loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(getContext(),"finish loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(WeatherViewModel w) {
        Toast.makeText(getContext(),"updated with data", Toast.LENGTH_SHORT).show();
        weather = w;
    }

    @Override
    public void showContent() {
        currentTemperature.setText(getString(R.string.weather_tv_temperature, weather.getTemperature()));
    }

    @Override
    public void showError(WeatherError error) {
        Toast.makeText(getContext(),"error!", Toast.LENGTH_SHORT).show();
    }
}
