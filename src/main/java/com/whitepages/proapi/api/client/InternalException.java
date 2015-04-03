package com.whitepages.proapi.api.client;

import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class InternalException extends ResponseException {

    public InternalException() {
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    @Override
    public Message.MessageType getMessageType() {
        return Message.MessageType.INTERNAL;
    }

}
