package com.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class EnvLoader {
    private static final String ENV_PATH = ".env";
    private static HashMap<String, String> envVars = new HashMap<>();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    envVars.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading .env file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return envVars.get(key);
    }
}
