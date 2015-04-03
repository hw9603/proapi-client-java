package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.message.Message;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class ErrorResponseTest<T extends Entity> extends LookupTest<T> {

    protected abstract Message.MessageType getExpectedMessageType();

    @Override
    protected abstract Response<T> performQuery() throws FindException;

    @Test
    public void itShouldBeFailureResponse() throws FindException {
        assertThat("Should be failure response", getResponse().isFailure());
    }

    @Test
    public void itShouldHaveAppropriateErrorMessage() throws FindException {
        assertThat("Should have one or more error messages", !(getResponse().getResponseMessages().getMessageList(Message.Severity.ERROR).isEmpty()));

        boolean expectedTypeFound = false;
        for( Message message : getResponse().getResponseMessages().getMessageList(Message.Severity.ERROR) ) {
            if(message.getType() == getExpectedMessageType()) {
                expectedTypeFound = true;
                break;
            }
        }

        assertThat("Should find message of type " + getExpectedMessageType().toString(), expectedTypeFound);
    }

    @Test
    public void itShouldHaveNoResults() throws FindException {
        assertThat("Should have no results, found " + getResponse().getResults().size(), getResponse().getResults().isEmpty());
    }

}
