package com.whitepages.proapi.data.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.whitepages.proapi.data.entity.Phone;
import com.whitepages.proapi.data.entity.Phone.LineType;

/**
 * <p>Messages are generated as a result of requests to the API and contain both
 * a message body and meta data about that message.</p>
 *
 * <p>Messages are found on the {@link com.whitepages.proapi.api.response.Response}
 * via the {@link com.whitepages.proapi.api.response.Response#getResponseMessages()}
 * accessor method.</p>
 *
 * <p>Messages have a number of meta-data properties, including:</p>
 * <ul>
 *     <li>Severity: the severity of the message,</li>
 *     <li>Type: the machine readable categorization of the message,,</li>
 *     <li>Code: An optional machine readable sub-categorization coding of the message, and</li>
 *     <li>Ancillary Data: An optional weakly-typed Map of data specific to the message.</li>
 * </ul>
 *
 * @see com.whitepages.proapi.api.response.Response
 * @see com.whitepages.proapi.api.response.ResponseMessages
 */
public class Message {

    /**
     * The message severity.
     */
	@JsonDeserialize(using=MessageSeverityDeserializer.class)
    public enum Severity {
        ERROR,
        WARNING,
        INFO,
        DEBUG;
    	
    	public static Severity getCaseInsensitive(String value) {
    		
        	for (Severity entry : Severity.values()) {
        		String key = entry.name();
        		if (key.equalsIgnoreCase(value))
                    return entry;
            }
            return null;
        }
    }

    /**
     * The primary machine readable categorization of messages.
     */
	@JsonDeserialize(using=MessageTypeDeserializer.class)
    public enum MessageType {
        UNKNOWN,
        INTERNAL,
        TIMEOUT,
        DEBUG,
        AUTH,
        QUOTA_EXCEEDED,
        INPUT,
        INPUT_FIELD,
        INVALID_PARAMETERS,
        MISSING_FIELD,
        ENTITY_ID_PARSE,
        ENTITY_ID_TYPE_MISMATCH,
        NON_DURABLE_ENTITY_ID_LOOKUP;
    	
    	public static MessageType getCaseInsensitive(String value) {
    		
        	for (MessageType entry : MessageType.values()) {
        		String key = entry.name();
        		if (key.equalsIgnoreCase(value))
                    return entry;
            }
            return null;
        }
    }

    /**
     * The secondary machine readable categorization of messages.
     */
    public enum Code {
        UNKNOWN,
        INVALID_INPUT,
        TOO_SHORT_INPUT,
        MISSING_INPUT,
        MISSING_DATA
    }

    private final Severity severity;
    private final MessageType type;
    private final Code code;
    private final String message;
    private final Map<String, Object> ancillaryData;

    public Message(Severity severity, MessageType type, Code code, String message) {
        this(severity, type, code, message, new HashMap<String, Object>());
    }

    public Message(Severity severity, MessageType type, Code code, String message, Map<String, Object> ancillaryData) {
        this.severity = severity;
        this.type = type;
        this.code = code;
        this.message = message;
        this.ancillaryData = ancillaryData;
    }

    /**
     * Returns the message severity.
     * @return The message severity.
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Returns the primary machine readable message categorization.
     * @return The message type.
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Returns the optional secondary machine readable message categorization.
     * @return The message code.
     */
    public Code getCode() {
        return code;
    }

    /**
     * Returns the human readable body text of the message.
     * @return The message body.
     */
    public String getMessage() {
        return message;
    }

    /**
     * <p>Returns the context dependent ancillary data for this message,
     * if any exists.</p>
     *
     * <p>Most messages do not contain any ancillary data; however the
     * occasional message does have extra data associated with it. While
     * the keys present, and the types for the values, will vary between
     * message types, for any given message type these properties can be
     * expected to stay consistent.</p>
     *
     * @return The ancillary data hash.
     */
    public Map<String, Object> getAncillaryData() {
        return ancillaryData;
    }

    @Override
    public String toString() {
        return String.format("<%s %s %s (%s)>", type, code, severity, message);
    }
}

class MessageSeverityDeserializer extends JsonDeserializer<Message.Severity> {

    @Override
    public Message.Severity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
    		throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Message.Severity type = null;
        try{
            if(node != null){
            	String nodeValue = node.asText().toLowerCase();
                type = Message.Severity.getCaseInsensitive(nodeValue);
                if (type != null) {
                    return type;
                }
            }
        }catch(Exception e){
            type = null;
        }
        return type;
    }

}

class MessageTypeDeserializer extends JsonDeserializer<Message.MessageType> {

    @Override
    public Message.MessageType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
    		throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Message.MessageType type = null;
        try{
            if(node != null){
            	String nodeValue = node.asText().toLowerCase();
                type = Message.MessageType.getCaseInsensitive(nodeValue);
                if (type != null) {
                    return type;
                }
            }
        }catch(Exception e){
            type = null;
        }
        return type;
    }

}