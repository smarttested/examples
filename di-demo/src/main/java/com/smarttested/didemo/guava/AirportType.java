package com.smarttested.didemo.guava;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Created by andrey.vorobyov on 30/05/14.
 */
public enum AirportType {

    AIRPORT("Airports"),
    MILITARY_AIRPORT("Military Airport");

    private String name;

    private AirportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AirportType fromName() {
        return AIRPORT;
    }


    public static AirportType fromName(final String value) {
        Optional<AirportType> airportType = Iterables.tryFind(Lists.newArrayList(AirportType.values()), new Predicate<AirportType>() {
            @Override
            public boolean apply(AirportType input) {
                return input.name.equals(value);
            }
        });
        return airportType.isPresent() ? airportType.get() : null;
    }


}
