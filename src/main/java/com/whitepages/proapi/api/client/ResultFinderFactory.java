package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.client.datasources.HttpDataSource;
import com.whitepages.proapi.api.client.querycoders.*;
import com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder.*;
import com.whitepages.proapi.api.query.*;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.*;

/**
 * <p></p>A factory for creation of {@link ResultFinder} instances.</p>
 *
 * <p>ResultFinders require choosing a compatible set of {@link QueryCoder}, {@link DataSource}, and
 * {@link ResponseDecoder} instances in order to function. This factory supplies factory methods for the
 * creation of properly configured ResultFinder instances.</p>
 *
 * @see ResultFinder
 */
public class ResultFinderFactory {

    public static ResultFinder<PersonQuery, Response<Person>> getDefaultPersonResultFinder() {
        return new ResultFinderImpl<>(new PersonQueryToProAPI20URICoder(), new HttpDataSource(), new PersonProAPI20JSONStreamResponseDecoder());
    }

    public static ResultFinder<BusinessQuery, Response<Business>> getDefaultBusinessResultFinder() {
        return new ResultFinderImpl<>(new BusinessQueryToProAPI20URICoder(), new HttpDataSource(), new BusinessProAPI20JSONStreamResponseDecoder());
    }

    public static ResultFinder<PhoneQuery, Response<Phone>> getDefaultPhoneResultFinder() {
        return new ResultFinderImpl<>(new PhoneQueryToProAPI20URICoder(), new HttpDataSource(), new PhoneProAPI20JSONStreamResponseDecoder());
    }

    public static ResultFinder<LocationQuery, Response<Location>> getDefaultLocationResultFinder() {
        return new ResultFinderImpl<>(new LocationQueryToProAPI20URICoder(), new HttpDataSource(), new LocationProAPI20JSONStreamResponseDecoder());
    }
    
}
