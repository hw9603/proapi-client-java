package com.whitepages.proapi.api.client;

import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class TimeoutException extends ResponseException {

    public TimeoutException() {
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }

    @Override
    public Message.MessageType getMessageType() {
        return Message.MessageType.TIMEOUT;
    }
}
