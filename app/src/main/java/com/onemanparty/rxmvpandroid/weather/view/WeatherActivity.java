package com.onemanparty.rxmvpandroid.weather.view;

import android.os.Bundle;

import com.onemanparty.rxmvpandroid.core.view.BaseActivity;

/**
 * Screen: weather
 */
public class WeatherActivity extends BaseActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            setSingleFragment(WeatherFragment.newInstance(), WeatherFragment.TAG);
        }
    }

}
