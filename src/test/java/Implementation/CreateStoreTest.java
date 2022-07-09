package Implementation;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseClass;
import Pages.LoginPage;
import Pages.PageContainer;
import Pages.SuperAdmin.Dashboard;
import Pages.SuperAdmin.Dashboard.MenuOption;
import Pages.SuperAdmin.Dashboard.SubMenuOption;
import Utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateStoreTest extends BaseClass {

	HashMap<String, String> user, storeDetails, metadata;
	String userName, password, storeKey;

	@Before()
	public void preCondition() throws Exception {
		initialization();
		Properties data = Utils.loadProperties("Testdata/StoreCreation.properties");
		user = Utils.jsonToMap(data.getProperty("superAdmin"));
		storeDetails = Utils.jsonToMap(data.getProperty("createStore"));
		metadata = Utils.getSubMap(storeDetails, "metadata");
		userName = user.get("username");
		password = user.get("username");
		storeKey = metadata.get("storeKey");
	}

	@Given("User logs in with username and password")
	public void loginIntoUser(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.login(username, password);
		Dashboard dashboard = new Dashboard();
		dashboard.isDashboardLoaded();
	}

	@When("User clicks on Stores -> store creation")
	public void user_clicks_on_stores_store_creation() throws Exception {
		PageContainer container = new PageContainer();
		container.selectMenuOption(MenuOption.STORES, SubMenuOption.STORECREATION);
	}

	@Then("Store list should be displayed")
	public void store_list_should_be_displayed() throws InterruptedException {
		Dashboard dashboard = new Dashboard();
		dashboard.isDashboardLoaded();

	}

	@After()
	public void closeBrowser(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take a screenshot
			scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
					"Failure Screenshot");
		}
		driver.quit();
	}
}
