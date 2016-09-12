package com.whitepages.proapi.api.client;


import com.whitepages.proapi.api.client.Props;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.BusinessQuery;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PropsTest {

    @Test
    public void defaultReadTimeout_works_as_expected() {
        int defaultReadTimeout = Props.getDefaultReadTimeout();
        Assert.assertNotEquals(0,  defaultReadTimeout);
    }
}
