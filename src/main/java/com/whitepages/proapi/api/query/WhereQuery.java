package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * An abstract superclass for all queries that can specify "where" parameters.
 */
public abstract class WhereQuery extends EntityQuery {

    private String streetLine1;
    private String streetLine2;

    private String city;
    private String stateCode;
    private String postalCode;
    private String countryCode;

    private Double latitude;
    private Double longitude;
    private Double radius;

    public WhereQuery() {
    }

    public WhereQuery(EntityId id) {
        super(id);
    }

    public WhereQuery(String streetLine1, String streetLine2, String city, String stateCode, String postalCode) {
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
    }

    public WhereQuery(String city, String stateCode, String postalCode) {
        this.city = city;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
    }

    public WhereQuery(Double latitude, Double longitude, Double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected String paramsToString() {
        return paramToString("streetLine1", getStreetLine1()) +
                paramToString("streetLine2", getStreetLine2()) +
                paramToString("city", getCity()) +
                paramToString("stateCode", getStateCode()) +
                paramToString("postalCode", getPostalCode()) +
                paramToString("countryCode", getCountryCode()) +
                paramToString("latitude", getLatitude()) +
                paramToString("longitude", getLongitude()) +
                paramToString("radius", getRadius());
    }
}
