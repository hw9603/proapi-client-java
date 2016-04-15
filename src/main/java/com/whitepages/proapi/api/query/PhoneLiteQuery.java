package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The PhoneQuery instance for PhoneLite query construction.
 */
public class PhoneLiteQuery extends PhoneQuery implements Query {

    public PhoneLiteQuery() {
        setResponseType(ResponseType.LITE);
    }

    public PhoneLiteQuery(EntityId id) {
        super(id);
        setResponseType(ResponseType.LITE);
    }

    public PhoneLiteQuery(String phone) {
        super(phone);
        setResponseType(ResponseType.LITE);
    }
}
