package Base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager extends BaseClass{
	
	public static void  initialiseDriver(String browserName) {
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/main/resources/Browsers/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/src/main/resources/Browsers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
	}

}
