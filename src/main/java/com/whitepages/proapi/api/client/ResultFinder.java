package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.query.Query;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;

/**
 * <p>{@link com.whitepages.proapi.api.client.ResultFinder} instances link
 * together the dataflow of the conversion of a
 * {@link com.whitepages.proapi.api.query.Query} to a
 * {@link com.whitepages.proapi.api.response.Response} via the following steps:</p>
 * <ol>
 *     <li>The {@link com.whitepages.proapi.api.query.Query} is transformed to an intermediate form via a {@link com.whitepages.proapi.api.client.QueryCoder},</li>
 *     <li>The intermediate form is used to fetch the raw response from the data source via a {@link com.whitepages.proapi.api.client.DataSource}, and</li>
 *     <li>The raw response is transformed into a normalized {@link com.whitepages.proapi.api.response.Response} via a {@link com.whitepages.proapi.api.client.ResponseDecoder}</li>
 * </ol>
 *
 * @see com.whitepages.proapi.api.client.ResultFinder
 * @see com.whitepages.proapi.api.client.ResultFinderImpl
 * @see com.whitepages.proapi.api.client.QueryCoder
 * @see com.whitepages.proapi.api.client.DataSource
 * @see com.whitepages.proapi.api.client.ResponseDecoder
 * @see com.whitepages.proapi.api.response.Response
 */
public interface ResultFinder<Q extends Query, R extends Response<? extends Entity>> {
    /**
     * Executes the given Query, returning a Response.
     * @param query The query to perform
     * @param client The client object to use.
     * @return The Response.
     * @throws FindException The wrapper class for any exceptions that may occur before a Response can be created and returned.
     */
    public R find(Q query, Client client) throws FindException;
}
