package Pages.SuperAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;

public class StoreListPage extends BaseClass {

	@FindBy(css = ".add_btndata")
	WebElement createStoreBtn;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(id = "kt_login_signin_submit")
	WebElement loginBtn;

	@FindBy(xpath = "")
	WebElement signUpBtn;

	@FindBy(xpath = "//div[@ref='eCenterContainer']")
	WebElement storesContainer;

	public StoreListPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickCreateStore() {
		createStoreBtn.click();
		PageUtil.isDisplayedByLocator(By.xpath("//*[text()='Create Store']"), 10);
	}

}
