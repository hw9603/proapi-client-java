package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The root concrete class for all Phone queries.
 */
public class PhoneQuery extends EntityQuery implements Query {

    public enum ResponseType {
        REGULAR,
        LITE,
        CALLER_ID
    }

    private String phone;

    private ResponseType responseType = ResponseType.REGULAR;

    public PhoneQuery() {
    }

    public PhoneQuery(EntityId id) {
        super(id);
    }

    public PhoneQuery(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    protected void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    @Override
    protected String paramsToString() {
        return paramToString("phone", getPhone()) + paramToString("responseType", getResponseType());
    }
}
