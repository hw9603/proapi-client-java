package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PhoneProxyTest extends EntityProxyEntityResolutionTest {

    @Override
    public EntityProxy getProxy() {
        return ProxyTestHelper.getPhoneProxy();
    }

    @Override
    public EntityProxy getProxy(Client client) {
        return ProxyTestHelper.getPhoneProxy(client);
    }

    @Override
    public EntityId getProxyEntityId() {
        return ProxyTestHelper.phoneId;
    }
}
