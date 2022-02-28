package com.company.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@UtilityClass
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static Properties loadPropertiesData(URL url) {
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
//            LOGGER.log(Level.FATAL, "Error with loading properties from file. ", e);
            throw new RuntimeException("Error with loading properties from file: " + url, e);
        }
        return properties;
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
