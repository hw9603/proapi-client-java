package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonProxyTest extends EntityProxyEntityResolutionTest {

    @Override
    public EntityProxy getProxy() {
        return ProxyTestHelper.getPersonProxy();
    }

    @Override
    public EntityProxy getProxy(Client client) {
        return ProxyTestHelper.getPersonProxy(client);
    }

    @Override
    public EntityId getProxyEntityId() {
        return ProxyTestHelper.personId;
    }
}
