package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.query.Query;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;

/**
 * A standardized, generic implementation of {@link ResultFinder} that properly
 * threads together the {@link QueryCoder}, {@link DataSource}, and
 * {@link ResponseDecoder} to execute a find request.
 *
 * @see com.whitepages.proapi.api.client.ResultFinder
 */
public class ResultFinderImpl<Q extends Query, R extends Response<? extends Entity>, U, V> implements ResultFinder<Q, R> {

    private final QueryCoder<Q, U> queryCoder;
    private final DataSource<U, V> dataSource;
    private final ResponseDecoder<R, V> responseDecoder;

    public ResultFinderImpl(QueryCoder<Q, U> queryCoder, DataSource<U, V> dataSource, ResponseDecoder<R, V> responseDecoder) {
        this.queryCoder = queryCoder;
        this.dataSource = dataSource;
        this.responseDecoder = responseDecoder;
    }

    @Override
    public R find(Q query, Client client) throws FindException {
        U queryData = queryCoder.encode(query, client);
        V responseData = dataSource.execute(queryData, client);
        return responseDecoder.decode(responseData, client);
    }
}
