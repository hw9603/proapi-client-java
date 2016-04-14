package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * Common superclass of {@link PhoneAssociation} and {@link BusinessAssociation}; useful
 * for Liskov substitution of these entities.
 * @see com.whitepages.proapi.data.entity.LegalEntity
 * @see com.whitepages.proapi.data.association.Association
 */
public abstract class LegalEntityAssociation extends Association {

    public LegalEntityAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public LegalEntityAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
    }
}
