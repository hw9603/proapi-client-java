package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The PhoneQuery instance for CallerID query construction.
 */
public class PhoneCallerIdQuery extends PhoneQuery implements Query {

    public PhoneCallerIdQuery() {
        setResponseType(ResponseType.CALLER_ID);
    }

    public PhoneCallerIdQuery(EntityId id) {
        super(id);
        setResponseType(ResponseType.CALLER_ID);
    }

    public PhoneCallerIdQuery(String phone) {
        super(phone);
        setResponseType(ResponseType.CALLER_ID);
    }
}
