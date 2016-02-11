package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.util.TimePeriod;

import java.util.Map;
import java.util.TreeMap;

/**
 * The interface for Location {@link Entity} classes.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public interface Location extends Entity {

    public enum LocationType {
        LAT_LONG,
        ADDRESS,
        STATE,
        CITY,
        COUNTY,
        NEIGHBORHOOD,
        POSTAL_CODE,
        COUNTRY,
        ZIP_PLUS_4,
        CITY_POSTAL_CODE;

        private static Map<String, LocationType> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("LatLong", LAT_LONG);
            namesMap.put("Address", ADDRESS);
            namesMap.put("State", STATE);
            namesMap.put("City", CITY);
            namesMap.put("County", COUNTY);
            namesMap.put("Neighborhood", NEIGHBORHOOD);
            namesMap.put("PostalCode", POSTAL_CODE);
            namesMap.put("Country", COUNTRY);
            namesMap.put("ZipPlus4", ZIP_PLUS_4);
            namesMap.put("CityPostalCode", CITY_POSTAL_CODE);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, LocationType> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static LocationType forValue(String value) {
            LocationType t = namesMap.get(value);
            if (t == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return t;
        }
    }

    public enum NotReceivingMailReason {
        VACANT,
        NEW_CONSTRUCTION,
        RURAL;

        private static Map<String, NotReceivingMailReason> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Vacant", VACANT);
            namesMap.put("NewConstruction", NEW_CONSTRUCTION);
            namesMap.put("Rural", RURAL);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, NotReceivingMailReason> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static NotReceivingMailReason forValue(String value) {
            NotReceivingMailReason e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }

    public enum AddressUsage {
        RESIDENTIAL,
        BUSINESS;

        private static Map<String, AddressUsage> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Residential", RESIDENTIAL);
            namesMap.put("Business", BUSINESS);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, AddressUsage> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static AddressUsage forValue(String value) {
            AddressUsage e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }

    public enum DeliveryPoint {
        COMMERCIAL_MAIL_DROP,
        PO_BOX_THROWBACK,
        PO_BOX,
        MULTI_UNIT,
        SINGLE_UNIT;

        private static Map<String, DeliveryPoint> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("CommercialMailDrop", COMMERCIAL_MAIL_DROP);
            namesMap.put("POBoxThrowback", PO_BOX_THROWBACK);
            namesMap.put("POBox", PO_BOX);
            namesMap.put("MultiUnit", MULTI_UNIT);
            namesMap.put("SingleUnit", SINGLE_UNIT);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, DeliveryPoint> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static DeliveryPoint forValue(String value) {
            DeliveryPoint e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }
    
    public enum AddressType {
        FIRM,
        GENERAL_DELIVERY,
        HIGH_RISE,
        PO_BOX,
        RURAL_ROUTE,
        STREET;

        private static Map<String, AddressType> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Firm", FIRM);
            namesMap.put("GeneralDelivery", GENERAL_DELIVERY);
            namesMap.put("HighRise", HIGH_RISE);
            namesMap.put("POBox", PO_BOX);
            namesMap.put("RuralRoute", RURAL_ROUTE);
            namesMap.put("Street", STREET);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, AddressType> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static AddressType forValue(String value) {
            AddressType e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }

    public LocationType getType();

    public String getAddress();

    public String getStandardAddressLine1();

    public String getStandardAddressLine2();

    public String getCity();

    public String getPostalCode();

    public String getStateCode();

    public String getCountryCode();

    public String getZip4();

    public String getHouse();

    public String getBoxNumber();

    public TimePeriod getValidFor();

    public Boolean getReceivingMail();

    public NotReceivingMailReason getNotReceivingMailReason();

    public AddressUsage getUsage();

    public DeliveryPoint getDeliveryPoint();

    public AddressType getAddressType();

    public LatLong getLatLong();

    public Boolean getDeliverable();

    public static class LatLong {

        public enum GeoAccuracy {
            ROOF_TOP,
            STREET,
            POSTAL_CODE,
            CITY,
            STATE,
            COUNTRY,
            UNKNOWN;

            private static Map<String, GeoAccuracy> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

            static {
                namesMap.put("RoofTop", ROOF_TOP);
                namesMap.put("Street", STREET);
                namesMap.put("PostalCode", POSTAL_CODE);
                namesMap.put("City", CITY);
                namesMap.put("State", STATE);
                namesMap.put("Country", COUNTRY);
                namesMap.put("Unknown", UNKNOWN);
            }

            @Override
            public String toString() {
                for (Map.Entry<String, GeoAccuracy> entry : namesMap.entrySet()) {
                    if (entry.getValue() == this)
                        return entry.getKey();
                }
                return null;
            }

            public static GeoAccuracy forValue(String value) {
                GeoAccuracy e = namesMap.get(value);
                if (e == null)
                    throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
                return e;
            }
        }

        public LatLong(double latitude, double longitude, GeoAccuracy geoAccuracy) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.geoAccuracy = geoAccuracy;
        }

        public LatLong(double latitude, double longitude) {
            this(latitude, longitude, GeoAccuracy.UNKNOWN);
        }

        private double latitude;
        private double longitude;
        private GeoAccuracy geoAccuracy;

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public GeoAccuracy getGeoAccuracy() {
            return geoAccuracy;
        }

        @Override
        public String toString() {
            return "LatLong{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", geoAccuracy=" + geoAccuracy +
                    '}';
        }
    }
}
