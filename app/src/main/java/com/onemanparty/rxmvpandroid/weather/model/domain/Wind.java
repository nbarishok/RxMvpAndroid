package com.onemanparty.rxmvpandroid.weather.model.domain;

/**
 * Wind
 */
public class Wind {

    private double speed;
    private double deg;

    /**
     *
     * @return
     * The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public double getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(double deg) {
        this.deg = deg;
    }

}
