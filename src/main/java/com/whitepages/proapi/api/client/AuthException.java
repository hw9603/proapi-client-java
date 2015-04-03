package com.whitepages.proapi.api.client;

import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class AuthException extends ResponseException {

    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

    @Override
    public Message.MessageType getMessageType() {
        return Message.MessageType.AUTH;
    }
}
