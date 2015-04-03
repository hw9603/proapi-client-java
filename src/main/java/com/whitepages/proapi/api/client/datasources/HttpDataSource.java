package com.whitepages.proapi.api.client.datasources;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.DataSource;
import com.whitepages.proapi.api.client.DataSourceException;
import com.whitepages.proapi.api.client.util.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class HttpDataSource implements DataSource<URI, HttpResponse> {
    @Override
    public HttpResponse execute(URI uri, Client client) throws DataSourceException {
        try {
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            Map<String, List<String>> headers = conn.getHeaderFields();
            InputStream body = (responseCode == HttpURLConnection.HTTP_OK) ? conn.getInputStream() : conn.getErrorStream();

            return new HttpResponse(responseCode, uri, headers, body);
        } catch (IOException e) {
            throw new DataSourceException(e);
        }
    }
}
