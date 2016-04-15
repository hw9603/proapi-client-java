package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Location;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class ReputationDetailsAnnotations {

		@JsonProperty("score")
		abstract int getScore();
		
		@JsonProperty("type")
		abstract String getType();
		
		@JsonProperty("category")
		abstract String getCategory();
		
}
