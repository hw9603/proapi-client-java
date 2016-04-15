package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.association.BusinessAssociation;
import com.whitepages.proapi.data.association.PersonAssociation;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.List;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class LocationAnnotations extends EntityAnnotations {
    @JsonProperty("type")
    abstract Location.LocationType getType();

    @JsonProperty("address")
    abstract String getAddress();

    @JsonProperty("standard_address_line1")
    abstract String getStandardAddressLine1();

    @JsonProperty("standard_address_line2")
    abstract String getStandardAddressLine2();

    @JsonProperty("standard_address_location")
    abstract String getStandardAddressLocation();

    @JsonProperty("city")
    abstract String getCity();

    @JsonProperty("postal_code")
    abstract String getPostalCode();

    @JsonProperty("state_code")
    abstract String getStateCode();

    @JsonProperty("country_code")
    abstract String getCountryCode();

    @JsonProperty("apt_type")
    abstract String getAptType();

    @JsonProperty("zip4")
    abstract String getZip4();

    @JsonProperty("house")
    abstract String getHouse();

    @JsonProperty("street_name")
    abstract String getStreetName();

    @JsonProperty("street_type")
    abstract String getStreetType();

    @JsonProperty("pre_dir")
    abstract String getPreDir();

    @JsonProperty("post_dir")
    abstract String getPostDir();

    @JsonProperty("apt_number")
    abstract String getAptNumber();

    @JsonProperty("box_number")
    abstract String getBoxNumber();

    @JsonProperty("valid_for")
    abstract TimePeriod getValidFor();

    @JsonProperty("is_receiving_mail")
    abstract Boolean getReceivingMail();

    @JsonProperty("not_receiving_mail_reason")
    abstract Location.NotReceivingMailReason getNotReceivingMailReason();

    @JsonProperty("usage")
    abstract Location.AddressUsage getUsage();

    @JsonProperty("delivery_point")
    abstract Location.DeliveryPoint getDeliveryPoint();

    @JsonProperty("address_type")
    abstract Location.AddressType getAddressType();

    @JsonProperty("lat_long")
    abstract Location.LatLong getLatLong();

    @JsonProperty("is_deliverable")
    abstract Boolean getDeliverable();

    @JsonProperty("business_associations")
    abstract List<BusinessAssociation> getBusinessAssociations();

    @JsonProperty("people_associations")
    abstract List<PersonAssociation> getPersonAssociations();
}
