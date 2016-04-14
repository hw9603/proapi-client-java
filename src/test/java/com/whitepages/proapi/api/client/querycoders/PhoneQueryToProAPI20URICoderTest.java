package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.PhoneLiteQuery;
import com.whitepages.proapi.api.query.PhoneQuery;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PhoneQueryToProAPI20URICoderTest extends QueryToProAPI2URICoderTest<PhoneQuery> {

    private static final String number = "7075551234";

    private static final String uriPath = "/2.0/phone.json";

    @Test
    public void phonePropertyPresence() throws QueryCoderException {
        PhoneQuery query = new PhoneQuery(number);
        URI uri = getCoder().encode(query, getClient());
        assertThat(uri.getQuery(), containsString("phone_number=" + number));
    }

    @Test
    public void responsePropertyDefault() throws QueryCoderException {
        PhoneQuery query = new PhoneQuery(number);
        URI uri = getCoder().encode(query, getClient());
        assertThat(uri.getQuery(), not(containsString("response_type=")));
    }

    @Test
    public void responsePropertyLite() throws QueryCoderException {
        PhoneQuery query = new PhoneLiteQuery(number);
        URI uri = getCoder().encode(query, getClient());
        assertThat(uri.getQuery(), containsString("response_type=lite"));
    }

    @Override
    protected PhoneQuery getDefaultQuery() {
        return new PhoneQuery(number);
    }

    @Override
    protected QueryCoder<PhoneQuery, URI> getCoder() {
        return new PhoneQueryToProAPI20URICoder();
    }

    @Override
    protected String getURIPath() {
        return uriPath;
    }
}
