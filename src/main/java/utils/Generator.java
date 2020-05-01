package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Generator {

    public static String getRandomString(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers).toLowerCase();
    }

    public static String getRandomString() {
        return getRandomString(8, true, true);
    }

}
