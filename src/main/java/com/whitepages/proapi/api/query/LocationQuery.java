package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The root concrete class for all Location queries.
 */
public class LocationQuery extends WhereQuery implements Query {

    public LocationQuery() {
    }

    public LocationQuery(EntityId id) {
        super(id);
    }

    public LocationQuery(String streetLine1, String streetLine2, String city, String stateCode, String postalCode) {
        super(streetLine1, streetLine2, city, stateCode, postalCode);
    }

    public LocationQuery(Double latitude, Double longitude, Double radius) {
        super(latitude, longitude, radius);
    }

}
