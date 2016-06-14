package com.whitepages.proapi.api.query;

import com.whitepages.proapi.data.entity.EntityId;

/**
 * The root concrete class for all Person queries.
 */
public class PersonQuery extends WhereQuery implements Query {

    private String name;
    private String firstName;
    private String middleName;
    private String lastName;

    private String suffix;
    private String title;

    private Boolean useHistorical;
    private Boolean useMetro;

    public PersonQuery() {
    }

    public PersonQuery(EntityId id) {
        super(id);
    }

    public PersonQuery(String name, String city, String stateCode, String postalCode) {
        super(city, stateCode, postalCode);
        this.name = name;
    }

    public PersonQuery(String firstName, String middleName, String lastName, String city, String stateCode, String postalCode) {
        super(city, stateCode, postalCode);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
    public PersonQuery(String firstName, String middleName, String lastName, String streetLine1, String streetLine2, String city, String stateCode, String postalCode) {
        super(streetLine1, streetLine2, city, stateCode, postalCode);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUseHistorical() {
        return useHistorical;
    }

    public void setUseHistorical(Boolean useHistorical) {
        this.useHistorical = useHistorical;
    }

    public Boolean getUseMetro() {
        return useMetro;
    }

    public void setUseMetro(Boolean useMetro) {
        this.useMetro = useMetro;
    }

    @Override
    protected String paramsToString() {
        String s = paramToString("name", getName()) +
                paramToString("firstName", getFirstName()) +
                paramToString("middleName", getMiddleName()) +
                paramToString("lastName", getLastName()) +
                paramToString("suffix", getSuffix()) +
                paramToString("title", getTitle()) +
                paramToString("useHistorical", getUseHistorical()) +
                paramToString("useMetro", getUseMetro());

        return s + super.paramsToString();
    }
}
