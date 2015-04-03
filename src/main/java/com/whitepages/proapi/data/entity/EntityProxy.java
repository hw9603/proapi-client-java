package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.data.association.*;

import java.util.List;

/**
 * <p>The complete entity graph, traversed to its full extent, would
 * be incredibly large, thus, in practice, only a subset of this graph
 * is presented in the Response.
 * As a result, in some cases you may encounter associations whose nodes
 * are not available to you. When this occurs, an appropriate {@link EntityProxy}
 * instance is put into its place. To test if an entity is a proxy, use
 * the {@link #isProxy} method.</p>
 *
 * <p>Entity proxies satisfy the appropriate entity interface, returning
 * null for all the values&mdash;acting as if the values were masked
 * from you. The exception to this rule is the ID of the associated entity,
 * which is always present on a proxy value.</p>
 *
 * <p>To load an entity, call the {@link #load} method. <em>This method
 * will make a call to the remote service</em>, fetching information for
 * the associated entity ID. To test if a value has been loaded, the
 * {@link #isLoaded} method may be used.</p>
 *
 * @see #isProxy
 * @see #isLoaded
 * @see #load
 * @see com.whitepages.proapi.data.entity.Entity
 */
public abstract class EntityProxy implements Entity {

    private final EntityId entityId;
    private final Client client;

    private boolean loaded;

    public EntityProxy(EntityId entityId, Client client) {
        this.entityId = entityId;
        this.client = client;
        this.loaded = false;
    }

    protected Client getClient() {
        return client;
    }

    protected abstract Entity getEntity();

    public abstract void load() throws FindException;

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    protected void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public boolean isProxy() {
        return true;
    }

    @Override
    public EntityId getId() {
        return entityId;
    }

    @Override
    public String getName() {
        return (getEntity() == null) ? null : getEntity().getName();
    }

    @Override
    public List<PersonAssociation> getPersonAssociations() {
        return (getEntity() == null) ? null : getEntity().getPersonAssociations();
    }

    @Override
    public List<BusinessAssociation> getBusinessAssociations() {
        return (getEntity() == null) ? null : getEntity().getBusinessAssociations();
    }

    @Override
    public List<LocationAssociation> getLocationAssociations() {
        return (getEntity() == null) ? null : getEntity().getLocationAssociations();
    }

    @Override
    public List<PhoneAssociation> getPhoneAssociations() {
        return (getEntity() == null) ? null : getEntity().getPhoneAssociations();
    }

    @Override
    public List<Association> getAssociations() {
        return (getEntity() == null) ? null : getEntity().getAssociations();
    }

    @Override
    public List<Person> getPeople() {
        return (getEntity() == null) ? null : getEntity().getPeople();
    }

    @Override
    public List<Business> getBusinesses() {
        return (getEntity() == null) ? null : getEntity().getBusinesses();
    }

    @Override
    public List<Location> getLocations() {
        return (getEntity() == null) ? null : getEntity().getLocations();
    }

    @Override
    public List<Phone> getPhones() {
        return (getEntity() == null) ? null : getEntity().getPhones();
    }

    @Override
    public List<Entity> getEntities() {
        return (getEntity() == null) ? null : getEntity().getEntities();
    }
}
