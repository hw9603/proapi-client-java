package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PhoneQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Phone;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PhoneLookupTest extends EntityLookupTest<Phone> {

    @Test
    public void resultShouldContainPhoneNumber() throws FindException {
        boolean found = false;
        for(Phone result : getResponse().getResults()) {
            if(result.getPhoneNumber().contains("2069735100")) {
                found = true;
                break;
            }
        }
        assertThat("Should find 2069735100 in results", found);
    }

    @Override
    protected Response<Phone> performQuery() throws FindException {
        return getClient().findPhones(getQuery());
    }

    protected PhoneQuery getQuery() {
        return new PhoneQuery("2069735100");
    }
}
