package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.util.TimePeriod;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonAgeRangeAnnotations {
    PersonAgeRangeAnnotations(@JsonProperty("start") int start,
                              @JsonProperty("end") int end) {}
}
