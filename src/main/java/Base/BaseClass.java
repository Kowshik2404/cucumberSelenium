package Base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String platform;
	
	public BaseClass(){
		try {
			prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream("configs/config.properties"));
			platform = prop.getProperty("platform");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void initialization(){
		
		DriverManager.initialiseDriver(prop.getProperty("browser"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	
	
	
	
	

}