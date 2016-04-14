package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationProxyTest extends EntityProxyEntityResolutionTest {

    @Override
    public EntityProxy getProxy() {
        return ProxyTestHelper.getLocationProxy();
    }

    @Override
    public EntityProxy getProxy(Client client) {
        return ProxyTestHelper.getLocationProxy(client);
    }

    @Override
    public EntityId getProxyEntityId() {
        return ProxyTestHelper.locationId;
    }

}
