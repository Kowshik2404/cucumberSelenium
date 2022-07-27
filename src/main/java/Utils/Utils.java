package Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mifmif.common.regex.Generex;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static Properties loadProperties(String location) {
		Properties prp = new Properties();
		try {
			prp.load(Utils.class.getClassLoader().getResourceAsStream(location));
			return prp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HashMap<String, String> jsonToMap(String jsonStr) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> mapping = objectMapper.readValue(jsonStr, HashMap.class);

		return mapping;
	}

	public static HashMap<String, String> getSubMap(HashMap<String, String> mapping, String key) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		mapping = objectMapper.convertValue(mapping.get(key), HashMap.class);

		return mapping;
	}
	
	public static String getRandomString(String regex, int length) {
		Generex generex = new Generex(regex);
		return generex.random(length);

	}
	
	public static void writeToSavedData(JSONObject json) {

		FileWriter file;
		try {
			file = new FileWriter(
					System.getProperty("user.dir") + "\\src\\test\\resources\\runtimeData\\savedData.json");
			file.write(json.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e1) {
		}
	}
	
	public static JSONObject readSavedData() {

		JSONObject jsonObject = null;
		try {
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\runtimeData\\savedData.json"));
		} catch (IOException | ParseException e) {
		}
		return jsonObject;
	}

}
