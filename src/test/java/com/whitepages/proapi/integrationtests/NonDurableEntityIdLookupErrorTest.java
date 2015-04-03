package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Person;
import com.whitepages.proapi.data.message.Message;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class NonDurableEntityIdLookupErrorTest extends ErrorResponseTest<Person> {

    @Override
    protected Message.MessageType getExpectedMessageType() {
        return Message.MessageType.NON_DURABLE_ENTITY_ID_LOOKUP;
    }

    @Override
    protected Response<Person> performQuery() throws FindException {
        Client client = new Client(ClientIntegrationTestHelper.getApiKey());
        PersonQuery query = new PersonQuery(EntityId.fromString("Person.3a366a38-d82c-4e0d-a206-12242a881953.Ephemeral"));
        return client.findPeople(query);
    }
}
