package com.whitepages.proapi.api.query;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class QueryTest {

    @Test
    public void itShouldPrintAHumanReadablePersonQuery() {
        PersonQuery q = new PersonQuery("Amelia", "Jessica", "Pond", "Seattle", "WA", "98101");

        String s = q.toString();
        System.out.println(s);

        assertThat(s, startsWith("PersonQuery"));
        assertThat(s, containsString("Amelia"));
        assertThat(s, containsString("Jessica"));
        assertThat(s, containsString("Pond"));
        assertThat(s, containsString("Seattle"));
        assertThat(s, containsString("WA"));
        assertThat(s, containsString("98101"));
    }

    @Test
    public void itShouldPrintAHumanReadableBusinessQuery() {
        BusinessQuery q = new BusinessQuery("Whitepages");
        q.setCity("Seattle");
        q.setStateCode("WA");

        String s = q.toString();
        System.out.println(s);

        assertThat(s, startsWith("BusinessQuery"));
        assertThat(s, containsString("Whitepages"));
        assertThat(s, containsString("Seattle"));
        assertThat(s, containsString("WA"));
    }

    @Test
    public void itShouldPrintAHumanReadablePhoneQuery() {
        PhoneQuery q = new PhoneQuery("2065551234");

        String s = q.toString();
        System.out.println(s);

        assertThat(s, startsWith("PhoneQuery"));
        assertThat(s, containsString("2065551234"));
    }

    @Test
    public void itShouldPrintAHumanReadableLocationQuery() {
        LocationQuery q = new LocationQuery("1301 5th Ave", "Ste 1600", "Seattle", "WA", "98101");

        String s = q.toString();
        System.out.println(s);

        assertThat(s, startsWith("LocationQuery"));
        assertThat(s, containsString("1301 5th Ave"));
        assertThat(s, containsString("Ste 1600"));
        assertThat(s, containsString("Seattle"));
        assertThat(s, containsString("WA"));
        assertThat(s, containsString("98101"));
    }

}
