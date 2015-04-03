package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class BusinessProxyTest extends EntityProxyEntityResolutionTest {

    @Override
    public EntityProxy getProxy() {
        return ProxyTestHelper.getBusinessProxy();
    }

    @Override
    public EntityProxy getProxy(Client client) {
        return ProxyTestHelper.getBusinessProxy(client);
    }

    @Override
    public EntityId getProxyEntityId() {
        return ProxyTestHelper.businessId;
    }

}
