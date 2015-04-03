package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.BusinessQuery;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessQueryToProAPI20URICoderTest extends WhereQueryToProAPI2URICoderTest<BusinessQuery> {

    private static final String uriPath = "/2.0/business.json";

    private static final String name = "Bob's Burgers";
    private static final String nameEncoded = "Bob%27s+Burgers";

    @Test
    public void nameParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), containsString("name=" + nameEncoded));
    }

    @Override
    protected BusinessQuery getDefaultQuery() {
        return new BusinessQuery(name);
    }

    @Override
    protected QueryCoder<BusinessQuery, URI> getCoder() {
        return new BusinessQueryToProAPI20URICoder();
    }

    @Override
    protected String getURIPath() {
        return uriPath;
    }
}
