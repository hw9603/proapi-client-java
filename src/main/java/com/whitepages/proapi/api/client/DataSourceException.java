package com.whitepages.proapi.api.client;

/**
* Copyright 2015 Whitepages, Inc.
*/
public class DataSourceException extends FindException {
    public DataSourceException() {
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }
}
