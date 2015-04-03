package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.util.URIQueryParameters;
import com.whitepages.proapi.api.query.PhoneQuery;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class ProAPI20ParamValueFormatter implements URIQueryParameters.ParamValueFormatter {

    private static final String RESPONSE_TYPE_REGULAR_VALUE = null;
    private static final String RESPONSE_TYPE_LITE_VALUE = "lite";
    private static final String RESPONSE_TYPE_CALLER_ID_VALUE = "caller_id";

    public ProAPI20ParamValueFormatter() {
    }

    public String format(String s) {
        return s;
    }

    public String format(Double d) {
        if( d == null || Double.isNaN(d) || Double.isInfinite(d) )
            return null;
        else
            return String.format("%.6f", d);
    }

    public String format(Boolean b) {
        if(b == null)
            return null;
        else
            return b ? "true" : "false";
    }

    public String format(PhoneQuery.ResponseType responseType) {
        if(responseType == null)
            return null;

        switch(responseType) {
            case REGULAR:
                return RESPONSE_TYPE_REGULAR_VALUE;
            case LITE:
                return RESPONSE_TYPE_LITE_VALUE;
            case CALLER_ID:
                return RESPONSE_TYPE_CALLER_ID_VALUE;
            default:
                return null;
        }
    }

    public String format(Object obj) {
        if(obj == null) {
            return null;
        } else {
            throw new RuntimeException("Cannot format unknown object " + obj.getClass().getName() + " as query parameter value: " + obj.toString());
        }
    }

}
