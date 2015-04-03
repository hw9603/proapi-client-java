package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class EntityProxyEntityResolutionTest {

    public abstract EntityProxy getProxy();

    public abstract EntityProxy getProxy(Client client);

    public abstract EntityId getProxyEntityId();

    @Test
    public void isLoadedShouldWork() throws FindException {
        EntityProxy proxy = getProxy();
        assertThat(proxy.isLoaded(), is(false));
        proxy.load();
        assertThat(proxy.isLoaded(), is(true));
    }

    @Test
    public void loadFetchesTheEntity() throws FindException {
        EntityProxy proxy = getProxy();
        proxy.load();
        assertThat(proxy.getId(), is(getProxyEntityId()));
    }

    @Test
    public void loadingTwiceShouldNotCauseLookupTwice() throws FindException {
        // Do a normal lookup.
        ProxyTestHelper.StubClient client = (ProxyTestHelper.StubClient) ProxyTestHelper.getErrorClient();
        client.setForceErrorResult(false);
        EntityProxy proxy = getProxy(client);
        proxy.load();

        // Error if we try to look it up again.
        client.setForceErrorResult(true);
        proxy.load();
    }

    @Test
    public void loadingAnEntityNotFoundOnLookup() throws FindException {
        EntityProxy proxy = getProxy(ProxyTestHelper.getEmptyClient());
        proxy.load();
        assertThat(proxy.getEntity(), nullValue());
        assertThat(proxy.isLoaded(), is(true));
    }

}
