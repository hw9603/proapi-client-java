package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class PhoneReputationAnnotations {

    PhoneReputationAnnotations(@JsonProperty("spam_score") int spamScore){};

}
