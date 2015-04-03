package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.ResponseDecoder;
import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.client.util.HttpResponse;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Person;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonProAPI20JSONStreamResponseDecoder extends ProAPI20JSONStreamResponseDecoder<Response<Person>> implements ResponseDecoder<Response<Person>, HttpResponse> {

    @Override
    public Response<Person> decode(HttpResponse responseData, Client client) throws ResponseDecoderException {
        Response<Person> personResponse;
        try {
            ObjectMapper mapper = getDeserializer(client);
            ResponseDeserializer deserializer = new ResponseDeserializer(mapper.readTree(responseData.getBody()), mapper, client);

            if(responseData.getResponseCode() == HTTP_OK) {
                personResponse = deserializer.deserializePersonResponse();
            } else {
                personResponse = deserializer.deserializeErrorResponse();
            }
        } catch (IOException e) {
            throw new ResponseDecoderException(e);
        }
        return personResponse;
    }

}
