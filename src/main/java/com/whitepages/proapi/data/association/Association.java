package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Date;

/**
 * <p>The root interface for all Associations (e.g. {@link PersonAssociation}, {@link BusinessAssociation},
 * {@link PhoneAssociation}, and {@link LocationAssociation}).</p>
 *
 * <p>The Whitepages API presents its data in a directed graph form, whereby:</p>
 * <ul>
 *     <li>Each node is an {@link Entity}, and</li>
 *     <li>Each edge is an {@link Association}.</li>
 * </ul>
 * <p></p>Entities contain data intrinsic to the entity, whereas associations
 * contain data pertaining to the relationship between its source and destination
 * entities</p>
 *
 * <p>Associations are a directed edge from a source entity node to
 * a destination entity node. Since traversal starts from the source
 * entity, there is only one accessor on
 * the Assocation instance, which leads to the destination entity.</p>
 *
 * @see com.whitepages.proapi.data.entity.Entity
 * @see com.whitepages.proapi.data.association.PersonAssociation
 * @see com.whitepages.proapi.data.association.BusinessAssociation
 * @see com.whitepages.proapi.data.association.PhoneAssociation
 * @see com.whitepages.proapi.data.association.LocationAssociation
 */
public abstract class Association {

    private final EntityId entityId;

    private final ResponseDictionary dictionary;

    private TimePeriod validFor;
    private Boolean historical;
    private Date contactCreationDate;

    public Association(EntityId entityId, ResponseDictionary dictionary) {
        this(entityId, dictionary, null, false, null);
    }

    public Association(EntityId entityId, ResponseDictionary dictionary, TimePeriod validFor, Boolean historical, Date contactCreationDate) {
        this.entityId = entityId;
        this.dictionary = dictionary;
        this.validFor = validFor;
        this.historical = historical;
        this.contactCreationDate = contactCreationDate;

        validateEntityIdType();
    }

    /**
     * Returns the ID of the associated entity.
     */
    public EntityId getEntityId() {
        return entityId;
    }
    /**
     * <p></p>Returns the associated entity in a way that is generic across all
     * Association types; for strongly typed accessors, see
     * {@link PersonAssociation#getPerson()},
     * {@link BusinessAssociation#getBusiness()},
     * {@link PhoneAssociation#getPhone()}, and
     * {@link LocationAssociation#getLocation()}.</p>
     *
     * @return
     *
     * @see com.whitepages.proapi.data.association.PersonAssociation#getPerson()
     * @see com.whitepages.proapi.data.association.BusinessAssociation#getBusiness()
     * @see com.whitepages.proapi.data.association.PhoneAssociation#getPhone()
     * @see com.whitepages.proapi.data.association.LocationAssociation#getLocation()
     */
    public abstract Entity getEntity();

    public abstract EntityId.EntityType getEntityIdType();

    protected TimePeriod getValidFor() {
        return validFor;
    }

    protected void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    protected Boolean getHistorical() {
        return historical;
    }

    protected void setHistorical(Boolean historical) {
        this.historical = historical;
    }

    public Date getContactCreationDate() {
        return contactCreationDate;
    }

    public void setContactCreationDate(Date contactCreationDate) {
        this.contactCreationDate = contactCreationDate;
    }

    protected ResponseDictionary getDictionary() {
        return dictionary;
    }

    protected void validateEntityIdType() {
        EntityId.EntityType expectedType = getEntityIdType();
        if(getEntityId().getType() != expectedType)
            throw new IllegalArgumentException("EntityId of wrong type: expected " + expectedType + " but got " + getEntityId().getType());
    }
}
