package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.AuthException;
import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Person;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class AuthErrorTest extends ResponseExceptionTest<Person> {

    protected Response<Person> performQuery() throws FindException {
        Client client = new Client("ffffffffffffffff");
        PersonQuery query = new PersonQuery("John", null, "Smith", null, null, "98101");
        query.setPostalCode("98101");
        return client.findPeople(query);
    }

    @Override
    protected Class<?> getExpectedExceptionClass() {
        return AuthException.class;
    }
}
