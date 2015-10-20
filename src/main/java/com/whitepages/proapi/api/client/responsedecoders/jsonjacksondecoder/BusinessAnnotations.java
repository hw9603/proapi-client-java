package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;
import com.whitepages.proapi.data.entity.Business;

import java.util.List;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class BusinessAnnotations extends EntityAnnotations {

    @JsonProperty("name")
    abstract String getName();

    @JsonProperty("locations")
    abstract List<LocationAssociation> getLocationAssociations();

    @JsonProperty("phones")
    abstract List<PhoneAssociation> getPhoneAssociations();

    @JsonProperty("urls")
    abstract List<Business.Url> getUrls();
}
