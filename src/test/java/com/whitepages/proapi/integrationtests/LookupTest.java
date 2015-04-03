package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class LookupTest<T extends Entity> {

    private Response<T> response;
    private FindException error;
    private boolean performedQuery = false;

    protected abstract Response<T> performQuery() throws FindException;

    protected Response<T> getResponse() throws FindException {
        ensureResponse();

        if(error == null)
            return response;
        else
            throw error;
    }

    protected FindException getError() {
        ensureResponse();
        return error;
    }

    private void ensureResponse() {
        if(!performedQuery) {
            try {
                response = performQuery();
            } catch (FindException e) {
                error = e;
            }
            performedQuery = true;
        }
    }
}
