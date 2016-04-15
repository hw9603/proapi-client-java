package com.whitepages.proapi.api.client.util;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class HttpResponse {

    private final int responseCode;

    private final URI uri;

    private final Map<String, List<String>> headerFields;

    private final InputStream body;

    public HttpResponse(int responseCode, URI uri, Map<String, List<String>> headerFields, InputStream body) {
        this.responseCode = responseCode;
        this.uri = uri;
        this.headerFields = headerFields;
        this.body = body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public URI getUri() {
        return uri;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public InputStream getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpResponse{responseCode=" + responseCode + ", uri=" + uri + "}";
    }
}
