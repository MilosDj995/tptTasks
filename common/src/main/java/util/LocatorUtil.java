package util;

import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocatorUtil {

    private static final Properties locators = new Properties();

    static {
        try (InputStream input = LocatorUtil.class.getClassLoader().getResourceAsStream("loc.properties")) {
            if (input != null) {
                locators.load(input);
            } else {
                throw new RuntimeException("loc.properties not found in resources folder.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load locators: " + e.getMessage());
        }
    }

    public static String locString(String key, String... args) {
        String locator = getRawLocator(key);
        for (String arg : args) {
            locator = locator.replaceFirst("%ARG%", arg);
        }
        if (locator.contains("%ARG%")) {
            throw new IllegalArgumentException();
        }
        return locator;
    }

    public static By loc(String key, String... args) {
        String locator = locString(key, args);

        if (locator.startsWith("xpath:")) {
            return By.xpath(locator.substring(6));
        } else if (locator.startsWith("css:")) {
            return By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("id:")) {
            return By.id(locator.substring(3));
        } else if (locator.startsWith("name:")) {
            return By.name(locator.substring(5));
        } else {
            throw new IllegalArgumentException("Unknown key for a locator: " + key + " -> " + locator);
        }
    }

    private static String getRawLocator(String key) {
        String locator = locators.getProperty(key);
        if (locator == null) {
            throw new IllegalArgumentException("Locator not found for key: " + key);
        }
        return locator;
    }
}
