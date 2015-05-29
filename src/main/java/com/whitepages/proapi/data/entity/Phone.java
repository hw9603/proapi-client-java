package com.whitepages.proapi.data.entity;

import com.whitepages.proapi.data.association.LocationAssociation;

import java.util.Map;
import java.util.TreeMap;

/**
 * The interface for Phone {@link Entity} classes.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public interface Phone extends Entity {

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

    public String getExtension();

    public String getCarrier();

    public Reputation getReputation();

    public Boolean getDoNotCall();

    public Boolean getPrepaid();

    public Boolean getValid();

    public LocationAssociation getBestLocationAssociation();

    public Location getBestLocation();

    public static class Reputation {

        public Reputation(int spamScore,
			  int spamIndex,
			  int volumeScore,
			  int reportCount) {
            this.spamScore = spamScore;
	    this.spamIndex = spamIndex;
	    this.volumeScore = volumeScore;
	    this.reportCount = reportCount;
        }

        public Integer getSpamScore() {
            return spamScore;
        }

	public Integer getSpamIndex() {
            return spamIndex;
        }

	public Integer getVolumeScore() {
            return volumeScore;
        }

	public Integer getReportCount() {
            return reportCount;
        }

	private Integer spamScore;
	private Integer spamIndex;
	private Integer volumeScore;
	private Integer reportCount;

    }
}
