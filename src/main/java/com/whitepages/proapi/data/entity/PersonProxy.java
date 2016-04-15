package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.association.LocationAssociation;

import java.util.List;

/**
 * Strongly typed {@link EntityProxy} for {@link Person} entities.
 * @see com.whitepages.proapi.data.entity.EntityProxy
 * @see com.whitepages.proapi.data.entity.Person
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class PersonProxy extends EntityProxy implements Person {

    private Person person;

    public PersonProxy(EntityId entityId, Client client) {
        super(entityId, client);
    }

    @Override
    protected Entity getEntity() {
        return person;
    }

    @Override
    public void load() throws FindException {
        if(!isLoaded()) {
            Response<Person> response = getClient().findPeople(new PersonQuery(getId()));
            List<Person> results = response.getResults();
            person = (results == null || results.isEmpty()) ? null : results.get(0);
            setLoaded(true);
        }
    }

    @Override
    public List<Name> getNames() {
        return (person == null) ? null : person.getNames();
    }

    @Override
    public AgeRange getAgeRange() {
        return (person == null) ? null : person.getAgeRange();
    }

    @Override
    public Gender getGender() {
        return (person == null) ? null : person.getGender();
    }
}
