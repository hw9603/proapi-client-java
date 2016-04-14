package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.util.TimePeriod;

/**
 * The standard concrete implementation of the {@link Location} {@link Entity}.
 * @see com.whitepages.proapi.data.entity.Location
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class LocationImpl extends EntityImpl implements Location {

    private LocationType type;

    private String address;
    private String standardAddressLine1;
    private String standardAddressLine2;
    private String standardAddressLocation;
    private String city;
    private String postalCode;
    private String stateCode;
    private String countryCode;
    private String aptType;
    private String zip4;
    private String house;
    private String streetName;
    private String streetType;
    private String preDir;
    private String postDir;
    private String aptNumber;
    private String boxNumber;

    private TimePeriod validFor;

    private Boolean receivingMail;

    private NotReceivingMailReason notReceivingMailReason;

    private AddressUsage usage;

    private DeliveryPoint deliveryPoint;
    
    private AddressType addressType;

    private LatLong latLong;

    private Boolean deliverable;

    public LocationImpl() {}

    public LocationImpl(EntityId id) {
        super(id);
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public String getStandardAddressLine1() {
        return standardAddressLine1;
    }

    public void setStandardAddressLine1(String standardAddressLine1) {
        this.standardAddressLine1 = standardAddressLine1;
    }

    public String getStandardAddressLine2() {
        return standardAddressLine2;
    }

    public void setStandardAddressLine2(String standardAddressLine2) {
        this.standardAddressLine2 = standardAddressLine2;
    }

    public String getStandardAddressLocation() {
        return standardAddressLocation;
    }

    public void setStandardAddressLocation(String standardAddressLocation) {
        this.standardAddressLocation = standardAddressLocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAptType() {
        return aptType;
    }

    public void setAptType(String aptType) {
        this.aptType = aptType;
    }

    public String getZip4() {
        return zip4;
    }

    public void setZip4(String zip4) {
        this.zip4 = zip4;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getPreDir() {
        return preDir;
    }

    public void setPreDir(String preDir) {
        this.preDir = preDir;
    }

    public String getPostDir() {
        return postDir;
    }

    public void setPostDir(String postDir) {
        this.postDir = postDir;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public Boolean getReceivingMail() {
        return receivingMail;
    }

    public void setReceivingMail(Boolean receivingMail) {
        this.receivingMail = receivingMail;
    }

    public NotReceivingMailReason getNotReceivingMailReason() {
        return notReceivingMailReason;
    }

    public void setNotReceivingMailReason(NotReceivingMailReason notReceivingMailReason) {
        this.notReceivingMailReason = notReceivingMailReason;
    }

    public AddressUsage getUsage() {
        return usage;
    }

    public void setUsage(AddressUsage usage) {
        this.usage = usage;
    }

    public DeliveryPoint getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public LatLong getLatLong() {
        return latLong;
    }

    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

    public Boolean getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(Boolean deliverable) {
        this.deliverable = deliverable;
    }

    public String getName() {
        return this.standardAddressLine1 + ' ' + this.standardAddressLine2 + ' ' + this.city + ' ' + this.stateCode + ' ' + this.postalCode;
    }

}
