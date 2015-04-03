package com.whitepages.proapi.api.client;

/**
* Copyright 2015 Whitepages, Inc.
*/
public class QueryCoderException extends FindException {
    public QueryCoderException() {
    }

    public QueryCoderException(String message) {
        super(message);
    }

    public QueryCoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryCoderException(Throwable cause) {
        super(cause);
    }
}
