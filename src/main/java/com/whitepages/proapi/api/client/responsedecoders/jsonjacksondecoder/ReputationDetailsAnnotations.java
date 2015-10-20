package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Location;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class ReputationDetailsAnnotations {

	ReputationDetailsAnnotations(@JsonProperty("score") int score,
									@JsonProperty("type") String type,
									@JsonProperty("category") String category) {
		
	}
}
