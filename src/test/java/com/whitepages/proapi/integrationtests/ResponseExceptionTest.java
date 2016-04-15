package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class ResponseExceptionTest<T extends Entity> extends LookupTest<T> {

    protected abstract Class<?> getExpectedExceptionClass();

    @Override
    protected abstract Response<T> performQuery() throws FindException;

    @Test
    public void expectedErrorShouldBeThrown() {
        assertThat(getError(), instanceOf(getExpectedExceptionClass()));
    }


}
