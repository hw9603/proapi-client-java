package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Phone;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class PhoneReputationAnnotations {

    @JsonCreator
    PhoneReputationAnnotations(@JsonProperty("level") int level,
								@JsonProperty("spam_score") int spamScore,
								@JsonProperty("spam_index") int spamIndex,
								@JsonProperty("volume_score") int volumeScore,
								@JsonProperty("report_count") int reportCount){};
								
	@JsonProperty("details") 
	abstract Phone.ReputationDetails getDetails();

}
