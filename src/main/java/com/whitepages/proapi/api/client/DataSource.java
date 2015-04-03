package com.whitepages.proapi.api.client;

/**
 * <p>Data sources act as brokers of information, fetching a response of one
 * type, using a request of another type. For example, a data source may
 * take a URI instance, call the associated external web service, and return
 * an InputStream containing the response body.</p>
 *
 * <p>DataSource is part of the ResultFinder pipeline.
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
public interface DataSource<U, V> {
    /**
     * Transforms the query intermediate form into a raw result.
     * @param u The query intermediate form.
     * @param client The client performing the request.
     * @return The raw result.
     * @throws DataSourceException The wrapper error for any errors encountered while attempting to retrieve data.
     */
    public V execute(U u, Client client) throws DataSourceException;
}
