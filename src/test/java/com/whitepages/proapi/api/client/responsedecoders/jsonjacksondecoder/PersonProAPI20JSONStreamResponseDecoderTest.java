package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.entity.EntityId;
import com.whitepages.proapi.data.entity.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonProAPI20JSONStreamResponseDecoderTest {

    static Response<Person> response;
    static Person person;
    static LocationAssociation association;

    @BeforeClass
    public static void setUp() throws ResponseDecoderException {
        response = new ResponseDecoderHelper().getPersonResponse();
        person = response.getResults().get(0);
        association = person.getLocationAssociations().get(0);
    }

    @Test
    public void hasResults() {
        assertThat(response.getResults().size(), is(23));
    }

    @Test
    public void hasNames() {
        assertThat(person.getNames().get(0).getFirstName(), is("Jane"));
        assertThat(person.getNames().get(0).getMiddleName(), is("A"));
        assertThat(person.getNames().get(0).getLastName(), is("Smith"));
    }

    @Test
    public void hasLocationAssociations() {
        assertThat(person.getLocationAssociations().size(), is(6));
    }

    @Test
    public void hasLocations() {
        assertThat(person.getLocations().get(0).getId(), is(EntityId.fromString("Location.cf41eb5e-b4c4-4c16-90fb-4bbbe688f06e.Durable")));
    }

    @Test
    public void hasPeopleAssociations() {
        assertThat(person.getPersonAssociations(), is(nullValue()));
    }

    @Test
    public void hasBusinessAssociations() {
        assertThat(person.getBusinessAssociations(), is(nullValue()));
    }

    @Test
    public void hasAgeRangeStart() {
        assertThat(person.getAgeRange().getStart(), is(55));
    }

    @Test
    public void hasAgeRangeEnd() {
        assertThat(person.getAgeRange().getEnd(), is(59));
    }

    @Test
    public void hasPhoneAssociations() {
        assertThat(person.getPhoneAssociations().size(), is(1));
    }

    @Test
    public void hasExcpectedHitoricalAssociationsForAttributeTests() {
        assertThat(association.getEntityId(), is(EntityId.fromString("Location.cf41eb5e-b4c4-4c16-90fb-4bbbe688f06e.Durable")));
    }

    @Test
    public void hasCorrecHistoricalAssociationAttribute() {
        assertThat(association.getHistorical(), is(true));
    }
    
    @Test
    public void hasCorrectValidForAssociationAttributes() {
        assertThat(association.getValidFor(), is(notNullValue()));
        assertThat(association.getValidFor().getStart(), is(new GregorianCalendar(1990, Calendar.JULY, 1).getTime()));
        assertThat(association.getValidFor().getStop(), is(new GregorianCalendar(1990, Calendar.DECEMBER, 1).getTime()));
    }

    @Test
    public void hasCorrectContactCreationDateAssociationAttributes() {
        long timeInSec = 1366924660L;
        assertThat(association.getContactCreationDate(), is(new Date(timeInSec * 1000L)));
    }
}
