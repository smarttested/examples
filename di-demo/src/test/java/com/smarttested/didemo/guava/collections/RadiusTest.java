package com.smarttested.didemo.guava.collections;


import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.smarttested.didemo.guava.Airport;
import com.smarttested.didemo.guava.AirportServiceImpl;
import com.smarttested.didemo.guava.Place;
import com.smarttested.didemo.guava.Predicates;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by andrey.vorobyov on 10/07/14.
 */
public class RadiusTest {
    /*Red Square coordinates http://goo.gl/T6tYLy*/
    private static final Place MOSCOW_RED_SQUARE = new Place(55.754194, 37.620139);
    private static final double radius = 300;


    @Test(description = "return the airports in radius from set coordinates")
    public void nearAirportsRadiusTest() throws IOException {
        List<Airport> airports = getAirports();
        Iterable<Airport> countryAirports =
                Iterables.filter(airports, Predicates.insideArea(MOSCOW_RED_SQUARE, radius));
        Assert.assertTrue(countryAirports.iterator().hasNext(), "There is no airports!");

        System.out.println(Joiner.on("\n").join(countryAirports));

        //TODO add smartasserts here (once it get released)
    }

    private List<Airport> getAirports() throws IOException {
        return new AirportServiceImpl().getAirports();
    }
}
