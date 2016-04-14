package com.whitepages.proapi.api.client.responsedecoders.jsonjacksondecoder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.whitepages.proapi.api.client.*;
import com.whitepages.proapi.api.client.util.HttpResponse;
import com.whitepages.proapi.api.response.Response;
import com.whitepages.proapi.api.response.ResponseDictionary;
import com.whitepages.proapi.api.response.ResponseMessages;
import com.whitepages.proapi.data.association.BusinessAssociation;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.association.PersonAssociation;
import com.whitepages.proapi.data.association.PhoneAssociation;
import com.whitepages.proapi.data.entity.*;
import com.whitepages.proapi.data.message.Message;
import com.whitepages.proapi.data.util.TimePeriod;

import java.io.IOException;
import java.util.*;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public abstract class ProAPI20JSONStreamResponseDecoder<R extends Response<?>> implements ResponseDecoder<R, HttpResponse> {

    private static SimpleModule dictionaryDeserializationModule(ObjectMapper mapper, Client client) {
        DictionaryDeserializer deserializer = new DictionaryDeserializer(mapper, client);
        SimpleModule module = new SimpleModule("DictionaryDeserializerModule");
        module.addDeserializer(ResponseDictionary.class, deserializer);

        return module;
    }

    private static SimpleModule timePeriodDeserializationModule(ObjectMapper mapper) {
        SimpleModule module = new SimpleModule("DictionaryDeserializerModule");
        module.addDeserializer(TimePeriod.class, new TimePeriodDeserializer(mapper));
        return module;
    }

    private static SimpleModule annotationsMixinModule() {
        SimpleModule module = new SimpleModule("AnnotationsMixinModule");
        module.setMixInAnnotation(EntityId.class, EntityIdAnnotations.class);

        module.setMixInAnnotation(ResponseMessages.class, MessageResponseAnnotations.class);
        module.setMixInAnnotation(Message.class, MessageAnnotations.class);

        module.setMixInAnnotation(Phone.class, PhoneAnnotations.class);
        module.setMixInAnnotation(Phone.Reputation.class, PhoneReputationAnnotations.class);

        module.setMixInAnnotation(Location.class, LocationAnnotations.class);
        module.setMixInAnnotation(Location.LatLong.class, LatLongAnnotations.class);

        module.setMixInAnnotation(Person.class, PersonAnnotations.class);
        module.setMixInAnnotation(Person.Name.class, PersonNameAnnotations.class);
        module.setMixInAnnotation(Person.AgeRange.class, PersonAgeRangeAnnotations.class);

        module.setMixInAnnotation(Business.class, BusinessAnnotations.class);

        module.setMixInAnnotation(LocationAssociation.class, LocationAssociationAnnotations.class);
        module.setMixInAnnotation(PhoneAssociation.class, PhoneAssociationAnnotations.class);
        module.setMixInAnnotation(PersonAssociation.class, PersonAssociationAnnotations.class);
        module.setMixInAnnotation(BusinessAssociation.class, BusinessAssociationAnnotations.class);

        return module;
    }

    private static SimpleModule enumDeserializationModule() {
        SimpleModule module = new SimpleModule("EnumDeserializationModule");
        module.setDeserializers(new EnumDeserializers());
        return module;
    }

    protected static ObjectMapper getDeserializer(Client client) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        mapper.registerModule(dictionaryDeserializationModule(mapper, client));
        mapper.registerModule(annotationsMixinModule());
        mapper.registerModule(enumDeserializationModule());
        mapper.registerModule(timePeriodDeserializationModule(mapper));

        return mapper;
    }

    static class EnumDeserializers extends SimpleDeserializers {

        @SuppressWarnings("unchecked")
        @Override
        public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
            return createDeserializer((Class<Enum>) type);
        }

        private <T extends Enum<T>> JsonDeserializer<?> createDeserializer(Class<T> enumClass) {
            T[] enumValues = enumClass.getEnumConstants();
            HashMap<String, T> map = createEnumValuesMap(enumValues);
            return new EnumDeserializer(new EnumCaseInsensitiveResolver<T>(enumClass, enumValues, map));
        }

        private <T extends Enum<T>> HashMap<String, T> createEnumValuesMap(T[] enumValues) {
            HashMap<String, T> map = new HashMap<String, T>();
            // from last to first, so that in case of duplicate values, first wins
            for (int i = enumValues.length; --i >= 0; ) {
                T e = enumValues[i];
                map.put(e.toString(), e);
            }
            return map;
        }

        public static class EnumCaseInsensitiveResolver<T extends Enum<T>> extends EnumResolver<T> {
            protected EnumCaseInsensitiveResolver(Class<T> enumClass, T[] enums, HashMap<String, T> map) {
                super(enumClass, enums, map);
            }

            @Override
            public T findEnum(String key) {
                for (Map.Entry<String, T> entry : _enumsById.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(key)) {
                        return entry.getValue();
                    }
                }
                return (_enumsById.keySet().contains("UNKNOWN")) ? _enumsById.get("UNKNOWN") : null;
            }
        }
    }

    static class DictionaryDeserializer extends StdDeserializer<ResponseDictionary> {

        private final ObjectMapper mapper;
        private final Client client;

        DictionaryDeserializer(ObjectMapper mapper, Client client) {
            super(ResponseDictionary.class);
            this.mapper = mapper;
            this.client = client;
        }

        @Override
        public ResponseDictionary deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            ResponseDictionary dictionary = new ResponseDictionary(client);
            InjectableValues injectableValues = new InjectableValues.Std().addValue(ResponseDictionary.class, dictionary);
            ObjectReader reader = mapper.reader(injectableValues);

            JsonNode root = mapper.readTree(jp);
            Iterator<Map.Entry<String, JsonNode>> it = root.fields();

            while(it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                EntityId id = EntityId.fromString(entry.getKey());
                JsonNode entityNode = entry.getValue();

                switch(id.getType()) {
                    default:
                        assert(false);
                        break;
                    case PERSON:
                        dictionary.add(reader.treeToValue(entityNode, PersonImpl.class));
                        break;
                    case PHONE:
                        dictionary.add(reader.treeToValue(processEntityWithLegalEntities(entityNode), PhoneImpl.class));
                        break;
                    case LOCATION:
                        dictionary.add(reader.treeToValue(processEntityWithLegalEntities(entityNode), LocationImpl.class));
                        break;
                    case BUSINESS:
                        dictionary.add(reader.treeToValue(entityNode, BusinessImpl.class));
                        break;
                }
            }

            return dictionary;
        }

        private static final Map<String, String> legalEntitiesPathMap = new HashMap<String, String>(){{
            put("Phone","belongs_to");
            put("Location","legal_entities_at");
        }};

        JsonNode processEntityWithLegalEntities(JsonNode entity) {
            JsonNodeFactory factory = JsonNodeFactory.instance;

            ObjectNode entityObject = (ObjectNode) entity;
            ArrayNode peopleAssociations = new ArrayNode(factory);
            ArrayNode businessAssociations = new ArrayNode(factory);

            String legalEntitiesPath = legalEntitiesPathMap.get(entity.path("id").path("type").asText());

            if (entity.get(legalEntitiesPath).isArray()) {
                for (JsonNode node : entity.get(legalEntitiesPath)) {
                    JsonNode type = node.path("id").path("type");
                    if (type.asText().equalsIgnoreCase("Person")) {
                        peopleAssociations.add(node);
                    } else if (type.asText().equalsIgnoreCase("Business")) {
                        businessAssociations.add(node);
                    }
                }
            }

            addArrayNodeToObject(peopleAssociations, entityObject, "people_associations");
            addArrayNodeToObject(businessAssociations, entityObject, "business_associations");

            return entityObject;
        }

        private void addArrayNodeToObject(ArrayNode array, ObjectNode object, String fieldName) {
            if (array.size() > 0) {
                ArrayNode newArray = object.putArray(fieldName);
                for (JsonNode node : array) {
                    newArray.add(node);
                }
            }
        }

    }

    static class TimePeriodDeserializer extends StdDeserializer<TimePeriod> {

        private ObjectMapper mapper;

        TimePeriodDeserializer(ObjectMapper mapper) {
            super(TimePeriod.class);
            this.mapper = mapper;
        }

        @Override
        public TimePeriod deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode root = mapper.readTree(jp);

            Date start = null;
            Date end = null;

            JsonNode startNode = root.path("start");
            if (!startNode.isNull() && !startNode.isMissingNode()) {
                start = extractDateFromJsonNode(startNode);
            }

            JsonNode stopNode = root.path("stop");
            if (!stopNode.isNull() && !stopNode.isMissingNode()) {
                end = extractDateFromJsonNode(stopNode);
            }

            return new TimePeriod(start, end);
        }

        private static Date extractDateFromJsonNode(JsonNode node) {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, node.path("year").intValue());
            calendar.set(Calendar.MONTH, node.path("month").intValue() - 1);
            calendar.set(Calendar.DAY_OF_MONTH, node.path("day").intValue());
            return calendar.getTime();
        }
    }

    static class UnixTimestampDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String data = jp.getText().trim();

            try {
                return new Date(Long.valueOf(data) * 1000);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    static class ErrorConverter {

        private ObjectMapper mapper;

        ErrorConverter(ObjectMapper mapper) {
            this.mapper = mapper;
        }

        @SuppressWarnings("unchecked")
        public ResponseMessages deserialize(JsonNode error) throws JsonProcessingException, ResponseException {
            Map<String, String> data = mapper.treeToValue(error, HashMap.class);

            String msg = data.get("message");

            Message message = new Message(
                    Message.Severity.ERROR,
                    readErrorType(data.get("name"), msg),
                    null,
                    msg
            );

            List<Message> messages = new ArrayList<>();
            messages.add(message);
            return new ResponseMessages(messages);
        }

        /**
         * <p>Converts the error type name into a MessageType.</p>
         *
         * <p>Some error types raise a ResponseException subclass instead. The
         * heuristic is to raise an exception if</p>
         * <ol>
         *     <li>The error would never present with other errors or messages, and</li>
         *     <li>The error is severe and fatal.</li>
         * </ol>
         *
         * @param typeName The error name from the API JSON.
         * @param message The exception message, if an exception is thrown.
         * @return The MessageType enum value.
         * @throws ResponseException
         */
        private Message.MessageType readErrorType(String typeName, String message) throws ResponseException {
            if(typeName == null) {
                throw new ResponseException(message);
            }

            switch(typeName) {
                case "InternalError":
                    throw new InternalException(message);
                case "TimeoutError":
                    throw new TimeoutException(message);
                case "AuthError":
                    throw new AuthException(message);
                case "QuotaExceededError":
                    throw new QuotaExceededException(message);
                case "InputError":
                    return Message.MessageType.INPUT;
                case "InputFieldError":
                    return Message.MessageType.INPUT_FIELD;
                case "InvalidParametersError":
                    return Message.MessageType.INVALID_PARAMETERS;
                case "MissingFieldError":
                    return Message.MessageType.MISSING_FIELD;
                case "EntityIDParseError":
                    return Message.MessageType.ENTITY_ID_PARSE;
                case "EntityIDTypeMismatchError":
                    return Message.MessageType.ENTITY_ID_TYPE_MISMATCH;
                case "NonDurableEntityIDLookupError":
                    return Message.MessageType.NON_DURABLE_ENTITY_ID_LOOKUP;
                default:
                    throw new ResponseException(message);
            }
        }

    }

    class ResponseDeserializer {

        private final static String RESULT_PATH = "results";
        private final static String DICTIONARY_PATH = "dictionary";
        private final static String MESSAGES_PATH = "messages";
        private final static String ERROR_PATH = "error";

        private final JsonNode result;
        private final Client client;
        private final ObjectMapper mapper;

        ResponseDeserializer(JsonNode result, ObjectMapper mapper, Client client) {
            this.result = result;
            this.client = client;
            this.mapper = mapper;
        }

        public Response<Phone> deserializePhoneResponse() throws IOException {
            ResponseDictionary dictionary = readDictionary();
            return new Response<>(client, phonesFromDictionary(readResultIDs(), dictionary), dictionary, readMessages());
        }

        public Response<Location> deserializeLocationResponse() throws IOException {
            ResponseDictionary dictionary = readDictionary();
            return new Response<>(client, locationsFromDictionary(readResultIDs(), dictionary), dictionary, readMessages());
        }

        public Response<Person> deserializePersonResponse() throws IOException {
            ResponseDictionary dictionary = readDictionary();
            return new Response<>(client, peopleFromDictionary(readResultIDs(), dictionary), dictionary, readMessages());
        }

        public Response<Business> deserializeBusinessResponse() throws IOException {
            ResponseDictionary dictionary = readDictionary();
            return new Response<>(client, businessesFromDictionary(readResultIDs(), dictionary), dictionary, readMessages());
        }

        public <T extends Entity> Response<T>deserializeErrorResponse() throws IOException, ResponseException {
            return new Response<T>(client, new ArrayList<T>(), new ResponseDictionary(client), readError());
        }

        @SuppressWarnings("unchecked")
        private List<String> readResultIDs() throws JsonProcessingException {
            return mapper.treeToValue(result.path(RESULT_PATH), List.class);
        }

        private ResponseDictionary readDictionary() throws JsonProcessingException {
            return mapper.treeToValue(result.path(DICTIONARY_PATH), ResponseDictionary.class);
        }

        private ResponseMessages readMessages() throws JsonProcessingException {
            return mapper.treeToValue(result.path(MESSAGES_PATH), ResponseMessages.class);
        }

        @SuppressWarnings("unchecked")
        private ResponseMessages readError() throws JsonProcessingException, ResponseException {
            return new ErrorConverter(mapper).deserialize(result.path(ERROR_PATH));
        }

        private List<Phone> phonesFromDictionary(List<String> ids, ResponseDictionary dictionary) {
            List<Phone> phones = new ArrayList<>(ids.size());
            for (String id : ids) {
                EntityId entityId = EntityId.fromString(id);
                phones.add(dictionary.getPhone(entityId));
            }
            return phones;
        }

        private List<Location> locationsFromDictionary(List<String> ids, ResponseDictionary dictionary) {
            List<Location> locations = new ArrayList<>(ids.size());
            for (String id : ids) {
                EntityId entityId = EntityId.fromString(id);
                locations.add(dictionary.getLocation(entityId));
            }
            return locations;
        }

        private List<Business> businessesFromDictionary(List<String> ids, ResponseDictionary dictionary) {
            List<Business> businesses = new ArrayList<>(ids.size());
            for (String id : ids) {
                EntityId entityId = EntityId.fromString(id);
                businesses.add(dictionary.getBusiness(entityId));
            }
            return businesses;
        }

        private List<Person> peopleFromDictionary(List<String> ids, ResponseDictionary dictionary) {
            List<Person> people = new ArrayList<>(ids.size());
            for (String id : ids) {
                EntityId entityId = EntityId.fromString(id);
                people.add(dictionary.getPerson(entityId));
            }
            return people;
        }

    }

}
