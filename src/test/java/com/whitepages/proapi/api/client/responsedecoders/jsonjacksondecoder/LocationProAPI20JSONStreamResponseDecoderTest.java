package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Location;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationProAPI20JSONStreamResponseDecoderTest {
    static Response<Location> response;
    static Location location;

    @BeforeClass
    public static void setUp() throws ResponseDecoderException {
        response = new ResponseDecoderHelper().getLocationResponse();
        location = response.getResults().get(0);
    }

    @Test
    public void hasResults() {
        assertThat(response.getResults().size(), is(1));
    }

    @Test
    public void hasNoLocationAssociations() {
        assertThat(location.getLocationAssociations(), is(nullValue()));
    }

    @Test
    public void hasNoPeopleAssociations() {
        assertThat(location.getPersonAssociations(), is(nullValue()));
    }

    @Test
    public void hasNoBusinessAssociations() {
        assertThat(location.getBusinessAssociations(), is(nullValue()));
    }

    @Test
    public void hasNoPhoneAssociations() {
        assertThat(location.getPhoneAssociations(), is(nullValue()));
    }

    @Test
    public void hasDeliverable() {
        assert(location.getDeliverable());
    }

    @Test
    public void hasDeliveryPoint() {
        assertThat(location.getDeliveryPoint(), is(Location.DeliveryPoint.MULTI_UNIT));
    }

    @Test
    public void hasLatitude() {
        assertEquals(47.608624, location.getLatLong().getLatitude(), 0.000001);
    }

    @Test
    public void hasLongitude() {
        assertEquals(-122.334442, location.getLatLong().getLongitude(), 0.000001);
    }

    @Test
    public void hasGeoAccuracy() {
        assertThat(location.getLatLong().getGeoAccuracy(), is(Location.LatLong.GeoAccuracy.ROOF_TOP));
    }
}
