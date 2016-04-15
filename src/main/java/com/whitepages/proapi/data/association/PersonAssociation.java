package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Person;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * The {@link com.whitepages.proapi.data.entity.Person} type specific {@link Association}.
 * @see com.whitepages.proapi.data.entity.Person
 * @see com.whitepages.proapi.data.association.Association
 */
public class PersonAssociation extends LegalEntityAssociation {

    public PersonAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public PersonAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
    }

    public Person getPerson() {
        return getDictionary().getPerson(getEntityId());
    }

    @Override
    public Entity getEntity() {
        return getPerson();
    }

    @Override
    public EntityId.EntityType getEntityIdType() {
        return EntityId.EntityType.PERSON;
    }

}
