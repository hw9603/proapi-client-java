package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.query.LocationQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationQueryToProAPI20URICoderTest extends WhereQueryToProAPI2URICoderTest<LocationQuery> {

    private static final String uriPath = "/2.0/location.json";

    @Override
    protected LocationQuery getDefaultQuery() {
        return new LocationQuery();
    }

    @Override
    protected QueryCoder<LocationQuery, URI> getCoder() {
        return new LocationQueryToProAPI20URICoder();
    }

    @Override
    protected String getURIPath() {
        return uriPath;
    }
}
