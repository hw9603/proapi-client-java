package com.whitepages.proapi.data.association;

import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.entity.*;

import java.util.UUID;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class TestDictionaryFactory {

    static final EntityId personId = new EntityId(EntityId.EntityType.PERSON, UUID.randomUUID(), EntityId.Durability.EPHEMERAL);
    static final EntityId businessId = new EntityId(EntityId.EntityType.BUSINESS, UUID.randomUUID(), EntityId.Durability.EPHEMERAL);
    static final EntityId locationId = new EntityId(EntityId.EntityType.LOCATION, UUID.randomUUID(), EntityId.Durability.EPHEMERAL);
    static final EntityId phoneId = new EntityId(EntityId.EntityType.PHONE, UUID.randomUUID(), EntityId.Durability.EPHEMERAL);

    /**
     * For association tests, we don't need wellformed entries, just stub entries
     * that we can test get associated to.
     */
    static ResponseDictionary getSimpleDicationary() {
        ResponseDictionary dict = new ResponseDictionary(null);
        dict.add(new PersonImpl(personId));
        dict.add(new BusinessImpl(businessId));
        dict.add(new LocationImpl(locationId));
        dict.add(new PhoneImpl(phoneId));
        return dict;
    }

}
