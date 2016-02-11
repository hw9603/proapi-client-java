package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.util.TimePeriod;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The interface for Person {@link Entity} classes.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public interface Person extends LegalEntity {

    public enum Gender {
        FEMALE,
        MALE,
        UNKNOWN;

        private static Map<String, Gender> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Female", FEMALE);
            namesMap.put("Male", MALE);
            namesMap.put("Unknown", UNKNOWN);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, Gender> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static Gender forValue(String value) {
            Gender e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }

    public List<Name> getNames();

    public AgeRange getAgeRange();

    public Gender getGender();

    public static class Name {

        private String salutation;
        private String firstName;
        private String middleName;
        private String lastName;
        private String suffix;
        private TimePeriod validFor;

        public Name(String salutation, String firstName, String middleName, String lastName, String suffix, TimePeriod validFor) {
            this.salutation = salutation;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.suffix = suffix;
            this.validFor = validFor;
        }

        public Name(String firstName, String middleName, String lastName) {
            this(null, firstName, middleName, lastName, null, null);
        }

        public Name(String firstName, String lastName) {
            this(firstName, null, lastName);
        }

        public String getSalutation() {
            return salutation;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getSuffix() {
            return suffix;
        }

        public TimePeriod getValidFor() {
            return validFor;
        }


        public String getFirstName() {
            return firstName;
        }

        @Override
        public String toString() {
            return "Name{" +
                    "salutation='" + salutation + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", suffix='" + suffix + '\'' +
                    ", validFor=" + validFor +
                    '}';
        }
    }

    public static class AgeRange {
        private int start;
        private int end;

        public AgeRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "AgeRange{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
