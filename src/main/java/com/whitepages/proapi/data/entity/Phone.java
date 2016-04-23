package com.whitepages.proapi.data.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.whitepages.proapi.data.association.LocationAssociation;
import com.whitepages.proapi.data.entity.Phone.LineType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class LineTypeDeserializer extends JsonDeserializer<Phone.LineType> {

    @Override
    public Phone.LineType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
    		throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Phone.LineType type = null;
        try{
            if(node != null){
            	String nodeValue = node.asText().toLowerCase();
                type = Phone.LineType.getCaseInsensitive(nodeValue);
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

/**
 * The interface for Phone {@link Entity} classes.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public interface Phone extends Entity {

	@JsonDeserialize(using=LineTypeDeserializer.class)
    public enum LineType {
        LANDLINE,
        MOBILE,
        VOICEMAIL,
        TOLL_FREE,
        PREMIUM,
        NON_FIXED_VOIP,
        FIXED_VOIP,
        OTHER;

        private static Map<String, LineType> namesMap = new TreeMap<String, LineType>(String.CASE_INSENSITIVE_ORDER);

        static {
            namesMap.put("Landline", LANDLINE);
            namesMap.put("Mobile", MOBILE);
            namesMap.put("Voicemail", VOICEMAIL);
            namesMap.put("TollFree", TOLL_FREE);
            namesMap.put("Premium", PREMIUM);
            namesMap.put("NonFixedVoip", NON_FIXED_VOIP);
            namesMap.put("FixedVoip", FIXED_VOIP);
            namesMap.put("Other", OTHER);
        }

        @Override
        public String toString() {
            for (Map.Entry<String, LineType> entry : namesMap.entrySet()) {
                if (entry.getValue() == this)
                    return entry.getKey();
            }
            return null;
        }

        public static LineType getCaseInsensitive(String value) {
        	for (Map.Entry<String, LineType> entry : namesMap.entrySet()) {
        		String key = entry.getKey();
        		if (key.equalsIgnoreCase(value))
                    return namesMap.get(key);
            }
            return null;
        }
        
        public static LineType forValue(String value) {
            LineType e = namesMap.get(value);
            if (e == null)
                throw new IllegalArgumentException(String.format("Invalid enum string. Got %s, expected ", value, namesMap.keySet()));
            return e;
        }
        
        

    }

    public LineType getLineType();

    public String getPhoneNumber();

    public String getCountryCallingCode();

    public String getCarrier();

    public Reputation getReputation();

    public Boolean getDoNotCall();

    public Boolean getPrepaid();

    public Boolean getValid();

    public Boolean getConnected();
    
    public static class ReputationDetails {
    	
    	public ReputationDetails() {
    		
    	}
    	
    	public ReputationDetails(int score,
    			String type,
    			String category) {
    		this.score = score;
    		this.type = type;
    		this.category = category;
    	}
    	
    	private Integer score;
    	private String type;
    	private String category;
    	
    	public Integer getScore() {
    		return this.score;
    	}
    	
    	public String getType() {
    		return this.type;
    	}
    	
    	public String getCategory() {
    		return this.category;
    	}
    	
        @Override
        public String toString() {
            return "ReputationDetails{" +
                    "score=" + score +
                    ", type=" + type +
                    ", category=" + category +
                    '}';
        }

    }
    public static class Reputation {

    	public Reputation() {
    		
    	}
    	
    	public Reputation(int level,
        		int volumeScore,
				int reportCount) {
			this(level, volumeScore, reportCount, null);
        }
    	
        public Reputation(int level,
        		int volumeScore,
				int reportCount,
				List<ReputationDetails> details) {
			this.level = level;
			this.volumeScore = volumeScore;
			this.reportCount = reportCount;
			this.details = details;
        }

        public Integer getVolumeScore() {
	            return volumeScore;
	        }
	
		public Integer getReportCount() {
	            return reportCount;
	        }

		public Integer getLevel() {
			return level;
		}
		
		public List<ReputationDetails> getDetails() {
			return this.details;
		}
		
		private List<ReputationDetails> details;
		private Integer volumeScore;
		private Integer reportCount;
		private Integer level;
	
    }
}
