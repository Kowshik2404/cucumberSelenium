package Base;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.core.config.Configurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String platform;
	public static Logger log = LogManager.getLogger(BaseClass.class);
	
	public BaseClass(){
		try {
			prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream("configs/config.properties"));
			platform = prop.getProperty("platform");
			
			Configurator.initialize(null, "log4j2.xml");
			Utils.Utils.writeToSavedData(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void initialization(){
		log.info("Initialise browser");
		DriverManager.initialiseDriver(prop.getProperty("browser"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		log.info("Url loaded");
		
	}
	
	
	
	
	
	
	
	

}