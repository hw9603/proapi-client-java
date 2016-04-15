package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.client.util.URICoder;
import com.whitepages.proapi.api.query.EntityQuery;
import com.whitepages.proapi.data.entity.EntityId;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class QueryToProAPI20URICoder<Q extends EntityQuery> implements QueryCoder<Q, URI> {

    private static final String API_KEY_KEY = "api_key";

    private static final String ENTITY_URI_PATH_ROOT = "/2.0/entity";
    private static final String TYPE_SUFFIX_JSON = ".json";

    @Override
    public URI encode(Q query, Client client) throws QueryCoderException {
        if (client == null)
            throw new QueryCoderException("No client object given");
        else if (client.getConfig() == null)
            throw new QueryCoderException("No configuration object found on client");
        else if (client.getConfig().getApiKey() == null)
            throw new QueryCoderException("No API Key configured on client");
        else
            return makeURI(query, client.getConfig());
    }

    protected abstract URIQueryParameters queryParams(Q query, Config config);

    protected abstract String getURIPath();

    protected URIQueryParameters getDefaultQueryParams(Config config) {
        URIQueryParameters params = new URIQueryParameters(new ProAPI20ParamValueFormatter());

        if(config != null) {
            params.put(API_KEY_KEY, config.getApiKey());
        }

        return params;
    }

    protected URI makeURI(Q query, Config config) throws QueryCoderException {
        try {
            EntityId id = (query == null) ? null : query.getId();
            return (id == null) ? findEntitiesURI(query, config) : findByIdURI(query, config);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new QueryCoderException(e);
        }
    }

    protected URI findEntitiesURI(Q query, Config config) throws UnsupportedEncodingException, URISyntaxException {
        URI serviceRoot = config.getServiceRoot();
        String uriPath = getURIPath();
        URIQueryParameters queryParams = queryParams(query, config);
        return URICoder.makeURI(serviceRoot, uriPath, queryParams);
    }

    protected URI findByIdURI(Q query, Config config) throws UnsupportedEncodingException, URISyntaxException {
        URI serviceRoot = config.getServiceRoot();
        String entityURIPath = ENTITY_URI_PATH_ROOT + "/" + query.getId().toString() + TYPE_SUFFIX_JSON ;
        URIQueryParameters queryParams = getDefaultQueryParams(config);
        return URICoder.makeURI(serviceRoot, entityURIPath, queryParams);
    }
}
