package com.whitepages.proapi.integrationtests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Copyright 2015 Whitepages, Inc.
 */
public class ClientIntegrationTestHelper {

    private static final String apiKey;

    static {
        File file = new File(ClientIntegrationTestHelper.class.getResource("api_key.txt").getFile());
        String key;
        try {
            key = new Scanner(file).useDelimiter("\n").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No API Key found, expected to find in first line of src/test/resources/api_key.txt");
        }
        apiKey = key;
    }

    public static String getApiKey() {
        return apiKey;
    }

}
