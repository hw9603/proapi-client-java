package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.BusinessQuery;
import com.whitepages.proapi.api.response.Response;

import java.util.List;

/**
 * Strongly typed {@link EntityProxy} for {@link Business} entities.
 * @see com.whitepages.proapi.data.entity.EntityProxy
 * @see com.whitepages.proapi.data.entity.Business
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class BusinessProxy extends EntityProxy implements Business {

    private Business business;

    public BusinessProxy(EntityId entityId, Client client) {
        super(entityId, client);
    }

    public List<Business.Url> getUrls() {
    	return (this.business == null) ? null : this.business.getUrls();
    }
    
    @Override
    protected Entity getEntity() {
        return business;
    }

    @Override
    public void load() throws FindException {
        if(!isLoaded()) {
            Response<Business> response = getClient().findBusinesses(new BusinessQuery(getId()));
            List<Business> results = response.getResults();
            business = (results == null || results.isEmpty()) ? null : results.get(0);
            setLoaded(true);
        }
    }

}
