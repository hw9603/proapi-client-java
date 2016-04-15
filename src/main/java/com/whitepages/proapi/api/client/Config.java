package com.whitepages.proapi.api.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.Properties;

/**
 * <p>The configuration data class.</p>
 *
 * <p>In most use cases, appropriate instantiation of the {@link Client} will create an
 * appropriate configuration. However in some advanced use cases, one or more custom
 * configurations may be created and configured appropriately.</p>
 *
 * <p>There are several supported idioms for usage of custom configurations, in
 * order to provide maximum flexibility to our clients. They are:</p>
 * <ul>
 *     <li>Create a new configuration instance for each client, or</li>
 *     <li>Create a single configuration instance to share beteween many clients.</li>
 * </ul>
 */
public class Config {

    private static final URI DEFAULT_SERVICE_ROOT = URI.create("https://proapi.whitepages.com");

    private String apiKey;
    private URI serviceRoot;
    
    /**
     * Creates a mutable configuration object.
     * @param apiKey The Whitepages API Key associated with your PRO account.
     */
    public Config(String apiKey) {
        this.apiKey = apiKey;
        setServiceRoot(DEFAULT_SERVICE_ROOT);
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public URI getServiceRoot() {
        return serviceRoot;
    }

    public void setServiceRoot(URI serviceRoot) {
        this.serviceRoot = serviceRoot;
    }
    
}
