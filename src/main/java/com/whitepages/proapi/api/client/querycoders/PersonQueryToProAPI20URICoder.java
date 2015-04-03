package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.PersonQuery;

import java.net.URI;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonQueryToProAPI20URICoder extends WhereQueryToProAPI20URICoder<PersonQuery> implements QueryCoder<PersonQuery, URI> {

    private static final String URI_PATH = "/2.0/person.json";

    private static final String NAME_KEY           = "name";
    private static final String FIRST_NAME_KEY     = "first_name";
    private static final String MIDDLE_NAME_KEY    = "middle_name";
    private static final String LAST_NAME_KEY      = "last_name";
    private static final String SUFFIX_KEY         = "suffix";
    private static final String TITLE_KEY          = "title";
    private static final String USE_HISTORICAL_KEY = "use_historical";
    private static final String USE_METRO_KEY      = "use_metro";

    @Override
    protected URIQueryParameters queryParams(PersonQuery query, Config config) {
        URIQueryParameters params = getDefaultQueryParams(config);

        if (query != null) {
            addWhereQueryParams(params, query);

            params.put(NAME_KEY, query.getName());
            params.put(FIRST_NAME_KEY, query.getFirstName());
            params.put(MIDDLE_NAME_KEY, query.getMiddleName());
            params.put(LAST_NAME_KEY, query.getLastName());
            params.put(SUFFIX_KEY, query.getSuffix());
            params.put(TITLE_KEY, query.getTitle());
            params.put(USE_HISTORICAL_KEY, query.getUseHistorical());
            params.put(USE_METRO_KEY, query.getUseMetro());
        }

        return params;
    }

    @Override
    protected String getURIPath() {
        return URI_PATH;
    }
}
