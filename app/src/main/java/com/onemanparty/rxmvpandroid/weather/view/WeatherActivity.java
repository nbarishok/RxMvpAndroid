package com.onemanparty.rxmvpandroid.weather.view;

import android.os.Bundle;

import com.onemanparty.rxmvpandroid.core.view.AbsSingleFragmentActivity;

public class WeatherActivity extends AbsSingleFragmentActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            setSingleFragment(WeatherFragment.newInstance(), WeatherFragment.TAG);
        }
    }

    @Override
    protected void setupToolbarImpl() {
        setToolbarTitle("The Weather");
    }

}
