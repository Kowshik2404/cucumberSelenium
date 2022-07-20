package Pages.SuperAdmin;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
	
	public enum StoreActions {
		EDIT("Edit details"), DELETE("Delete"), CATALOGUE("Catalogue"), STORE_SETTINGS("Store Settings"),
		DELIVERY_SETTINGS("Delivery Settings"), PICKERS("Pickers"), RIDERS("Riders"), VEHICLES("Vehicles");
		private String value;

		public String getValue() {
			return this.value;
		}

		private StoreActions(String option) {
			this.value = option;
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
	
	public void performActionOnStore(String storeName, StoreActions action){
		WebElement element = PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
		WebElement parent = element.findElement(By.xpath("./.."));
		String rowId = parent.getAttribute("row-id");
		PageUtil.scrollIntoView(parent);
		PageUtil.scrollRight(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		String locator = locators.getProperty("actionBlock");
		locator = locator.replace("toReplace1", rowId);
		locator = locator.replace("toReplace2", action.getValue());
		PageUtil.clickElement(PageUtil.getElement(
				locator, LocatorType.XPATH, 5));
		if (action.getValue().equalsIgnoreCase(StoreActions.DELETE.getValue())) {
			PageUtil.clickElement(PageUtil.getElement(locators.getProperty("deleteButton"), LocatorType.XPATH, 5));
			PageUtil.waitForStoreDelete();
		}
		if (action.getValue().equalsIgnoreCase(StoreActions.CATALOGUE.getValue())) {
			PageUtil.isElementDisplayed(
					PageUtil.getElement(locators.getProperty("storeCatalogueHeading").replace("toReplace1", storeName),
							LocatorType.XPATH, 5),
					5);
		}
	}
	
	public void inActivateStore(String storeName) {
		PageUtil.scrollLeft(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		WebElement element = PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
		WebElement parent = element.findElement(By.xpath("./.."));
		String rowId = parent.getAttribute("row-id");
		PageUtil.scrollIntoView(parent);
		PageUtil.scrollRight(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		PageUtil.clickElement(PageUtil.getElement(locators.getProperty("activeButton").replace("toReplace1", rowId),
				LocatorType.XPATH, 5));
		PageUtil.clickElement(PageUtil.getElement(locators.getProperty("inactivePopupButton"), LocatorType.XPATH, 5));
		PageUtil.waitForStoreInactiveStore();
	}
	
	public void activateStore(String storeName) {
		PageUtil.scrollLeft(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		WebElement element = PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
		WebElement parent = element.findElement(By.xpath("./.."));
		String rowId = parent.getAttribute("row-id");
		PageUtil.scrollIntoView(parent);
		PageUtil.scrollRight(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		PageUtil.clickElement(PageUtil.getElement(locators.getProperty("inactiveButton").replace("toReplace1", rowId),
				LocatorType.XPATH, 5));
		PageUtil.clickElement(PageUtil.getElement(locators.getProperty("activatePopupButton"), LocatorType.XPATH, 5));
		PageUtil.waitForStoreInactiveStore();
	}
	
	public boolean verifyIfStoreNotDisplayed(String storeName) {
		return !PageUtil.verifyIfElementNotPresent(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
	}
	
	public boolean isStoreActive(String storeName) {
		PageUtil.scrollLeft(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		WebElement element = PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
		WebElement parent = element.findElement(By.xpath("./.."));
		String rowId = parent.getAttribute("row-id");
		PageUtil.scrollIntoView(parent);
		PageUtil.scrollRight(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		return PageUtil.isElementDisplayed(PageUtil.getElement(locators.getProperty("activeButton").replace("toReplace1", rowId),
				LocatorType.XPATH, 5), 5);
	}
	
	public boolean isStoreInActive(String storeName) {
		PageUtil.scrollLeft(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		WebElement element = PageUtil.getElement(
				locators.getProperty("storeNameField").replace("toReplace1", storeName), LocatorType.XPATH, 5);
		WebElement parent = element.findElement(By.xpath("./.."));
		String rowId = parent.getAttribute("row-id");
		PageUtil.scrollIntoView(parent);
		PageUtil.scrollRight(driver.findElement(By.cssSelector(".ag-center-cols-viewport")));
		return PageUtil.isElementDisplayed(PageUtil.getElement(locators.getProperty("inactiveButton").replace("toReplace1", rowId),
				LocatorType.XPATH, 5), 5);
	}
	
	public boolean verifyIfStoreCatalgueIsDisplayed(String storeName) {
		return PageUtil.isElementDisplayed(
				PageUtil.getElement(locators.getProperty("storeCatalogueHeading").replace("toReplace1", storeName),
						LocatorType.XPATH, 5),
				5);
	}
}
