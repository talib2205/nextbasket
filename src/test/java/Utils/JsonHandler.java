package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHandler {
    public String getJsonValue(String fileName, String key) {
        JSONParser jsonParser = new JSONParser();
        String value = null;
        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            value = (String) jsonObject.get(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void writeJsonValue(String fileName, String key, String value) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            jsonObject.put(key, value);
            try (FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(jsonObject.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonData(String fileName) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".json")) {
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getFromKeyPairValue(String fileName,String Key, String subKey) {
        JSONObject jsonObject = getJsonData(fileName);
        if (jsonObject != null) {
            JSONObject fromDateObject = (JSONObject) jsonObject.get(Key);
            if (fromDateObject != null) {
                return (String) fromDateObject.get(subKey);
            }
        }
        return null;
    }

}
