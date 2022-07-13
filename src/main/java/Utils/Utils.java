package Utils;

import java.util.HashMap;
import java.util.Properties;

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

}
