package com.whitepages.proapi.data.association;

import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public interface ContactTyped {

    public static enum ContactType {
        OTHER,
        WORK,
        HOME,
        BUSINESS;

        private static Map<String, ContactType> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Other", OTHER);
            namesMap.put("Work", WORK);
            namesMap.put("Home", HOME);
            namesMap.put("Business", BUSINESS);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, ContactType> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static ContactType forValue(String value) {
            return namesMap.get(value);
        }
    }

    public ContactType getContactType();
}
