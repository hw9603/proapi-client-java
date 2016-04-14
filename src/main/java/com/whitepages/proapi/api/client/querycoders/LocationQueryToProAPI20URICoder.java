package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.LocationQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationQueryToProAPI20URICoder extends WhereQueryToProAPI20URICoder<LocationQuery> implements QueryCoder<LocationQuery, URI> {

    private static final String URI_PATH = "/2.0/location.json";

    @Override
    protected URIQueryParameters queryParams(LocationQuery query, Config config) {
        URIQueryParameters params = getDefaultQueryParams(config);

        if(query != null) {
            addWhereQueryParams(params, query);
        }

        return params;
    }

    @Override
    protected String getURIPath() {
        return URI_PATH;
    }
}
