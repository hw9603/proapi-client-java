package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.BusinessQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessQueryToProAPI20URICoder extends WhereQueryToProAPI20URICoder<BusinessQuery> implements QueryCoder<BusinessQuery, URI> {

    private static final String URI_PATH = "/2.0/business.json";

    private static final String NAME_KEY = "name";

    @Override
    protected URIQueryParameters queryParams(BusinessQuery query, Config config) {
        URIQueryParameters params = getDefaultQueryParams(config);

        if(query != null) {
            addWhereQueryParams(params, query);

            params.put(NAME_KEY, query.getName());
        }

        return params;
    }

    @Override
    protected String getURIPath() {
        return URI_PATH;
    }
}
