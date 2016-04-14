package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.message.Message;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class EntityLookupTest<T extends Entity> extends LookupTest<T> {

    @Test
    public void resultShouldNotBeEmpty() throws FindException {
        assertThat("Should contain results", !(getResponse().getResults().isEmpty()));
    }

    @Test
    public void successShouldBeTrue() throws FindException {
        assertThat("Should be successful", getResponse().isSuccess());
    }

    @Test
    public void resultShouldHaveNoErrorMessages() throws FindException {
        assertThat("Should Have No Error Messages", getResponse().getResponseMessages().getMessageList(Message.Severity.ERROR).isEmpty());
    }

    @Override
    protected abstract Response<T> performQuery() throws FindException;

    protected static Client getClient() {
        return new Client(ClientIntegrationTestHelper.getApiKey());
    }
}
