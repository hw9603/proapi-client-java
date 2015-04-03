package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Phone;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * <p>The {@link com.whitepages.proapi.data.entity.Phone} type specific {@link Association}.</p>
 *
 * <p>This class also adds accessors for the contact type of the location.</p>
 *
 * @see com.whitepages.proapi.data.entity.Phone
 * @see com.whitepages.proapi.data.association.Association
 */
public class PhoneAssociation extends Association implements ContactTyped {

    private ContactType contactType;

    public PhoneAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public PhoneAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate, ContactType contactType) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
        this.contactType = contactType;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public Phone getPhone() {
        return getDictionary().getPhone(getEntityId());
    }

    @Override
    public Entity getEntity() {
        return getPhone();
    }

    @Override
    public EntityId.EntityType getEntityIdType() {
        return EntityId.EntityType.PHONE;
    }
}
