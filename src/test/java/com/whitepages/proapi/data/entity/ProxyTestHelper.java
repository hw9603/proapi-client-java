package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.BusinessQuery;
import com.whitepages.proapi.api.query.LocationQuery;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.query.PhoneQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.api.response.ResponseMessages;
import com.whitepages.proapi.data.message.Message;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class ProxyTestHelper {

    static final String apiKey = "mockapikey";

    static final EntityId personId = new EntityId(EntityId.EntityType.PERSON, UUID.randomUUID());
    static final EntityId businessId = new EntityId(EntityId.EntityType.BUSINESS, UUID.randomUUID());
    static final EntityId phoneId = new EntityId(EntityId.EntityType.PHONE, UUID.randomUUID());
    static final EntityId locationId = new EntityId(EntityId.EntityType.LOCATION, UUID.randomUUID());

    private static final Person person;
    private static final Business business;
    private static final Phone phone;
    private static final Location location;

    static {
        person = new PersonImpl(personId);
    }

    static {
        business = new BusinessImpl(businessId);
    }

    static {
        phone = new PhoneImpl(phoneId);
        ((PhoneImpl) phone).setPhoneNumber("7075551234");
    }

    static {
        location = new LocationImpl(locationId);
    }

    private static final ResponseMessages emptyMessages = new ResponseMessages(new ArrayList<Message>());

    static Client getClient() {
        return new StubClient(apiKey);
    }

    static Client getEmptyClient() {
        return new StubClient(apiKey, false, true);
    }

    static Client getErrorClient() {
        return new StubClient(apiKey, true, false);
    }

    static class StubClient extends Client {
        public boolean forceErrorResult;
        public boolean forceEmptyResult;

        public StubClient(String apiKey) {
            this(apiKey, false, false);
        }

        public StubClient(String apiKey, boolean forceErrorResult, boolean forceEmptyResult) {
            super(apiKey);
            this.forceErrorResult = forceErrorResult;
            this.forceEmptyResult = forceEmptyResult;
        }

        public boolean isForceErrorResult() {
            return forceErrorResult;
        }

        public void setForceErrorResult(boolean forceErrorResult) {
            this.forceErrorResult = forceErrorResult;
        }

        public boolean isForceEmptyResult() {
            return forceEmptyResult;
        }

        public void setForceEmptyResult(boolean forceEmptyResult) {
            this.forceEmptyResult = forceEmptyResult;
        }

        @Override
        public Response<Person> findPeople(PersonQuery query) throws FindException {
            if(isForceErrorResult())
                throw new FindException("Stubbed client always errors!");

            ArrayList<Person> results = new ArrayList<>();
            if( !isForceEmptyResult() )
                results.add(person);
            return new Response<>(this, results, getDictionary(this), emptyMessages);
        }

        @Override
        public Response<Business> findBusinesses(BusinessQuery query) throws FindException {
            if(isForceErrorResult())
                throw new FindException("Stubbed client always errors!");

            ArrayList<Business> results = new ArrayList<>();
            if( !isForceEmptyResult() )
                results.add(business);
            return new Response<>(this, results, getDictionary(this), emptyMessages);
        }

        @Override
        public Response<Phone> findPhones(PhoneQuery query) throws FindException {
            if(isForceErrorResult())
                throw new FindException("Stubbed client always errors!");

            ArrayList<Phone> results = new ArrayList<>();
            if( !isForceEmptyResult() )
                results.add(phone);
            return new Response<>(this, results, getDictionary(this), emptyMessages);
        }

        @Override
        public Response<Location> findLocations(LocationQuery query) throws FindException {
            if(isForceErrorResult())
                throw new FindException("Stubbed client always errors!");

            ArrayList<Location> results = new ArrayList<>();
            if( !isForceEmptyResult() )
                results.add(location);
            return new Response<>(this, results, getDictionary(this), emptyMessages);
        }
    }

    static ResponseDictionary getDictionary(Client client) {
        ResponseDictionary dict = new ResponseDictionary(client);
        dict.add(person);
        dict.add(business);
        dict.add(phone);
        dict.add(location);
        return dict;
    }

    static Person getPerson() {
        return person;
    }

    static Business getBusiness() {
        return business;
    }

    static Phone getPhone() {
        return phone;
    }

    static Location getLocation() {
        return location;
    }

    static PersonProxy getPersonProxy() {
        return getPersonProxy(getClient());
    }

    static BusinessProxy getBusinessProxy() {
        return getBusinessProxy(getClient());
    }

    static PhoneProxy getPhoneProxy() {
        return getPhoneProxy(getClient());
    }

    static LocationProxy getLocationProxy() {
        return getLocationProxy(getClient());
    }

    static PersonProxy getPersonProxy(Client client) {
        return new PersonProxy(personId, client);
    }

    static BusinessProxy getBusinessProxy(Client client) {
        return new BusinessProxy(businessId, client);
    }

    static PhoneProxy getPhoneProxy(Client client) {
        return new PhoneProxy(phoneId, client);
    }

    static LocationProxy getLocationProxy(Client client) {
        return new LocationProxy(locationId, client);
    }

}
