package com.onemanparty.rxmvpandroid.weather.view;

/**
 * Model for {@link WeatherView}
 */
public class WeatherViewModel {

    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
