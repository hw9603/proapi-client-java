package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Business;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * The {@link com.whitepages.proapi.data.entity.Business} type specific {@link Association}.
 * @see com.whitepages.proapi.data.entity.Business
 * @see com.whitepages.proapi.data.association.Association
 */
public class BusinessAssociation extends LegalEntityAssociation implements HistoricalAssociation {

    public BusinessAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public BusinessAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
    }

    public Business getBusiness() {
        return getDictionary().getBusiness(getEntityId());
    }

    @Override
    public Entity getEntity() {
        return getBusiness();
    }

    @Override
    public EntityId.EntityType getEntityIdType() {
        return EntityId.EntityType.BUSINESS;
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
