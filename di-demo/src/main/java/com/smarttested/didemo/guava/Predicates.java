package com.smarttested.didemo.guava;

import com.google.common.base.Predicate;

/**
 * Created by andrey.vorobyov on 26/06/14.
 */
public class Predicates {

    public static Predicate<Airport> insideArea(final double lat, final double lon, final double radius) {
        return new Predicate<Airport>() {
            @Override
            public boolean apply(Airport input) {
                int radiusEarth = 6371;
                double dist = Math.acos(Math.sin(Math.PI * lat / 180) * Math.sin(Math.PI * input.getLat() / 180) +
                        Math.cos(Math.PI * lat / 180) * Math.cos(Math.PI * input.getLat() / 180) * Math.cos(Math.PI * lon / 180
                                - Math.PI * input.getLon() / 180)) * radiusEarth;
                return dist < radius;
            }
        };
    }

    public static Predicate<Airport> insideArea(Place place, final double radius) {
        return insideArea(place.getLat(), place.getLon(), radius);
    }

}
