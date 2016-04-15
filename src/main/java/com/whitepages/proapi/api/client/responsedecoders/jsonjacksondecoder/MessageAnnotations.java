package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.message.Message;

import java.util.List;
import java.util.Map;

/**
 * Copyright 2015 Whitepages, Inc.
 */
class MessageAnnotations {

    @JsonCreator
    MessageAnnotations(@JsonProperty("severity") Message.Severity severity,
                       @JsonProperty("type") Message.MessageType type,
                       @JsonProperty("code") Message.Code code,
                       @JsonProperty("message") String message,
                       @JsonProperty("ancillary_data") Map<String, Object> ancillaryData) {
    }

}

