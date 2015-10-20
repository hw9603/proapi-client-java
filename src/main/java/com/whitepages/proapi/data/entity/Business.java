package com.whitepages.proapi.data.entity;

import java.util.List;

import com.whitepages.proapi.data.entity.Phone.ReputationDetails;

/**
 * The interface for Business {@link Entity} classes.
 * @see com.whitepages.proapi.data.entity.Entity
 */
public interface Business extends LegalEntity {
	
	public List<Url> getUrls();
	
	public static class Url {
    
		public Url() {
			
		}
		
        public Url(String url,
        		String displayName,
				String type) {
			this.url = url;
			this.displayName = displayName;
			this.type = type;
	    }

        public String getUrl() {
            return url;
        }
	
		public String getDisplayName() {
	            return displayName;
	        }
	
		public String getType() {
	            return type;
	        }
	
				
		private String url;
		private String displayName;
		private String type;
		
    }

}
