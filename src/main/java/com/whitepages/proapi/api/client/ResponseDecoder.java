package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.response.Response;

/**
 * <p>Response decoders transform the raw response data from a compatible
 * DataSource instance, and return a standardized Response. For example, they
 * may take an InputStream of JSON and parse it into native data objects.</p>
 *
 * <p>ResponseDecoder is part of the ResultFinder pipeline.
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
public interface ResponseDecoder<R extends Response<?>, V> {
    /**
     * Transforms raw response data into a standardized response.
     * @param responseData The raw response data.
     * @param client The associated Client.
     * @return The standardized Response.
     * @throws ResponseDecoderException The wrapper for any errors encountered while attempting this transformation.
     */
    public R decode(V responseData, Client client) throws ResponseDecoderException;
}
