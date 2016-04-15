package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.data.association.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for implementation of common behaviors between all
 * {@link Entity} types.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public abstract class EntityImpl implements Entity {
    private EntityId id;

    private List<PersonAssociation> personAssociations;
    private List<BusinessAssociation> businessAssociations;
    private List<LocationAssociation> locationAssociations;
    private List<PhoneAssociation> phoneAssociations;

    private List<Person> people;
    private List<Business> businesses;
    private List<Location> locations;
    private List<Phone> phones;

    public EntityImpl() {}

    public EntityImpl(EntityId id) {
        this.id = id;
    }

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public List<PersonAssociation> getPersonAssociations() {
        return personAssociations;
    }

    public void setPersonAssociations(List<PersonAssociation> personAssociations) {
        this.personAssociations = personAssociations;
        people = null;
    }

    public List<BusinessAssociation> getBusinessAssociations() {
        return businessAssociations;
    }

    public void setBusinessAssociations(List<BusinessAssociation> businessAssociations) {
        this.businessAssociations = businessAssociations;
        businesses = null;
    }

    public List<LocationAssociation> getLocationAssociations() {
        return locationAssociations;
    }

    public void setLocationAssociations(List<LocationAssociation> locationAssociations) {
        this.locationAssociations = locationAssociations;
        locations = null;
    }

    public List<PhoneAssociation> getPhoneAssociations() {
        return phoneAssociations;
    }

    public void setPhoneAssociations(List<PhoneAssociation> phoneAssociations) {
        this.phoneAssociations = phoneAssociations;
        phones = null;
    }

    public List<Association> getAssociations() {
        List<Association> associations = new ArrayList<>();
        addAllIfNotNull(associations, getPersonAssociations());
        addAllIfNotNull(associations, getPhoneAssociations());
        addAllIfNotNull(associations, getBusinessAssociations());
        addAllIfNotNull(associations, getLocationAssociations());
        return associations;
    }

    public List<Person> getPeople() {
        if (people == null && getPersonAssociations() != null) {
            people = new ArrayList<>();
            for (PersonAssociation linkedPerson : getPersonAssociations()) {
                people.add(linkedPerson.getPerson());
            }
        }
        return people;
    }

    public List<Business> getBusinesses() {
        if (businesses == null && getBusinessAssociations() != null) {
            businesses = new ArrayList<>();
            for(BusinessAssociation linkedBusiness : getBusinessAssociations()) {
                businesses.add(linkedBusiness.getBusiness());
            }
        }
        return businesses;
    }

    public List<Location> getLocations() {
        if (locations == null && getLocationAssociations() != null) {
            locations = new ArrayList<>();
            for(LocationAssociation linkedLocation : getLocationAssociations()) {
                locations.add(linkedLocation.getLocation());
            }
        }
        return locations;
    }

    public List<Phone> getPhones() {
        if (phones == null && getPhoneAssociations() != null) {
            phones = new ArrayList<>();
            for(PhoneAssociation linkedPhone : getPhoneAssociations()) {
                phones.add(linkedPhone.getPhone());
            }
        }
        return phones;
    }

    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<>();
        addAllIfNotNull(entityList, getPeople());
        addAllIfNotNull(entityList, getBusinesses());
        addAllIfNotNull(entityList, getPhones());
        addAllIfNotNull(entityList, getLocations());
        return entityList;
    }

    public abstract String getName();

    public boolean isProxy() {
        return false;
    }

    public boolean isLoaded() {
        return true;
    }

    public void load() throws FindException {
    }

    private <T, S extends T> void addAllIfNotNull(List<T> coll, List<S> c) {
        if(c != null) {
            coll.addAll(c);
        }
    }
}
