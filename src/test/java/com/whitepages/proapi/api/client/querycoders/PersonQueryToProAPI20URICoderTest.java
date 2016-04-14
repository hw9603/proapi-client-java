package com.whitepages.proapi.api.client.querycoders;

import com.whitepages.proapi.api.client.QueryCoder;
import com.whitepages.proapi.api.client.QueryCoderException;
import com.whitepages.proapi.api.query.PersonQuery;
import org.junit.Test;

import java.net.URI;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonQueryToProAPI20URICoderTest extends WhereQueryToProAPI2URICoderTest<PersonQuery> {

    private static final String uriPath = "/2.0/person.json";

    private static final String name = "Amelia Pond";
    private static final String nameEncoded = "Amelia+Pond";
    private static final String firstName = "Amelia";
    private static final String middleName = "Jessica";
    private static final String lastName = "Pond";
    private static final String suffix = "I";
    private static final String title = "Dr";

    @Test
    public void nameParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getFullNameQuery(), getClient());
        assertThat(uri.getQuery(), containsString("name=" + nameEncoded));
    }

    @Test
    public void firstNameParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), containsString("first_name=" + firstName));
    }

    @Test
    public void middleNameParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), containsString("middle_name=" + middleName));
    }

    @Test
    public void lastNameParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), containsString("last_name=" + lastName));
    }

    @Test
    public void titleParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getTitleAndSuffixNameQuery(), getClient());
        assertThat(uri.getQuery(), containsString("title=" + title));
    }

    @Test
    public void suffixParameter() throws QueryCoderException {
        URI uri = getCoder().encode(getTitleAndSuffixNameQuery(), getClient());
        assertThat(uri.getQuery(), containsString("suffix=" + suffix));
    }

    @Test
    public void namePartParametersOptional() throws QueryCoderException {
        URI uri = getCoder().encode(getFullNameQuery(), getClient());
        assertThat(uri.getQuery(), not(containsString("first_name")));
        assertThat(uri.getQuery(), not(containsString("middle_name")));
        assertThat(uri.getQuery(), not(containsString("last_name")));
    }

    @Test
    public void titleParametersOptional() throws QueryCoderException {
        URI uri = getCoder().encode(getDefaultQuery(), getClient());
        assertThat(uri.getQuery(), not(containsString("suffix")));
        assertThat(uri.getQuery(), not(containsString("title")));
    }

    @Test
    public void booleanProperties() throws QueryCoderException {
        URI uri = getCoder().encode(getBooleanTestingQuery(), getClient());
        assertThat(uri.getQuery(), not(containsString("use_metro")));
        assertThat(uri.getQuery(), containsString("use_historical=false"));
    }

    protected PersonQuery getFullNameQuery() {
        return new PersonQuery(name, null, null, null);
    }

    protected PersonQuery getTitleAndSuffixNameQuery() {
        PersonQuery query = getDefaultQuery();
        query.setTitle(title);
        query.setSuffix(suffix);
        return query;
    }

    protected PersonQuery getBooleanTestingQuery() {
        PersonQuery query = getDefaultQuery();
        query.setUseHistorical(false);
        query.setUseMetro(null);
        return query;
    }

    @Override
    protected PersonQuery getDefaultQuery() {
        return new PersonQuery(firstName, middleName, lastName, null, null, null);
    }

    @Override
    protected QueryCoder<PersonQuery, URI> getCoder() {
        return new PersonQueryToProAPI20URICoder();
    }

    @Override
    protected String getURIPath() {
        return uriPath;
    }
}
