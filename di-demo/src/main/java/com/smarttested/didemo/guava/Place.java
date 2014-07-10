package com.smarttested.didemo.guava;

/**
 * Created by andrey.vorobyov on 10/07/14.
 */
public class Place {

    private final double lat;
    private final double lon;

    public Place(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
