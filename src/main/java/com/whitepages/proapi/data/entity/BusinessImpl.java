package com.whitepages.proapi.data.entity;

/**
 * The standard concrete implementation of the {@link Business} {@link Entity}.
 * @see com.whitepages.proapi.data.entity.Business
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class BusinessImpl extends EntityImpl implements Business {

    private String name;

    public BusinessImpl() {}

    public BusinessImpl(EntityId id) {
        super(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
