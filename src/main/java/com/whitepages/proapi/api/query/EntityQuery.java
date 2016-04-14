package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * An abstract superclass providing common methods for working with entity queries.
 */
public abstract class EntityQuery implements Query {

    private EntityId id;

    private Integer maxResults;
    private Integer page;
    private Integer pageLength;

    public EntityQuery() {
    }

    public EntityQuery(EntityId id) {
        this.id = id;
    }

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageLength() {
        return pageLength;
    }

    public void setPageLength(Integer pageLength) {
        this.pageLength = pageLength;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "( " +
                paramToString("id", getId()) +
                paramsToString() +
                ")";
    }

    protected abstract String paramsToString();

    protected String paramToString(String name, Object value) {
        return name + "=" + value + " ";
    }
}
