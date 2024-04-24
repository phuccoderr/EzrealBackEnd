package com.ezreal.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Dotenv {
    private static final String CONFIG_FILE = ".env";

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
