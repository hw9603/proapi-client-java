package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.PhoneQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PhoneQueryToProAPI20URICoder extends QueryToProAPI20URICoder<PhoneQuery> implements QueryCoder<PhoneQuery, URI> {

    private static final String URI_PATH = "/2.0/phone.json";

    private static final String PHONE_KEY         = "phone_number";
    private static final String RESPONSE_TYPE_KEY = "response_type";

    @Override
    protected URIQueryParameters queryParams(PhoneQuery query, Config config) {
        URIQueryParameters params = getDefaultQueryParams(config);

        if(query != null) {
            params.put(PHONE_KEY, query.getPhone());
            params.put(RESPONSE_TYPE_KEY, query.getResponseType());
        }

        return params;
    }

    @Override
    protected String getURIPath() {
        return URI_PATH;
    }
}
