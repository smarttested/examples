package com.smarttested.didemo.guava.collections;

import com.google.common.base.Predicate;

/**
 * Created by andrey.vorobyov on 26/06/14.
 */
public class Predicates {

    public static Predicate<Airport> insideArea(int lat, int lon, int radius) {
        return new Predicate<Airport>() {
            @Override
            public boolean apply(Airport input) {
                boolean bLatPositive = (lat - input.getLat()) < radius && input.getLat() - lat < radius && input.getLat() > 0;
                boolean bLonPositive = (lon - input.getLon()) < radius && input.getLon() - lon < radius && input.getLon() > 0;
                boolean bLatNegative = Math.abs(lat - input.getLat()) < radius && Math.abs(input.getLat() - lat) < radius && input.getLat() < 0;
                boolean bLonNegative = Math.abs(lon - input.getLon()) < radius && Math.abs(input.getLon() - lon) < radius && input.getLon() < 0;
                return (bLatPositive && bLonPositive) || (bLatNegative && bLonNegative) || (bLatPositive && bLonNegative) || (bLatNegative && bLonPositive);
            }
        };

    }
}
