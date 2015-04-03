package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.EntityId;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class EntityAnnotations {
    @JsonProperty("id")
    abstract EntityId getId();
}
