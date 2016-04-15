package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Phone;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class UrlAnnotations {

    @JsonCreator
    UrlAnnotations(@JsonProperty("url") String url,
								@JsonProperty("display_name") String displayName,
								@JsonProperty("type") String type){};
    
    

}
