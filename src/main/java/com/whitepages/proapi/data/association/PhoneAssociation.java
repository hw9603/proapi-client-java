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
public class PhoneAssociation extends Association implements HistoricalAssociation {

    public PhoneAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public PhoneAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
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
    
    @Override
    public TimePeriod getValidFor() {
        return super.getValidFor();
    }

    @Override
    public void setValidFor(TimePeriod validFor) {
        super.setValidFor(validFor);
    }

    @Override
    public Boolean getHistorical() {
        return super.getHistorical();
    }

    @Override
    public void setHistorical(Boolean historical) {
        super.setHistorical(historical);
    }

}
