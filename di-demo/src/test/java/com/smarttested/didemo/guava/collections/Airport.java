package com.smarttested.didemo.guava.collections;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrey.vorobyov on 23/05/14.
 */
public class Airport {

    private String code;
    private Float lat;
    private Float lon;
    private String name;
    private String city;
    private String state;
    private String country;
    private Long woeid;
    private String tz;
    private String phone;
    private AirportType type;
    private String email;
    private String url;
    @SerializedName("runway_length")
    private Integer runwayLength;
    private Integer elev;
    private String icao;
    @SerializedName("direct_flights")
    private Integer directFlights;
    private Integer carriers;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getWoeid() {
        return woeid;
    }

    public void setWoeid(Long woeid) {
        this.woeid = woeid;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AirportType getType() {
        return type;
    }

    public void setType(AirportType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRunwayLength() {
        return runwayLength;
    }

    public void setRunwayLength(Integer runwayLength) {
        this.runwayLength = runwayLength;
    }

    public Integer getElev() {
        return elev;
    }

    public void setElev(Integer elev) {
        this.elev = elev;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public Integer getDirectFlights() {
        return directFlights;
    }

    public void setDirectFlights(Integer directFlights) {
        this.directFlights = directFlights;
    }

    public Integer getCarriers() {
        return carriers;
    }

    public void setCarriers(Integer carriers) {
        this.carriers = carriers;
    }


    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", woeid=" + woeid +
                ", tz='" + tz + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", runwayLength=" + runwayLength +
                ", elev=" + elev +
                ", icao='" + icao + '\'' +
                ", directFlights=" + directFlights +
                ", carriers=" + carriers +
                '}';
    }
}
