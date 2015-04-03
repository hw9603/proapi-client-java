package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.association.Association;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */

public class EntityImplTest {

    private static final EntityId entityId = new EntityId(EntityId.EntityType.PERSON, UUID.randomUUID(), EntityId.Durability.DURABLE);
    private static final EntityId associatedPhoneId = new EntityId(EntityId.EntityType.PHONE, UUID.randomUUID(), EntityId.Durability.DURABLE);

    private EntityImpl entity;
    private Phone associatedPhone;

    @Before
    public void makeEntity() {
        associatedPhone = new PhoneImpl(associatedPhoneId);

        entity = new PersonImpl(entityId);

        entity.setBusinessAssociations(null);
        entity.setLocationAssociations(new ArrayList<LocationAssociation>());
        entity.setPhoneAssociations(new ArrayList<PhoneAssociation>() {{
            ResponseDictionary dict = new ResponseDictionary(null);
            dict.add(associatedPhone);
            add(new PhoneAssociation(associatedPhoneId, dict));
        }});
    }

    @Test
    public void itShouldWorkWithAssociations() {
        List<PhoneAssociation> assocs = entity.getPhoneAssociations();
        assertThat(assocs, is(size(1)));
        assertThat(assocs.get(0).getEntityId(), is(associatedPhoneId));
    }

    @Test
    public void itShouldAllowEmptyAssociations() {
        assertThat(entity.getLocationAssociations(), is(size(0)));
    }

    @Test
    public void itShouldAllowNullAssociations() {
        assertThat(entity.getBusinessAssociations(), is(nullValue()));
    }

    @Test
    public void itShouldResolveAssociations() {
        List<Phone> phones = entity.getPhones();
        assertThat(phones, is(size(1)));
        assertThat(phones.get(0).getId(), is(associatedPhoneId));
    }

    @Test
    public void itShouldResolveEmptyAssociations() {
        assertThat(entity.getLocationAssociations(), is(size(0)));
    }

    @Test
    public void itShouldResolveNullAssociations() {
        assertThat(entity.getBusinessAssociations(), is(nullValue()));
    }

    @Test
    public void itShouldAggregateNullAndEmptyAssociations() {
        List<Association> assocs = entity.getAssociations();
        assertThat(assocs, is(size(1)));
        assertThat(assocs.get(0).getEntityId(), is(associatedPhoneId));
    }

    @Test
    public void itShouldAggregateResolvedNullAndEmptyAssociations() {
        List<Entity> entities = entity.getEntities();
        assertThat(entities, is(size(1)));
        assertThat(entities.get(0).getId(), is(associatedPhoneId));
    }

    private static Matcher<Collection<?>> size(final int size) {
        return new BaseMatcher<Collection<?>>() {
            int expectedSize = size;

            @Override
            public boolean matches(Object item) {
                if( item instanceof Collection )
                    return ((Collection) item).size() == expectedSize;
                else
                    throw new RuntimeException("Cannot determine size of non-collection: " + item.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("size " + size);
            }
        };
    }


}
