package com.smarttested.didemo.guava.collections;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

/**
 * @author Implementation of Airport Service using the <a href="http://airports.pidgets.com/">http://airports.pidgets.com/</a> Web Service
 */
class AirportServiceImpl implements AirportService {


    /**
     * Default Web Service URL
     */
    private static final String DEFAULT_URL = "http://airports.pidgets.com/v1";

    /**
     * Cache file
     */
    private static final String AIRPORTS_CACHE_FILE = "airports.temp";

    /**
     * WebService URL
     */
    private String baseUrl;

    /**
     * JSON Serializer
     */
    private Gson gson;


    private FileCache airportsCache;

    /**
     * Default constructor. Default web service URL will be used
     */
    AirportServiceImpl() throws IOException {
        this(DEFAULT_URL);
    }

    /**
     * Constructor with WebService base url as parameter
     *
     * @param baseUrl - WebService base URL
     */
    AirportServiceImpl(String baseUrl) throws IOException {
        this.baseUrl = baseUrl;
        airportsCache = new FileCache(AIRPORTS_CACHE_FILE, Duration.ofDays(1));

        /* Initializer GSON with custom Serializer/Deserializer */
        gson = new GsonBuilder().registerTypeAdapter(AirportType.class, new EnumTypeDeserializer()).create();
    }

    @Override
    public List<Airport> getAirports() throws IOException {
        if (!airportsCache.exists()) {
            System.out.println("Loading from URL...");
            URL airportsUrl = buildUrl(baseUrl, "/airports?format=json");
            byte[] airportsList = Resources.asByteSource(airportsUrl).read();
            airportsCache.store(ByteSource.wrap(airportsList));
        }

        return gson.fromJson(new JsonReader(airportsCache.load().asCharSource(Charsets.UTF_8).openBufferedStream()), new TypeToken<List<Airport>>() {
        }.getType());
    }

    @Override
    public List<Airport> getAirports(Predicate<Airport> predicate) throws IOException {
        return Lists.newArrayList(Iterables.filter(getAirports(), predicate));
    }

    private URL buildUrl(String base, String resource) {
        StringBuilder builder = new StringBuilder();
        String url;
        if ('/' == base.charAt(base.length() - 1)) {
            url = builder.append(base, 0, base.length() - 2).append(resource).toString();
        } else {
            url = builder.append(base).append(resource).toString();
        }
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Incorrect URL provided: " + url);
        }
    }


}
