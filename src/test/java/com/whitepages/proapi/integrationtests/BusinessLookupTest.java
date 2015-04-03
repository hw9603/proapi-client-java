package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.BusinessQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Business;
import com.whitepages.proapi.data.entity.Entity;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessLookupTest extends EntityLookupTest<Business>{

    @Test
    public void resultShouldContainBusinessName() throws FindException {
        boolean found = false;
        for(Entity result : getResponse().getResults()) {
            if(result.getName().contains("Whitepages")) {
                found = true;
                break;
            }
        }
        assertThat("Should find business name in results", found);
    }

    @Override
    protected Response<Business> performQuery() throws FindException {
        return getClient().findBusinesses(getQuery());
    }

    protected BusinessQuery getQuery() {
        BusinessQuery q = new BusinessQuery("Whitepages");
        q.setCity("Seattle");
        q.setStateCode("WA");
        return q;
    }
}
