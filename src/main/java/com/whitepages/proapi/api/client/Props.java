package com.whitepages.proapi.api.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>The properties / configuration data class.</p>
 * 
 */
public class Props {

    private static int _defaultConnectTimeout;
    private static int _defaultReadTimeout;
    
    /**
     * Creates a properties object.
     * @throws IOException 
     */
    static  {
        ProApiGetPropertyValues configValues = new ProApiGetPropertyValues();
        try {
			configValues.readPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        _defaultConnectTimeout = configValues.getDefaultConnectTimeout();
        _defaultReadTimeout = configValues.getDefaultReadTimeout();
    }
    
    public static int getDefaultConnectTimeout() {
    	return _defaultConnectTimeout;
    }
    
    public static int getDefaultReadTimeout() {
    	return _defaultReadTimeout;
    }

}

class ProApiGetPropertyValues {
	
	InputStream inputStream;
	int _defaultConnectTimeout;
	int _defaultReadTimeout;
	
	public void readPropValues() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			Class<? extends ProApiGetPropertyValues> c = getClass();
			ClassLoader cl = c.getClassLoader();
			
			inputStream = cl.getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				String classpath = System.getProperty("java.class.path");
				System.out.println("CLASSPATH: " + classpath);
				ClassLoader loader = ProApiGetPropertyValues.class.getClassLoader();
				System.out.println("ClassLoader resource path: " + loader.getResource("config.properties"));        
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
     						
			String sConnect = prop.getProperty("DefaultConnectTimeout");
			String sRead = prop.getProperty("DefaultReadTimeout");
			try {
				_defaultConnectTimeout = Integer.parseInt(sConnect);
			} catch (NumberFormatException nfe) {
				_defaultConnectTimeout = 30000;
			}
			
			try {
				_defaultReadTimeout = Integer.parseInt(sRead);
			} catch (NumberFormatException nfe) {
				_defaultReadTimeout = 30000;
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
	}
	
	public int getDefaultConnectTimeout() {
		return _defaultConnectTimeout;
	}
	
	public int getDefaultReadTimeout() {
		return _defaultReadTimeout;
	}
	
	
	
}

