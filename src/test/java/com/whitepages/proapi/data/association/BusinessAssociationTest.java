package com.whitepages.proapi.data.association;

import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessAssociationTest {

    @Test
    public void entityResolution() {
        BusinessAssociation assoc = getDefaultAssociation();
        Entity entity = assoc.getEntity();
        assertThat(entity, notNullValue());
        MatcherAssert.assertThat(entity.getId(), equalTo(TestDictionaryFactory.businessId));
        assertThat(assoc.getBusiness(), is(entity));
    }

    @Test(expected = IllegalArgumentException.class)
    public void entityIdTypeContraint() {
        EntityId id = new EntityId(EntityId.EntityType.PHONE, UUID.randomUUID().toString());
        new BusinessAssociation(id, new ResponseDictionary(null));
    }

    protected BusinessAssociation getDefaultAssociation() {
        BusinessAssociation assoc = new BusinessAssociation(TestDictionaryFactory.businessId, TestDictionaryFactory.getSimpleDicationary());
        //NOTE: Fill-in more properties as needed.
        return assoc;
    }

}
