package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.ResponseDecoder;
import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.client.util.HttpResponse;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Location;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class LocationProAPI20JSONStreamResponseDecoder extends ProAPI20JSONStreamResponseDecoder<Response<Location>> implements ResponseDecoder<Response<Location>, HttpResponse> {

    @Override
    public Response<Location> decode(HttpResponse responseData, Client client) throws ResponseDecoderException {
        Response<Location> locationResponse;
        try {
            ObjectMapper mapper = getDeserializer(client);
            ResponseDeserializer deserializer = new ResponseDeserializer(mapper.readTree(responseData.getBody()), mapper, client);

            if(responseData.getResponseCode() == HTTP_OK) {
                locationResponse = deserializer.deserializeLocationResponse();
            } else {
                locationResponse = deserializer.deserializeErrorResponse();
            }
        } catch (IOException e) {
            throw new ResponseDecoderException(e);
        }
        return locationResponse;
    }

}
