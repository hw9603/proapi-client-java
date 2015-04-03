package com.whitepages.proapi.data.entity;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * <p>The data class encapsulating {@link Entity} IDs.</p>
 *
 * <p>In addition to being used as an opaque ID, this class
 * presents methods for extracting metadata about the ID.</p>
 *
 * @see com.whitepages.proapi.data.entity.Entity
 */
public class EntityId {

    public enum EntityType {
        PERSON,
        BUSINESS,
        LOCATION,
        PHONE;

        private static Map<String, EntityType> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Person", PERSON);
            namesMap.put("Business", BUSINESS);
            namesMap.put("Location", LOCATION);
            namesMap.put("Phone", PHONE);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, EntityType> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static EntityType forValue(String value) {
            EntityType e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
    }

    public enum Durability {
        DURABLE,
        EPHEMERAL,
        TEMPORARY;

        private static Map<String, Durability> namesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Durable", DURABLE);
            namesMap.put("Ephemeral", EPHEMERAL);
            namesMap.put("Temporary", TEMPORARY);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, Durability> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static Durability forValue(String value) {
            Durability d = namesMap.get(value);
            if (d == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return d;
        }
    }

    private EntityType type;
    private UUID uuid;
    private Durability durability;

    public EntityId(EntityType type, UUID uuid, Durability durability) {
        this.type = type;
        this.uuid = uuid;
        this.durability = durability;
    }

    private static IllegalArgumentException invalidEntityIdStringException =
            new IllegalArgumentException("EntityId string expected to be in format \"<type>.<uuid>.<durability>\"");

    public static EntityId fromString(String key) {
        String[] idPieces = key.split("\\.");
        if (idPieces.length != 3)
            throw invalidEntityIdStringException;

        EntityType type = EntityType.valueOf(idPieces[0].toUpperCase());
        UUID uuid = UUID.fromString(idPieces[1]);
        Durability durability = Durability.valueOf(idPieces[2].toUpperCase());

        if (type == null || uuid == null || durability == null)
            throw invalidEntityIdStringException;

        return new EntityId(type, uuid, durability);
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Durability getDurability() {
        return durability;
    }

    public void setDurability(Durability durability) {
        this.durability = durability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityId entityId = (EntityId) o;

        if (type != entityId.type) return false;
        if (uuid != null ? !uuid.equals(entityId.uuid) : entityId.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        //TODO: Capitalize correctly to match API.
        return String.format("%s.%s.%s", type, uuid, durability);
    }
}
