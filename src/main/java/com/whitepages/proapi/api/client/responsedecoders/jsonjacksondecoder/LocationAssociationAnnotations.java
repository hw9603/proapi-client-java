package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.association.ContactTyped;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class LocationAssociationAnnotations {

    LocationAssociationAnnotations(@JsonProperty("id") EntityId entityId,
                                   @JacksonInject ResponseDictionary dictionary,
                                   @JsonProperty("valid_for") TimePeriod validFor,
                                   @JsonProperty("is_historical") Boolean historical,
                                   @JsonDeserialize(using = ProAPI20JSONStreamResponseDecoder.UnixTimestampDeserializer.class)
                                   @JsonProperty("contact_creation_date") Date contactCreationDate,
                                   @JsonProperty("contact_type") ContactTyped.ContactType contactType) {
    }

}
