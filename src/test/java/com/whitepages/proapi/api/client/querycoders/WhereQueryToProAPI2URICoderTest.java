package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.WhereQuery;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class WhereQueryToProAPI2URICoderTest<Q extends WhereQuery> extends QueryToProAPI2URICoderTest<Q> {

    private static final String streetLine1 = "76 Totter's Lane";
    private static final String streetLine1Encoded = "76+Totter%27s+Lane";
    private static final String streetLine2 = "Ste 2";
    private static final String streetLine2Encoded = "Ste+2";
    private static final String city = "Sebastopol";
    private static final String stateCode = "CA";
    private static final String postalCode = "95472";
    private static final String countryCode = "USA";

    private static final String latitude = "47.7233526";
    private static final String latitudeEncoded = "47.723353";
    private static final String longitude = "-122.1943964";
    private static final String longitudeEncoded = "-122.194396";
    private static final String radius = "1";
    private static final String radiusEncoded = "1.000000";

    @Test
    public void streetLine1Parameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), containsString("street_line_1=" + streetLine1Encoded));
    }

    @Test
    public void streetLine2Parameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), containsString("street_line_2=" + streetLine2Encoded));
    }

    @Test
    public void cityParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), containsString("city=" + city));
    }

    @Test
    public void stateCodeParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), containsString("state_code=" + stateCode));
    }

    @Test
    public void postalCodeParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), containsString("postal_code=" + postalCode));
    }

    @Test
    public void noNearbyParametersOnLocation() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultLocationQuery(), getClient());
        assertThat(uri.getQuery(), not(containsString("lat")));
        assertThat(uri.getQuery(), not(containsString("lon")));
        assertThat(uri.getQuery(), not(containsString("radius")));
    }

    @Test
    public void latitudeParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultNearbyQuery(), getClient());
        assertThat(uri.getQuery(), containsString("lat=" + latitudeEncoded));
    }

    @Test
    public void longitudeParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultNearbyQuery(), getClient());
        assertThat(uri.getQuery(), containsString("lon=" + longitudeEncoded));
    }

    @Test
    public void radiusParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultNearbyQuery(), getClient());
        assertThat(uri.getQuery(), containsString("radius=" + radiusEncoded));
    }

    @Test
    public void noLocationParametersOnNearby() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultNearbyQuery(), getClient());
        assertThat(uri.getQuery(), not(containsString("address_line_1")));
        assertThat(uri.getQuery(), not(containsString("address_line_2")));
        assertThat(uri.getQuery(), not(containsString("city")));
        assertThat(uri.getQuery(), not(containsString("postal_code")));
        assertThat(uri.getQuery(), not(containsString("state_code")));
        assertThat(uri.getQuery(), not(containsString("country_code")));
    }

    private final Q getDefaultLocationQuery() {
        Q query = getDefaultQuery();
        query.setStreetLine1(streetLine1);
        query.setStreetLine2(streetLine2);
        query.setCity(city);
        query.setStateCode(stateCode);
        query.setPostalCode(postalCode);
        query.setCountryCode(countryCode);
        return query;
    }

    private final Q getDefaultNearbyQuery() {
        Q query = getDefaultQuery();
        query.setLatitude(Double.parseDouble(latitude));
        query.setLongitude(Double.parseDouble(longitude));
        query.setRadius(Double.parseDouble(radius));
        return query;
    }

}
