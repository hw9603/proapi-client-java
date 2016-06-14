package com.whitepages.proapi.integrationtests;

import com.whitepages.proapi.api.client.Client;
import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Entity;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.data.entity.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class PersonLookupTest extends EntityLookupTest<Person> {

    @Test
    public void resultShouldContainJohnSmith() throws FindException {
        boolean found = false;
        for(Entity result : getResponse().getResults()) {
            if(result.getName().contains("John") && result.getName().contains("Smith")) {
                found = true;
                break;
            }
        }
        assertThat("Should find John Smith in results", found);
    }

    @Test
    public void readmeExample() {
        Client client = new Client(ClientIntegrationTestHelper.getApiKey());

        PersonQuery personQuery = new PersonQuery("Rory", null, "Williams", null, "WA", null);

        Response<Person> response = null;
        try {
            response = client.findPeople(personQuery);
        } catch (FindException e) {
            e.printStackTrace();
        }

        if (response != null && response.isSuccess()) {
            List<Person> results = response.getResults();
            for (Person p : results) {
                System.out.println(p.getName());
            }
        }
    }
    
    @Test
    public void StreetLine1_query_works_as_expected() throws FindException {
    	PersonQuery q = new PersonQuery("Rob", null, "Eleveld", "1021 NE 72nd St", null, null, null, "98115");
    	q.setUseHistorical(true);
    	Response<Person> response = getClient().findPeople(q);
    	List<Person> results = response.getResults();
    	for (Person person : results) {
    		System.out.println(person.getName());
    		List<Location> locations = person.getLocations();
    		for (Location location : locations) {
    			System.out.println(location.getStandardAddressLine1());
    		}
    	}
    	assertThat("There should be at least one person", results.size() > 0);
        
    }

    @Override
    protected Response<Person> performQuery() throws FindException {
        return getClient().findPeople(getPersonQuery1(), 10000, 10000);
    }

    protected PersonQuery getPersonQuery1() {
        PersonQuery q = new PersonQuery("John", null, "Smith", null, "WA", null);
        return q;
    }

}
