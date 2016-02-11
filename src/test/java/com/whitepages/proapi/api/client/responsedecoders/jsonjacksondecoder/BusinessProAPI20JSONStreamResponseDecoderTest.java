package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Business;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessProAPI20JSONStreamResponseDecoderTest {

    static Response<Business> response;
    static Business business;

    @BeforeClass
    public static void setUp() throws ResponseDecoderException {
        response = new ResponseDecoderHelper().getBusinessResponse();
        business = response.getResults().get(0);
    }

    @Test
    public void hasResults() {
        assertThat(response.getResults().size(), is(4));
    }

    @Test
    public void hasName() {
        assertThat(business.getName(), is("Whitepages"));
    }

    @Test
    public void hasLocationAssociations() {
        assertThat(business.getLocationAssociations().size(), is(1));
    }

    @Test
    public void hasPhoneAssociations() {
        assertThat(business.getPhoneAssociations().size(), is(3));
    }

    @Test
    public void hasNoPersonAssociations() {
        assertThat(business.getPersonAssociations(), is(nullValue()));
    }
}
