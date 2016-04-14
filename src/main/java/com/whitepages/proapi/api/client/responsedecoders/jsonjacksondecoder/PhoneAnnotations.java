package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.association.BusinessAssociation;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PersonAssociation;
import com.whitepages.proapi.data.entity.Phone;

import java.util.List;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class PhoneAnnotations extends EntityAnnotations {
    @JsonProperty("phone_number")
    abstract String getPhoneNumber();

    @JsonProperty("line_type")
    abstract Phone.LineType getLineType();

    @JsonProperty("business_associations")
    abstract List<BusinessAssociation> getBusinessAssociations();

    @JsonProperty("people_associations")
    abstract List<PersonAssociation> getPersonAssociations();

    @JsonProperty("country_calling_code")
    abstract String getCountryCallingCode();

    @JsonProperty("extension")
    abstract String getExtension();

    @JsonProperty("carrier")
    abstract String getCarrier();

    @JsonProperty("reputation")
    abstract Phone.Reputation getReputation();

    @JsonProperty("do_not_call")
    abstract Boolean getDoNotCall();

    @JsonProperty("is_prepaid")
    abstract Boolean getPrepaid();

    @JsonProperty("is_valid")
    abstract Boolean getValid();

    @JsonProperty("is_connected")
    abstract Boolean getConnected();
    
    @JsonProperty("best_location")
    abstract LocationAssociation getBestLocationAssociation();

    @JsonProperty("associated_locations")
    abstract List<LocationAssociation> getLocationAssociations();

}
