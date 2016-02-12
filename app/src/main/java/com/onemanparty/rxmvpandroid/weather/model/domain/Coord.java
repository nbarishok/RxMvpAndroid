package com.onemanparty.rxmvpandroid.weather.model.domain;

/**
 * Coord
 */
public class Coord {

    private double lon;
    private double lat;

    /**
     *
     * @return
     * The lon
     */
    public double getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The lat
     */
    public double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

}
