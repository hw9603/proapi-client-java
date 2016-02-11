package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;
import com.whitepages.proapi.data.entity.Person;

import java.util.List;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class PersonAnnotations extends EntityAnnotations {

    @JsonProperty("names")
    abstract List<Person.Name> getNames();

    @JsonProperty("age_range")
    abstract Person.AgeRange getAgeRange();

    @JsonProperty("gender")
    abstract Person.Gender getGender();

    @JsonProperty("locations")
    abstract List<LocationAssociation> getLocationAssociations();

    @JsonProperty("phones")
    abstract List<PhoneAssociation> getPhoneAssociations();

    @JsonProperty("best_name")
    abstract String getBestName();

    @JsonProperty("best_location")
    abstract LocationAssociation getBestLocationAssociation();

}
