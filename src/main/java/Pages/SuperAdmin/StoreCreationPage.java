package Pages.SuperAdmin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;
import Utils.Utils;
import Utils.PageUtil.LocatorType;

public class StoreCreationPage extends BaseClass {

	Properties locators = new Properties();

	@FindAll({ @FindBy(xpath = "//*[text()='Submit']") })
	private List<WebElement> submitButtons;

	@FindAll({ @FindBy(xpath = "//*[text()='Reset']") })
	private List<WebElement> resetButtons;

	@FindAll({ @FindBy(xpath = "//*[text()='Cancel']") })
	private List<WebElement> cancelButtons;

	@FindBy(name = "storeName")
	WebElement storeNameField;

	@FindBy(id = "storeKey")
	WebElement storeKeyField;

	@FindBy(name = "storeMobile")
	WebElement storeMobileNumField;

	@FindBy(name = "storePhone")
	WebElement storePhoneNumField;

	@FindBy(xpath = "//label[text()='Store Category*']/..//input")
	WebElement storeCategory;

	@FindBy(css = ".MuiDrawer-paperAnchorDockedLeft .MuiList-root")
	WebElement leftMenu;

	public StoreCreationPage() {
		PageFactory.initElements(driver, this);
		try {
			locators = Utils.loadProperties("Locators/StoreCreation.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterMetaData(HashMap<String, String> metadata) {
		if (metadata.get("storeName") != null)
			PageUtil.getElement(locators.getProperty("storeName"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("storeName"));
		if (metadata.get("storeKey") != null)
			PageUtil.getElement(locators.getProperty("storeKey"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("storeKey"));
		if (metadata.get("storeMobile") != null)
			PageUtil.getElement(locators.getProperty("storeMobile"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("storeMobile"));
		if (metadata.get("storePhone") != null)
			PageUtil.getElement(locators.getProperty("storePhone"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("storePhone"));
		if (metadata.get("adminLoginId") != null)
			PageUtil.getElement(locators.getProperty("adminLoginId"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("adminLoginId"));
		if (metadata.get("adminPassword") != null)
			PageUtil.getElement(locators.getProperty("adminPassword"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("adminPassword"));
		if (metadata.get("aboutStore") != null)
			PageUtil.getElement(locators.getProperty("aboutStore"), LocatorType.CSS, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), metadata.get("aboutStore"));
		if (metadata.get("storeCountry") != null) {
			WebElement country = PageUtil.getElement(locators.getProperty("storeCountry"), LocatorType.XPATH, 3);
			country.click();
			country.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			country.sendKeys(metadata.get("storeCountry"));
			PageUtil.getElement(
					locators.getProperty("storeListing").replace("toReplace1", metadata.get("storeCountry")),
					LocatorType.XPATH, 3).click();
		}
		if (metadata.get("storeCategory") != null) {
			WebElement country = PageUtil.getElement(locators.getProperty("storeCategory"), LocatorType.XPATH, 3);
			country.click();
			country.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			country.sendKeys(metadata.get("storeCategory"));
			PageUtil.getElement(
					locators.getProperty("storeListing").replace("toReplace1", metadata.get("storeCategory")),
					LocatorType.XPATH, 3).click();
		}
		if (metadata.get("storeLogo") != null) {
			PageUtil.getElement(locators.getProperty("storeLogo"), LocatorType.XPATH, 3)
					.sendKeys(new File("").getAbsolutePath() + File.separator + metadata.get("storeLogo"));
		}
	}

	public void enterContactDetails(HashMap<String, String> contactDetails) {
		if (contactDetails.get("name") != null)
			PageUtil.getElement(locators.getProperty("name"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), contactDetails.get("name"));
		if (contactDetails.get("email") != null)
			PageUtil.getElement(locators.getProperty("email"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), contactDetails.get("email"));
		if (contactDetails.get("mobileNumber") != null)
			PageUtil.getElement(locators.getProperty("mobileNumber"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), contactDetails.get("mobileNumber"));
		
		WebElement addBtn = PageUtil.getElement(locators.getProperty("addButton"), LocatorType.XPATH, 3);
		PageUtil.clickElement(addBtn);
	}

	public void enterLocationDetails(HashMap<String, String> locationDetails) throws Exception {
		if (locationDetails.get("addressName") != null)
			PageUtil.getElement(locators.getProperty("addressName"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), locationDetails.get("addressName"));
		if (locationDetails.get("address1") != null)
			PageUtil.getElement(locators.getProperty("address1"), LocatorType.NAME, 3)
					.sendKeys(Keys.chord(Keys.CONTROL, "a"), locationDetails.get("address1"));
		if (locationDetails.get("state") != null) {
			WebElement country = PageUtil.getElement(locators.getProperty("state"), LocatorType.XPATH, 3);
			country.click();
			country.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			country.sendKeys(locationDetails.get("state"));
			PageUtil.getElement(locators.getProperty("storeListing").replace("toReplace1", locationDetails.get("state")),
					LocatorType.XPATH, 3).click();
		}
		if (locationDetails.get("city") != null) {
			WebElement country = PageUtil.getElement(locators.getProperty("city"), LocatorType.XPATH, 3);
			country.click();
			country.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			country.sendKeys(locationDetails.get("city"));
			PageUtil.getElement(locators.getProperty("storeListing").replace("toReplace1", locationDetails.get("city")),
					LocatorType.XPATH, 3).click();
		}
		if (locationDetails.get("locality") != null) {
			WebElement locality = PageUtil.getElement(locators.getProperty("locality"), LocatorType.XPATH, 3);
			locality.click();
			locality.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			locality.sendKeys(locationDetails.get("locality"));
			PageUtil.getElement(locators.getProperty("storeListing").replace("toReplace1", locationDetails.get("locality")),
					LocatorType.XPATH, 3).click();
		}
		if (locationDetails.get("searchPlace") != null) {
			
			WebElement locatorSearch = PageUtil.getElement(locators.getProperty("locationSearch"), LocatorType.XPATH, 3);
			locatorSearch.sendKeys(Keys.chord(Keys.CONTROL, "a"), locationDetails.get("searchPlace"));
			Thread.sleep(1000);
			locatorSearch.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
			PageUtil.getElement(locators.getProperty("getButton"), LocatorType.XPATH, 3).click();
		}
	}
	
	public void SubmitStore() {
		WebElement submitButton = PageUtil.getElement(locators.getProperty("submitButtons"), LocatorType.XPATH, 3);
		PageUtil.clickElement(submitButton);
		PageUtil.waitForStoreCreation();
	}

}
