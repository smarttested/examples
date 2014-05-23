package com.smarttested.didemo.guava.collections;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by andrey.vorobyov on 18/03/14.
 */
public class ComparisonTest {


    @Test
    public void testAirports() throws IOException {
        ArrayList<Airport> airports = Lists.newArrayList(getAirports());


        Iterable<Airport> usaAirports = Iterables.filter(airports, new Predicate<Airport>() {
            @Override
            public boolean apply(Airport input) {
                return "Belarus".equals(input.getCountry());
            }
        });

        System.out.println(Joiner.on("\n").join(usaAirports));
    }

    private Airport[] getAirports() throws IOException {
        URL airportsUrl = new URL("http://airports.pidgets.com/v1/airports?format=json");

        Gson gson = new Gson();
        return gson.fromJson(new JsonReader(Resources.newReaderSupplier(airportsUrl, Charsets.UTF_8).getInput()), Airport[].class);


    }
}
