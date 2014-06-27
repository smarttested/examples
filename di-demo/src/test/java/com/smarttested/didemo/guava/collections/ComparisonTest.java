package com.smarttested.didemo.guava.collections;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by andrey.vorobyov on 18/03/14.
 */
public class ComparisonTest {

    private static final Set<String> SET1 = Sets.newHashSet("ONE", "two", "three");
    private static final Set<String> SET2 = Sets.newHashSet("three", "four", "five");

    private static final int lat = 50;
    private static final int lon = 50;
    private static final int radius = 5;


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

    @Test
    public void nearRadiusAirportsTest() throws IOException {
        List<Airport> airports = getAirports();
        Iterable<Airport> countryAirports =
                Iterables.filter(airports, Predicates.insideArea(lat, lon, radius));

        System.out.println(Joiner.on("\n").join(countryAirports));
    }

    @Test
    public void testIntersection(){


        Sets.SetView<String> intersection = Sets.intersection(SET1, SET2);
        System.out.println("Intersection: " + intersection);

        Sets.SetView<String> difference = Sets.symmetricDifference(SET1, SET2);
        System.out.println("Difference: " + difference);



        System.out.println("Difference Left: " + Sets.difference(SET1, SET2));
    }

    @Test
    //any - like contains one
    //all - like contains ALL
    public void testAny(){
       boolean result = Iterables.any(SET1, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return "one".equals(input);
            }
        });
        System.out.println(result);
    }


    @Test
    // composition: AND, OR
    public void testComposition(){
        Predicate<String> predicate = com.google.common.base.Predicates.and(new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return "ONE".equals(input);
            }
        }, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                assert input != null;
                return input.toUpperCase().equals(input);
            }
        });

        System.out.println(Iterables.any(SET1, predicate));

    }


    private List<Airport> getAirports() throws IOException {
        return new AirportServiceImpl().getAirports();
    }
}
