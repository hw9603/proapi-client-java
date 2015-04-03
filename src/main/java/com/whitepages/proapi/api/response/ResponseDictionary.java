package com.whitepages.proapi.api.response;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.data.entity.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The Response Dictionary is an internal helper class used for lazily resolving
 * {@link com.whitepages.proapi.data.association.Association} references.
 *
 * @see com.whitepages.proapi.data.association.Association
 * @see com.whitepages.proapi.api.response.Response
 */
public class ResponseDictionary {

    private Client client;

    private Map<EntityId, Person> personDictionary = new HashMap<>();
    private Map<EntityId, Business> businessDictionary = new HashMap<>();
    private Map<EntityId, Location> locationDictionary = new HashMap<>();
    private Map<EntityId, Phone> phoneDictionary = new HashMap<>();

    public ResponseDictionary(Client client) {
        this.client = client;
    }

    public void add(Phone phone) {
        phoneDictionary.put(phone.getId(), phone);
    }

    public void add(Location location) {
        locationDictionary.put(location.getId(), location);
    }

    public void add(Person person) {
        personDictionary.put(person.getId(), person);
    }

    public void add(Business business) {
        businessDictionary.put(business.getId(), business);
    }

    public Set<EntityId> getIds() {
        Set<EntityId> ids = new HashSet<>();
        ids.addAll(getPhoneIds());
        ids.addAll(getBusinessIds());
        ids.addAll(getLocationIds());
        ids.addAll(getPersonIds());

        return ids;
    }

    public Set<EntityId> getPhoneIds() {
        return phoneDictionary.keySet();
    }

    public Set<EntityId> getLocationIds() {
        return locationDictionary.keySet();
    }

    public Set<EntityId> getPersonIds() {
        return personDictionary.keySet();
    }

    public Set<EntityId> getBusinessIds() {
        return businessDictionary.keySet();
    }

    public Person getPerson(EntityId entityId) {
        if( personDictionary.containsKey(entityId) ) {
            return personDictionary.get(entityId);
        } else {
            Person proxy = new PersonProxy(entityId, client);
            personDictionary.put(entityId, proxy);
            return proxy;
        }
    }

    public Business getBusiness(EntityId entityId) {
        if( businessDictionary.containsKey(entityId) ) {
            return businessDictionary.get(entityId);
        } else {
            Business proxy = new BusinessProxy(entityId, client);
            businessDictionary.put(entityId, proxy);
            return proxy;
        }
    }

    public Location getLocation(EntityId entityId) {
        if( locationDictionary.containsKey(entityId) ) {
            return locationDictionary.get(entityId);
        } else {
            Location proxy = new LocationProxy(entityId, client);
            locationDictionary.put(entityId, proxy);
            return proxy;
        }
    }

    public Phone getPhone(EntityId entityId) {
        if( phoneDictionary.containsKey(entityId) ) {
            return phoneDictionary.get(entityId);
        } else {
            Phone proxy = new PhoneProxy(entityId, client);
            phoneDictionary.put(entityId, proxy);
            return proxy;
        }
    }

}
