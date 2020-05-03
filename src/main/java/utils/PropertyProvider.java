package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/resources/config.properties"));
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }
}
