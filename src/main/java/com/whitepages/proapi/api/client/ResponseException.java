package com.whitepages.proapi.api.client;

import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class ResponseException extends ResponseDecoderException {

    public ResponseException() {
    }

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }

    public Message.MessageType getMessageType() {
        return Message.MessageType.UNKNOWN;
    }

}
