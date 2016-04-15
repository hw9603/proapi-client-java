package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitepages.proapi.data.entity.Location;

/**
 * Copyright 2015 Whitepages, Inc.
 */
abstract class LatLongAnnotations {

    LatLongAnnotations(@JsonProperty("latitude") double latitude,
                       @JsonProperty("longitude") double longitude,
                       @JsonProperty("accuracy") Location.LatLong.GeoAccuracy geoAccuracy){}
}
