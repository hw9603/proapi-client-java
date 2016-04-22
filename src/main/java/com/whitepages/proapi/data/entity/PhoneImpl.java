package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.association.LocationAssociation;

/**
 * The standard concrete implementation of the {@link Phone} {@link Entity}.
 * @see com.whitepages.proapi.data.entity.Phone
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class PhoneImpl extends EntityImpl implements Phone {

    private LineType lineType;

    private String phoneNumber;
    private String countryCallingCode;
    private String carrier;

    private Reputation reputation;

    private Boolean doNotCall;
    private Boolean prepaid;
    private Boolean valid;
    private Boolean connected;
    
    private Location bestLocation;
    private LocationAssociation bestLocationAssociation;

    public PhoneImpl() {}

    public PhoneImpl(EntityId id) {
        super(id);
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Reputation getReputation() {
        return reputation;
    }

    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
    }

    public Boolean getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(Boolean doNotCall) {
        this.doNotCall = doNotCall;
    }

    public Boolean getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getConnected() {
    	return connected;
    }
    
    public void setConnected(Boolean connected) {
    	this.connected = connected;
    }
    
    public LocationAssociation getBestLocationAssociation() {
        return bestLocationAssociation;
    }

    public void setBestLocationAssociation(LocationAssociation bestLocationAssociation) {
        this.bestLocationAssociation = bestLocationAssociation;
        bestLocation = null;
    }

    public Location getBestLocation() {
        if (bestLocation == null && bestLocationAssociation != null) {
            bestLocation = bestLocationAssociation.getLocation();
        }
        return bestLocation;
    }

    public String getName() {
        return phoneNumber;
    }

}
