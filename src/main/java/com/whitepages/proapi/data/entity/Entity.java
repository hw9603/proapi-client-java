package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.api.client.FindException;
import com.whitepages.proapi.data.association.Association;
import com.whitepages.proapi.data.association.PersonAssociation;
import com.whitepages.proapi.data.association.BusinessAssociation;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;

import java.util.List;

/**
 * <p>The root interface for all Entities (e.g. {@link Person}, {@link Business},
 * {@link Phone}, and {@link Location}).</p>
 *
 * <p>The Whitepages API presents its data in a directed graph form, whereby:</p>
 * <ul>
 *     <li>Each node is an {@link Entity}, and</li>
 *     <li>each Edge is an {@link Association}.</li>
 * </ul>
 * <p></p>Entities contain data intrinsic to the entity, whereas associations
 * contain data pertaining to the relationship between its source and destination
 * entities</p>
 *
 * <h2>Properties</h2>
 *
 * <p>Entity properties are optionally filled in; factors include
 * the query type and the API Key configuration. When a value is not
 * available, its accessor is filled in with a {@code null} value,
 * therefore it is a good idea to check the nullness of property
 * values before using them.</p>
 *
 * <h2>Traversal</h2>
 *
 * <p>There are two ways to traverse the graph:</p>
 * <ol>
 *     <li>Explicit traversal through the association, and</li>
 *     <li>Direct traversal to the associated entity.</li>
 * </ol>
 * <p>The former is useful when information on the association is desired;
 * however, one is often interested only in the associated entity itself,
 * in which case direct traversal is clearer.</p>
 *
 * <p>For example, the following two lines have the same effect:</p>
 * <pre>
 * {@code
 * Person p1 = phone.getPersonAssociations().get(0).getPerson();
 * Person p2 = phone.getPeople().get(0);
 * p1 == p2 //is true
 * }
 * </pre>
 * <p>Careful readers of the above example will note that extra safety code is needed
 * to check that the returned lists are non-null and non-empty before
 * calling {@code get()}.</p>
 *
 * <h2>Proxies</h2>
 *
 * <p>The complete entity graph, traversed to its full extent, would
 * be incredibly large, thus, in practice, only a subset of this graph
 * is presented in the Response.
 * As a result, in some cases you may encounter associations whose nodes
 * are not available to you. When this occurs, an appropriate {@link EntityProxy}
 * instance is put into its place. To test if an entity is a proxy, use
 * the {@link #isProxy} method.</p>
 *
 * <p>Entity proxies satisfy the appropriate entity interface, returning
 * null for all the values&mdash;acting as if the values were masked
 * from you. The exception to this rule is the ID of the associated entity,
 * which is always present on a proxy value.</p>
 *
 * <p>To load an entity, call the {@link #load} method. <em>This method
 * will make a call to the remote service</em>, fetching information for
 * the associated entity ID. To test if a value has been loaded, the
 * {@link #isLoaded} method may be used.</p>
 *
 * @see com.whitepages.proapi.data.entity.Person
 * @see com.whitepages.proapi.data.entity.Business
 * @see com.whitepages.proapi.data.entity.Phone
 * @see com.whitepages.proapi.data.entity.Location
 * @see com.whitepages.proapi.data.association.Association
 * @see com.whitepages.proapi.data.entity.EntityProxy
 */
public interface Entity {

    public EntityId getId();

    public List<PersonAssociation> getPersonAssociations();

    public List<BusinessAssociation> getBusinessAssociations();

    public List<LocationAssociation> getLocationAssociations();

    public List<PhoneAssociation> getPhoneAssociations();

    /**
     * A convenience method for getting the concatenation of
     * the association lists.
     * @return Concatenated list of all associations.
     */
    public List<Association> getAssociations();

    public List<Person> getPeople();

    public List<Business> getBusinesses();

    public List<Location> getLocations();

    public List<Phone> getPhones();

    /**
     * A convenience method for getting the concatenation of
     * the entity lists.
     * @return Concatenated list of all entities associated with this entity.
     */
    public List<Entity> getEntities();

    /**
     * A standardized method for getting the primary, human readable,
     * formatted String version of this entity.
     * @return The human formatted string.
     */
    public String getName();

    /**
     * Returns true if this entity instance is a proxy instance.
     * @return true if this entity instance is a proxy instance.
     * @see #isLoaded
     * @see #load
     * @see com.whitepages.proapi.data.entity.EntityProxy
     */
    public boolean isProxy();

    /**
     * If the called on a proxy, return true only
     * if the backing entity has been fetched. If called
     * on a concrete entity, then always return true.
     * @return load status for this entity.
     * @see #isProxy
     * @see #load
     * @see com.whitepages.proapi.data.entity.EntityProxy
     */
    public boolean isLoaded();

    /**
     * <p>Causes this instance to load its data by contacting
     * the remote service and requesting information.
     * Once loaded, subsequent calls to this method have
     * no effect.</p>
     *
     * <p>This method is useful for proxy entities found
     * at the edge of the graph.</p>
     *
     * @throws FindException If the lookup fails before it is able to generate a {@link com.whitepages.proapi.api.response.Response} object.
     * @see #isProxy
     * @see #isLoaded
     * @see com.whitepages.proapi.data.entity.EntityProxy
     */
    public void load() throws FindException;

}
