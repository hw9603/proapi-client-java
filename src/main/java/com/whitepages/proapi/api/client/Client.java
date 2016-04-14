package com.whitepages.proapi.api.client;

import com.whitepages.proapi.api.query.BusinessQuery;
import com.whitepages.proapi.api.query.LocationQuery;
import com.whitepages.proapi.api.query.PersonQuery;
import com.whitepages.proapi.api.query.PhoneQuery;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.data.entity.Business;
import com.whitepages.proapi.data.entity.Location;
import com.whitepages.proapi.data.entity.Person;
import com.whitepages.proapi.data.entity.Phone;

/**
 * <p>The Client class is the starting point for interaction with the Whitepages
 * API, serving as the starting place for making requests and getting results.</p>
 *
 * <p>The key steps for use of the Client are:</p>
 *
 * <ol>
 *     <li>Instantiation of a Client,</li>
 *     <li>Creation of a {@link com.whitepages.proapi.api.query.Query}, and</li>
 *     <li>Execution of the Query via the find methods on the Client.</li>
 * </ol>
 *
 * <p>For example:</p>
 * <pre>
 * {@code
 * Client client = new Client("yourapikey");
 *
 * PersonQuery personQuery = new PersonQuery("Rory", null, "Williams");
 * Response<Person> = client.findPeople(personQuery);
 *
 * List<Person> results = Response.getResults();
 * for( Person p : results ) {
 *     System.out.println(p.getBestName());
 * }
 * }
 * </pre>
 *
 * <p>In production applications, it is typically preferable to instantiate a single
 * Client instance per thread, repeatedly using it for sequential requests to the API.</p>
 *
 * @see com.whitepages.proapi.api.client.Config
 * @see com.whitepages.proapi.api.query.Query
 * @see com.whitepages.proapi.api.response.Response
 */
public class Client {

    private Config config;

    private final ResultFinder<PersonQuery, Response<Person>> personResultFinder;
    private final ResultFinder<BusinessQuery, Response<Business>> businessResultFinder;
    private final ResultFinder<PhoneQuery, Response<Phone>> phoneResultFinder;
    private final ResultFinder<LocationQuery, Response<Location>> locationResultFinder;

    /**
     * Creates a Client instance configured with the given API Key.
     * @param apiKey Your Whitepages API key.
     * @see com.whitepages.proapi.api.client.Config
     */
    public Client(String apiKey) {
        this(new Config(apiKey));
    }

    /**
     * <p>Creates a Client instance with the given Config instance.</p>
     *
     * <p>The Config instance is not duplicated, so alterations to it will
     * affect all Client instances using it.</p>
     *
     * @param config The Config instance to use.
     */
    public Client(Config config) {
        this(
                config,
                ResultFinderFactory.getDefaultPersonResultFinder(),
                ResultFinderFactory.getDefaultBusinessResultFinder(),
                ResultFinderFactory.getDefaultPhoneResultFinder(),
                ResultFinderFactory.getDefaultLocationResultFinder()
        );
    }

    /**
     * <p>Creates a Client instance with the given Config and ResultFinder instances.</p>
     *
     * <p>Normally, one should use the default ResultFinders, however there are
     * advanced cases where alternative ResultFinders may be useful, such
     * as with custom response parsers or stubbed finders for testing.</p>
     *
     * @param config The configuration instance.
     * @param personResultFinder The ResultFinder used for {@link #findPeople} lookups.
     * @param businessResultFinder The ResultFinder used for {@link #findBusinesses} lookups.
     * @param phoneResultFinder The ResultFinder used for {@link #findPhones} lookups.
     * @param locationResultFinder The ResultFinder used for {@link #findLocations} lookups.
     */
    private Client(Config config,
                   ResultFinder<PersonQuery, Response<Person>> personResultFinder,
                   ResultFinder<BusinessQuery, Response<Business>> businessResultFinder,
                   ResultFinder<PhoneQuery, Response<Phone>> phoneResultFinder,
                   ResultFinder<LocationQuery, Response<Location>> locationResultFinder) {
        this.config = config;

        this.personResultFinder = personResultFinder;
        this.businessResultFinder = businessResultFinder;
        this.phoneResultFinder = phoneResultFinder;
        this.locationResultFinder = locationResultFinder;
    }

    public Config getConfig() {
        return config;
    }

    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Person> findPeople(PersonQuery query) throws FindException {
        return personResultFinder.find(query, this);
    }
    
    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @param connectTimeout the number of milliseconds to wait until a connection attempt times out
     * @param readTimeout the number of milliseconds to wait until a read attempt times out
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Person> findPeople(PersonQuery query, int connectTimeout, int readTimeout) throws FindException {
        return personResultFinder.find(query, this, connectTimeout, readTimeout);
    }
    
    
    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Business> findBusinesses(BusinessQuery query) throws FindException {
        return businessResultFinder.find(query, this);
    }
    
    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @param connectTimeout the number of milliseconds to wait until a connection attempt times out
     * @param readTimeout the number of milliseconds to wait until a read attempt times out
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Business> findBusinesses(BusinessQuery query, int connectTimeout, int readTimeout) throws FindException {
        return businessResultFinder.find(query, this, connectTimeout, readTimeout);
    }

    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Phone> findPhones(PhoneQuery query) throws FindException {
        return phoneResultFinder.find(query, this);
    }
    
    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @param connectTimeout the number of milliseconds to wait until a connection attempt times out
     * @param readTimeout the number of milliseconds to wait until a read attempt times out
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Phone> findPhones(PhoneQuery query, int connectTimeout, int readTimeout) throws FindException {
        return phoneResultFinder.find(query, this, connectTimeout, readTimeout);
    }

    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Location> findLocations(LocationQuery query) throws FindException {
        return locationResultFinder.find(query, this);
    }
    
    /**
     * Executes the given query and returns the response.
     * @param query The query to perform.
     * @param connectTimeout the number of milliseconds to wait until a connection attempt times out
     * @param readTimeout the number of milliseconds to wait until a read attempt times out
     * @return The Response object.
     * @throws FindException Thrown if an exception occurs before a Response object could be created.
     */
    public Response<Location> findLocations(LocationQuery query, int connectTimeout, int readTimeout) throws FindException {
        return locationResultFinder.find(query, this, connectTimeout, readTimeout);
    }
}
