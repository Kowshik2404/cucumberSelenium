package Pages.SuperAdmin;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;
import Utils.PageUtil.LocatorType;
import Utils.Utils;

public class StoreListPage extends BaseClass {
	
	Properties locators = new Properties();

	public StoreListPage() {
		PageFactory.initElements(driver, this);
		try {
			locators = Utils.loadProperties("Locators/storeList.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isStoreListLoaded() throws InterruptedException {
		PageUtil.isElementDisplayed(PageUtil.getElement(locators.getProperty("storesContainer"), LocatorType.XPATH, 10), 1);
	}
	
	public void clickCreateStore() {
		PageUtil.getElement(locators.getProperty("createStoreBtn"), LocatorType.CSS, 10).click();
		PageUtil.isDisplayedByLocator(By.xpath("//*[text()='Create Store']"), 10);
	}

	public boolean isStoreDisplayed(String storeName) {
		return PageUtil.isElementDisplayed(PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5), 1);
	}
}
