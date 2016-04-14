package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import java.io.InputStream;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.Config;
import com.whitepages.proapi.api.client.util.HttpResponse;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Business;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.data.entity.Person;
import com.whitepages.proapi.data.entity.Phone;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class ResponseDecoderHelper {

    private static Client client = new Client("api_key");
    private static Config config = new Config("api_key");

    private Response<Business> businessResponse = decodeBusinessResponse();
    private Response<Phone> phoneResponse = decodePhoneResponse();
    private Response<Location> locationResponse = decodeLocationResponse();
    private Response<Person> personResponse = decodePersonResponse();

    Response<Business> getBusinessResponse() {
        return businessResponse;
    }

    Response<Phone> getPhoneResponse() {
        return phoneResponse;
    }

    Response<Location> getLocationResponse() {
        return locationResponse;
    }

    Response<Person> getPersonResponse() {
        return personResponse;
    }

    private Response<Business> decodeBusinessResponse() {
        try {
        	HttpResponse fileResponse = readFile("/jsonresponses/businessResponse.json");
        	BusinessProAPI20JSONStreamResponseDecoder decoder = new BusinessProAPI20JSONStreamResponseDecoder();
        	Response<Business> response = decoder.decode(fileResponse, client);
        	return response;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Response<Phone> decodePhoneResponse() {
        try {
            return new PhoneProAPI20JSONStreamResponseDecoder().decode(readFile("/jsonresponses/phoneResponse.json"), client);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Response<Location> decodeLocationResponse() {
        try {
            return new LocationProAPI20JSONStreamResponseDecoder().decode(readFile("/jsonresponses/locationResponse.json"), client);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Response<Person> decodePersonResponse() {
        try {
            return new PersonProAPI20JSONStreamResponseDecoder().decode(readFile("/jsonresponses/personResponse.json"), client);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse readFile(String fileName) {
    	Class<? extends ResponseDecoderHelper> c = this.getClass();
    	InputStream stream = c.getResourceAsStream(fileName);
        return new HttpResponse(200, null, null, stream);
    }

}
