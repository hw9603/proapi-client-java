package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PhoneQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.association.LocationAssociation;

import java.util.List;

/**
 * Strongly typed {@link EntityProxy} for {@link Phone} entities.
 * @see com.whitepages.proapi.data.entity.EntityProxy
 * @see com.whitepages.proapi.data.entity.Phone
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class PhoneProxy extends EntityProxy implements Phone {

    private Phone phone;

    public PhoneProxy(EntityId entityId, Client client) {
        super(entityId, client);
    }

    @Override
    protected Entity getEntity() {
        return phone;
    }

    @Override
    public void load() throws FindException {
        if(!isLoaded()) {
            Response<Phone> response =  getClient().findPhones(new PhoneQuery(getId()));
            List<Phone> results = response.getResults();
            phone = (results == null || results.isEmpty()) ? null : results.get(0);
            setLoaded(true);
        }
    }

    @Override
    public LineType getLineType() {
        return (phone == null) ? null : phone.getLineType();
    }

    @Override
    public String getPhoneNumber() {
        return (phone == null) ? null : phone.getPhoneNumber();
    }

    @Override
    public String getCountryCallingCode() {
        return (phone == null) ? null : phone.getCountryCallingCode();
    }

    @Override
    public String getExtension() {
        return (phone == null) ? null : phone.getExtension();
    }

    @Override
    public String getCarrier() {
        return (phone == null) ? null : phone.getCarrier();
    }

    @Override
    public Reputation getReputation() {
        return (phone == null) ? null : phone.getReputation();
    }

    @Override
    public Boolean getDoNotCall() {
        return (phone == null) ? null : phone.getDoNotCall();
    }

    @Override
    public Boolean getPrepaid() {
        return (phone == null) ? null : phone.getPrepaid();
    }

    @Override
    public Boolean getValid() {
        return (phone == null) ? null : phone.getValid();
    }

    @Override
    public LocationAssociation getBestLocationAssociation() {
        return (phone == null) ? null : phone.getBestLocationAssociation();
    }

    @Override
    public Location getBestLocation() {
        return (phone == null) ? null : phone.getBestLocation();
    }
}
