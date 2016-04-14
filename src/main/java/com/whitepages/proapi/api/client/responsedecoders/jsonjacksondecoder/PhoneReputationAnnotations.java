package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Phone;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class PhoneReputationAnnotations {

	@JsonProperty("level")
	abstract int getLevel();

	@JsonProperty("volume_score")
	abstract int getVolumeScore();

	@JsonProperty("report_count")
	abstract int getReportCount();

	@JsonProperty("details")
	abstract List<Phone.ReputationDetails> getDetails();

}
