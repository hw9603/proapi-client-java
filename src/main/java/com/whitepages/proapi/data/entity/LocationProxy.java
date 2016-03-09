package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.LocationQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.List;

/**
 * Strongly typed {@link EntityProxy} for {@link Location} entities.
 * @see com.whitepages.proapi.data.entity.EntityProxy
 * @see com.whitepages.proapi.data.entity.Location
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class LocationProxy extends EntityProxy implements Location {

    private Location location;

    public LocationProxy(EntityId entityId, Client client) {
        super(entityId, client);
    }

    @Override
    protected Entity getEntity() {
        return location;
    }

    @Override
    public void load() throws FindException {
        if(!isLoaded()) {
            Response<Location> response = getClient().findLocations(new LocationQuery(getId()));
            List<Location> results = response.getResults();
            location = (results == null || results.isEmpty()) ? null : results.get(0);
            setLoaded(true);
        }
    }

    @Override
    public LocationType getType() {
        return location.getType();
    }

    @Override
    public String getStandardAddressLine1() {
        return (location == null) ? null : location.getStandardAddressLine1();
    }

    @Override
    public String getStandardAddressLine2() {
        return (location == null) ? null : location.getStandardAddressLine2();
    }

    @Override
    public String getCity() {
        return (location == null) ? null : location.getCity();
    }

    @Override
    public String getPostalCode() {
        return (location == null) ? null : location.getPostalCode();
    }

    @Override
    public String getStateCode() {
        return (location == null) ? null : location.getStateCode();
    }

    @Override
    public String getCountryCode() {
        return (location == null) ? null : location.getCountryCode();
    }

    @Override
    public String getZip4() {
        return (location == null) ? null : location.getZip4();
    }

    @Override
    public TimePeriod getValidFor() {
        return (location == null) ? null : location.getValidFor();
    }

    @Override
    public Boolean getReceivingMail() {
        return (location == null) ? null : location.getReceivingMail();
    }

    @Override
    public NotReceivingMailReason getNotReceivingMailReason() {
        return (location == null) ? null : location.getNotReceivingMailReason();
    }

    @Override
    public AddressUsage getUsage() {
        return (location == null) ? null : location.getUsage();
    }

    @Override
    public DeliveryPoint getDeliveryPoint() {
        return (location == null) ? null : location.getDeliveryPoint();
    }

    @Override
    public AddressType getAddressType() {
        return (location == null) ? null : location.getAddressType();
    }

    @Override
    public LatLong getLatLong() {
        return (location == null) ? null : location.getLatLong();
    }

    @Override
    public Boolean getDeliverable() {
        return (location == null) ? null : location.getDeliverable();
    }
}
