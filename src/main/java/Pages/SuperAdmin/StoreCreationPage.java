package Pages.SuperAdmin;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;

public class StoreCreationPage extends BaseClass {

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
	
	@FindBy(name = "loginId")
	WebElement loginIdField;

	@FindBy(xpath = "//label[text()='Store Category*']/..//input")
	WebElement storeCategory;

	@FindBy(css = ".MuiDrawer-paperAnchorDockedLeft .MuiList-root")
	WebElement leftMenu;

	public StoreCreationPage() {
		PageFactory.initElements(driver, this);
	}

}
