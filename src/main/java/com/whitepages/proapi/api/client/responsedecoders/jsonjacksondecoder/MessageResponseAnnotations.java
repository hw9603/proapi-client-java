package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.whitepages.proapi.data.message.Message;

import java.util.List;

/**
 * Copyright 2015 Whitepages, Inc.
 */
class MessageResponseAnnotations {

    @JsonCreator
    MessageResponseAnnotations(List<Message> messages){}

}
