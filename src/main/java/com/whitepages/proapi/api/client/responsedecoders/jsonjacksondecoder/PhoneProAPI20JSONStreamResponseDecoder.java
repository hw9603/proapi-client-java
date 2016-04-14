package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.ResponseDecoder;
import com.whitepages.proapi.api.client.ResponseDecoderException;
import com.whitepages.proapi.api.client.util.HttpResponse;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Phone;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PhoneProAPI20JSONStreamResponseDecoder extends ProAPI20JSONStreamResponseDecoder<Response<Phone>> implements ResponseDecoder<Response<Phone>, HttpResponse> {

    @Override
    public Response<Phone> decode(HttpResponse responseData, Client client) throws ResponseDecoderException {
        Response<Phone> phoneResponse;
        try {
            ObjectMapper mapper = getDeserializer(client);
            ResponseDeserializer deserializer = new ResponseDeserializer(mapper.readTree(responseData.getBody()), mapper, client);

            if(responseData.getResponseCode() == HTTP_OK) {
                phoneResponse = deserializer.deserializePhoneResponse();
            } else {
                phoneResponse = deserializer.deserializeErrorResponse();
            }
        } catch (IOException e) {
            throw new ResponseDecoderException(e);
        }
        return phoneResponse;
    }

}
