package com.smarttested.didemo.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by andrey.vorobyov on 18/03/14.
 */
public class ComparisonTest {


    @Test
    public void testAirports() throws IOException {
        List<Airport> airports = getAirports();

        Iterable<Airport> usaAirports = Iterables.filter(airports, new Predicate<Airport>() {
            @Override
            public boolean apply(Airport input) {
                return "Belarus".equals(input.getCountry());
            }
        });

        System.out.println(Joiner.on("\n").join(usaAirports));
    }

    private List<Airport> getAirports() throws IOException {
        return new AirportServiceImpl().getAirports();
    }
}
