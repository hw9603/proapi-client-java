package com.whitepages.proapi.api.client.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class URICoder {

    static final int noPortSpecified = -1;

    public static URI makeURI(URI serviceRoot, String path, URIQueryParameters queryParams) throws URISyntaxException, UnsupportedEncodingException {
        return makeURI(serviceRoot.getScheme(), serviceRoot.getHost(), serviceRoot.getPort(), path, queryParams);
    }

    public static URI makeURI(String scheme, String host, Integer port, String path, URIQueryParameters queryParams) throws URISyntaxException, UnsupportedEncodingException {
        int portValue = port == null ? noPortSpecified : port;
        String encodedQueryParams = queryParams.encodeQueryString();
        return new URI(scheme, null, host, portValue, path, encodedQueryParams, null);
    }



}
