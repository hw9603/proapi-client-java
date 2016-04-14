package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.util.TimePeriod;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonNameAnnotations {

    PersonNameAnnotations(@JsonProperty("salutation") String salutation,
                          @JsonProperty("first_name") String firstName,
                          @JsonProperty("middle_name") String middleName,
                          @JsonProperty("last_name") String lastName,
                          @JsonProperty("suffix") String suffix) {}

}
