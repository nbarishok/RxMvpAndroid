package com.onemanparty.rxmvpandroid.weather.view.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.weather.view.WeatherView;

/**
 * Model for {@link WeatherView}
 */
public class WeatherViewModel implements Parcelable {

    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.temperature);
    }

    public WeatherViewModel() {
    }

    private WeatherViewModel(Parcel in) {
        this.temperature = in.readInt();
    }

    public static final Parcelable.Creator<WeatherViewModel> CREATOR = new Parcelable.Creator<WeatherViewModel>() {
        public WeatherViewModel createFromParcel(Parcel source) {
            return new WeatherViewModel(source);
        }

        public WeatherViewModel[] newArray(int size) {
            return new WeatherViewModel[size];
        }
    };
}
