package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.WhereQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class WhereQueryToProAPI20URICoder<Q extends WhereQuery> extends QueryToProAPI20URICoder<Q> implements QueryCoder<Q, URI> {

    private static final String STREET_LINE_1_KEY = "street_line_1";
    private static final String STREET_LINE_2_KEY = "street_line_2";
    private static final String CITY_KEY          = "city";
    private static final String STATE_CODE_KEY    = "state_code";
    private static final String POSTAL_CODE_KEY   = "postal_code";
    private static final String COUNTRY_CODE_KEY  = "country_code";
    private static final String LATITUDE_KEY      = "lat";
    private static final String LONGITUDE_KEY     = "lon";
    private static final String RADIUS_KEY        = "radius";

    protected void addWhereQueryParams(URIQueryParameters params, WhereQuery query) {
        if (query != null) {
            params.put(STREET_LINE_1_KEY, query.getStreetLine1());
            params.put(STREET_LINE_2_KEY, query.getStreetLine2());
            params.put(CITY_KEY, query.getCity());
            params.put(STATE_CODE_KEY, query.getStateCode());
            params.put(POSTAL_CODE_KEY, query.getPostalCode());
            params.put(COUNTRY_CODE_KEY, query.getCountryCode());
            params.put(LATITUDE_KEY, query.getLatitude());
            params.put(LONGITUDE_KEY, query.getLongitude());
            params.put(RADIUS_KEY, query.getRadius());
        }
    }

}
