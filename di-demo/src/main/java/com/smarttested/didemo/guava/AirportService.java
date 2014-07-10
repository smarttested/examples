package com.smarttested.didemo.guava;

import com.google.common.base.Predicate;

import java.io.IOException;
import java.util.List;

/**
 * @author avarabyeu
 */
public interface AirportService {

    /**
     * Returns full airports list
     *
     * @return
     * @throws java.io.IOException
     */
    List<Airport> getAirports() throws IOException;

    /**
     * Returns airport list filtered by provided {@link com.google.common.base.Predicate}
     *
     * @param predicate
     * @return
     * @throws java.io.IOException
     */
    List<Airport> getAirports(Predicate<Airport> predicate) throws IOException;
}
