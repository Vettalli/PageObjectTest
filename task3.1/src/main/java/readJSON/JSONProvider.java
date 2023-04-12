package readJSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.Logger;
import models.UserData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONProvider {
    public static JSONObject getJSON(String type){
        JSONParser parser = new JSONParser();
        Object obj = new Object();

        try {
            switch (type){
                case "config":

                    obj = parser.parse(new FileReader("src/main/resources/config.json"));
                    break;

                case "data":
                    obj = parser.parse(new FileReader("src/main/resources/data.json"));
                    break;
            }
        } catch (IOException e) {
            Logger.info("IOException Runtime exception during parsing JSON to Object");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            Logger.info("ParseException e Runtime exception during parsing JSON to Object");
            throw new RuntimeException(e);
        }

        JSONObject jo = (JSONObject) obj;

        return jo;
    }

    public static String getProperty(JSONObject jo, String key){
        String property = (String) jo.get(key);

        return property;
    }

    public static ArrayList<String> getProperties(JSONObject jo, String key){
        var properties = new ArrayList<String>();
        JSONArray propertiesObj = (JSONArray) jo.get(key);

        for (var property : propertiesObj) {
            properties.add(property.toString());
        }

        return properties;
    }

    public static Iterator<Object[]> getUserData(){
        ObjectMapper objectMapper = new ObjectMapper();
        int amountOfUsers = Integer.parseInt(JSONProvider.getProperty(JSONProvider.getJSON("data"), "amountOfUsers"));
        File file = new File("src/main/resources/userData.json");
        ArrayList <Object[]> objUsers = new ArrayList<>(amountOfUsers);
        int lengthOfArray = 1;
        int index = 0;
        UserData[] users;

        try {
            users = objectMapper.readValue(file, UserData[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < users.length; i++) {
            var objUser = new Object[lengthOfArray];
            objUser[index] = users[i];
            objUsers.add(objUser);
        }

        return objUsers.iterator();
    }
}