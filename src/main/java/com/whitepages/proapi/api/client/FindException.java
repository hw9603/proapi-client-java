package com.whitepages.proapi.api.client;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class FindException extends Exception {
    public FindException() {
    }

    public FindException(String message) {
        super(message);
    }

    public FindException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindException(Throwable cause) {
        super(cause);
    }
}
