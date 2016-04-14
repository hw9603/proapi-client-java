package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * <p>The {@link com.whitepages.proapi.data.entity.Location} type specific {@link Association}.</p>
 *
 * <p>This class also adds accessors for the contact type of the location.</p>
 *
 * @see com.whitepages.proapi.data.entity.Location
 * @see com.whitepages.proapi.data.association.Association
 */
public class LocationAssociation extends Association implements HistoricalAssociation {

    public LocationAssociation(EntityId entityId, ResponseDictionary responseDictionary) {
        super(entityId, responseDictionary);
    }

    public LocationAssociation(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        super(entityId, dictionary, validFor, historical, contactCreationDate);
    }

    public Location getLocation() {
        return getDictionary().getLocation(getEntityId());
    }

    @Override
    public Entity getEntity() {
        return getLocation();
    }

    @Override
    public EntityId.EntityType getEntityIdType() {
        return EntityId.EntityType.LOCATION;
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
