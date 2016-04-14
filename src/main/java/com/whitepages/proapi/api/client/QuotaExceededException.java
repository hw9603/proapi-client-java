package com.whitepages.proapi.api.client;

import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class QuotaExceededException extends ResponseException {

    public QuotaExceededException() {
    }

    public QuotaExceededException(String message) {
        super(message);
    }

    public QuotaExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuotaExceededException(Throwable cause) {
        super(cause);
    }

    @Override
    public Message.MessageType getMessageType() {
        return Message.MessageType.QUOTA_EXCEEDED;
    }
}
