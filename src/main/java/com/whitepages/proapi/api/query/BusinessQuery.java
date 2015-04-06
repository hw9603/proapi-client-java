package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The root concrete class for all Business queries.
 */
public class BusinessQuery extends WhereQuery implements Query {

    private String name;

    public BusinessQuery() {
    }

    public BusinessQuery(EntityId id) {
        super(id);
    }

    public BusinessQuery(String name) {
        this.name = name;
    }

    public BusinessQuery(String name, String city, String state, String postalCode) {
        super(city, state, postalCode);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String paramsToString() {
        return paramToString("name", getName()) + super.paramsToString();
    }
}
