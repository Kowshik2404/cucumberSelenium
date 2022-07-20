package Pages;

import java.util.Properties;

import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;
import Utils.PageUtil.LocatorType;
import Utils.Utils;

public class LoginPage extends BaseClass {

	Properties locators = new Properties();

	public LoginPage() {
		PageFactory.initElements(driver, this);
		try {
			locators = Utils.loadProperties("Locators/login.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login(String username, String password) {
		PageUtil.getElement(locators.getProperty("username"), LocatorType.NAME, 10).sendKeys(username);
		PageUtil.getElement(locators.getProperty("password"), LocatorType.NAME, 10).sendKeys(password);
		PageUtil.getElement(locators.getProperty("loginButton"), LocatorType.ID, 10).click();
		PageUtil.waitForLogin();
	}
}
