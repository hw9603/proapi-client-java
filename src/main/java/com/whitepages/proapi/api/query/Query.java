package com.whitepages.proapi.api.query;

/**
 * <p>The root type for all Queries. The following concrete Query classes are available
 * for use</p>
 * <ul>
 *     <li>{@link com.whitepages.proapi.api.query.PersonQuery}</li>
 *     <li>{@link com.whitepages.proapi.api.query.BusinessQuery}</li>
 *     <li>{@link com.whitepages.proapi.api.query.PhoneQuery}</li>
 *     <li>{@link com.whitepages.proapi.api.query.PhoneLiteQuery}</li>
 *     <li>{@link com.whitepages.proapi.api.query.LocationQuery}</li>
 * </ul>
 *
 * <h2>Example Queries</h2>
 *
 * <p>Find a person by name and zip code:</p>
 * <pre>
 * {@code
 * public PersonQuery queryByNameAndZip(String firstName, String lastName, String zip) {
 *     PersonQuery q = new PersonQuery(firstName, null, lastName);
 *     q.setPostalCode(zip);
 *     return q;
 * }
 * }
 * </pre>
 *
 * <p>Find a location by entity id:</p>
 * <pre>
 * {@code
 * public LocationQuery queryByLocId(EntityId id) {
 *     return new LocationQuery(id);
 * }
 * }
 * </pre>
 *
 * <p>Find a phone number with a boolean switch between a full and a lite phone query:</p>
 * <pre>
 * {@code
 * public PhoneQuery queryPhoneNumber(String number, boolean lite) {
 *     return lite ? new PhoneLiteQuery(number) : new PhoneQuery(number);
 * }
 * }
 * </pre>
 *
 *
 * @see com.whitepages.proapi.api.query.PersonQuery
 * @see com.whitepages.proapi.api.query.BusinessQuery
 * @see com.whitepages.proapi.api.query.PhoneQuery
 * @see com.whitepages.proapi.api.query.PhoneLiteQuery
 * @see com.whitepages.proapi.api.query.LocationQuery
 */
public interface Query {
}
