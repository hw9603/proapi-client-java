package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.LocationQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Location;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationLookupTest extends EntityLookupTest<Location> {

    @Test
    public void resultShouldContainStreetNumber() throws FindException {
        boolean found = false;
        for(Location result : getResponse().getResults()) {
            if(result.getName().contains("1301")) {
                found = true;
                break;
            }
        }
        assertThat("Should find street number in results", found);
    }

    @Override
    protected Response<Location> performQuery() throws FindException {
        return getClient().findLocations(getQuery());
    }

    protected LocationQuery getQuery() {
        return new LocationQuery("1301 5th Ave", "Ste 1600", "Seattle", "WA", null);
    }
}
