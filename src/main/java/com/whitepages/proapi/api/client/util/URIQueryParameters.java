package com.whitepages.proapi.api.client.util;

import com.whitepages.proapi.api.query.PhoneQuery;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class URIQueryParameters {

    private static final String SEPARATOR = "&";
    private static final String KEY_VALUE_SEPARATOR = "=";

    private final Map<String, String> params;
    private final ParamValueFormatter paramValueFormatter;

    public URIQueryParameters(ParamValueFormatter paramValueFormatter) {
        this.paramValueFormatter = paramValueFormatter;
        this.params = new TreeMap<>();
    }

    public void put(String key, String value) {
        params.put(key, getParamValueFormatter().format(value));
    }

    public void put(String key, Double value) {
        params.put(key, getParamValueFormatter().format(value));
    }

    public void put(String key, Boolean value) {
        params.put(key, getParamValueFormatter().format(value));
    }

    public void put(String key, PhoneQuery.ResponseType value) {
        params.put(key, getParamValueFormatter().format(value));
    }

    protected final ParamValueFormatter getParamValueFormatter() {
        return paramValueFormatter;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        String s = null;
        try {
            s = encodeQueryString();
        } catch (UnsupportedEncodingException e) {
            s = "UnsupportedEncodingException";
        }
        return "QueryParameters(" + s + ")";
    }

    public String encodeQueryString() throws UnsupportedEncodingException {
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        StringBuilder builder = new StringBuilder();

        boolean firstElement = true;
        while(it.hasNext()) {
            String queryString = encodeQueryString(it.next());

            if( queryString != null ) {
                if(!firstElement)
                    builder.append(SEPARATOR);
                else
                    firstElement = false;

                builder.append(queryString);
            }
        }

        return builder.toString();
    }

    private String encodeQueryString(Map.Entry<String, String> entry) throws UnsupportedEncodingException {
        return encodeQueryString(entry.getKey(), entry.getValue());
    }

    private String encodeQueryString(String key, String value) throws UnsupportedEncodingException {
        if( value == null || key == null )
            return null;
        else
            return URLEncoder.encode(key, "UTF-8") + KEY_VALUE_SEPARATOR + URLEncoder.encode(value, "UTF-8");
    }

    /**
     * Interface for parameter value formatters.
     * This is an open/close interface, meaning that as new types have to be encoded into
     * the URL parameters, new format methods will be defined here.
     */
    public interface ParamValueFormatter {
        public String format(String s);
        public String format(Double d);
        public String format(Boolean b);
        public String format(Object o);
        public String format(PhoneQuery.ResponseType responseType);
    }
}
