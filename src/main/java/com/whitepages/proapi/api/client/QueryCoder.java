package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.query.Query;

/**
 * <p>Query coders serve to transform/translate a Query into an intermediate
 * form that is compatible with the DataSource. For example, one may change
 * the query into a URI for later execution against an external web service.</p>
 *
 * <p>QueryCoder is part of the ResultFinder pipeline.
 * {@link com.whitepages.proapi.api.client.ResultFinder} instances link
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
public interface QueryCoder<Q extends Query, U> {
    /**
     * Transforms the query to an intermediate form.
     * @param query The query.
     * @param client The client performing the query.
     * @return The query intermediate form.
     * @throws QueryCoderException The wrapper for any errors encountered while attempting this transformation.
     */
    public U encode(Q query, Client client) throws QueryCoderException;
}
