package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.Query;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class QueryToProAPI2URICoderTest<Q extends Query> {

    private static final String apiKey = "ae9";

    @Test
    public void nullQuery() throws QueryCoderException {
        URI uri = getCoder().encode(null, getClient());
        assertThat(uri, notNullValue());
        assertThat(uri.toString(), containsString(getServiceRoot().toString()));
        assertThat(uri.toString(), containsString("api_key=" + getAPIKey()));
    }

    @Test(expected=QueryCoderException.class)
    public void nullClient() throws QueryCoderException {
        getCoder().encode(getDefaultQuery(), null);
    }

    @Test(expected=QueryCoderException.class)
    public void nullConfig() throws QueryCoderException {
        getCoder().encode(getDefaultQuery(), new Client((Config) null));
    }

    @Test
    public void nullAPIKey() {
        String errorMessage = null;

        try {
            getCoder().encode(getDefaultQuery(), new Client((String) null));
        } catch (QueryCoderException e) {
            errorMessage = e.getMessage();
        }

        assertThat(errorMessage, containsString("No API Key"));
    }

    @Test
    public void uriPath() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getPath(), equalTo(getURIPath()));
    }

    @Test
    public void apiKeyPropertyPresence() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), containsString("api_key=" + getAPIKey()));
    }

    protected abstract Q getDefaultQuery();

    protected abstract QueryCoder<Q, URI> getCoder();

    protected abstract String getURIPath();

    protected URI getServiceRoot() {
        try {
            return new URI("http://localhost:8907");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected Config getConfig() {
        Config config = new Config(getAPIKey());
        config.setServiceRoot(getServiceRoot());
        return config;
    }

    protected Client getClient() {
        return new Client(getConfig());
    }

    private String getAPIKey() {
        return apiKey;
    }

}
