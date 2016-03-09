package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.association.LocationAssociation;

import java.util.List;

/**
 * The standard concrete implementation of the {@link Person} {@link Entity}.
 * @see com.whitepages.proapi.data.entity.Person
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class PersonImpl extends EntityImpl implements Person {

    private List<Name> names;
    private AgeRange ageRange;
    private Gender gender;
    private String bestName;
    private LocationAssociation bestLocationAssociation;
    private Location bestLocation;

    public PersonImpl(){};

    public PersonImpl(EntityId id) {
        super(id);
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBestName() {
        return bestName;
    }

    public void setBestName(String bestName) {
        this.bestName = bestName;
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
        return bestName;
    }
}
