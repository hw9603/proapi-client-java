package com.whitepages.proapi.api.client;

/**
* Copyright 2015 Whitepages, Inc.
*/
public class ResponseDecoderException extends FindException {
    public ResponseDecoderException() {
    }

    public ResponseDecoderException(String message) {
        super(message);
    }

    public ResponseDecoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseDecoderException(Throwable cause) {
        super(cause);
    }
}
