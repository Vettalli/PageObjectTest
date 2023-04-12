package helpers;

import readJSON.JSONProvider;

import java.util.Random;

public class StringUtil {
    public static String generateRandomString(){
        int leftLimit = Integer.parseInt(JSONProvider.getProperty(JSONProvider.getJSON("data"), "leftLimit"));
        int rightLimit = Integer.parseInt(JSONProvider.getProperty(JSONProvider.getJSON("data"), "rightLimit"));
        int targetStringLength = RandomUtil.getRandomInteger();
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String getXpathWithStringParam(String xpath, String param){
        return String.format(xpath, param);
    }

    public static String getXpathWithNumberParam(String xpath, Integer param){
        return String.format(xpath, param);
    }
}
