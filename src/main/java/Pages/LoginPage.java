package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;

public class LoginPage extends BaseClass {

		@FindBy(name="username")
		WebElement usernameField;
		
		@FindBy(name="password")
		WebElement passwordField;
		
		@FindBy(id="kt_login_signin_submit")
		WebElement loginBtn;
		
		@FindBy(xpath="")
		WebElement signUpBtn;
		
		@FindBy(xpath="")
		WebElement crmLogo;
		
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
		public void login(String username, String password) {
			usernameField.sendKeys(username);
			passwordField.sendKeys(password);
			loginBtn.click();
			PageUtil.waitForLogin();
		}
}
