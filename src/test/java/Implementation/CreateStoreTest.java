package Implementation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseClass;
import Pages.LoginPage;
import Pages.PageContainer;
import Pages.SuperAdmin.Dashboard;
import Pages.SuperAdmin.Dashboard.MenuOption;
import Pages.SuperAdmin.Dashboard.SubMenuOption;
import Pages.SuperAdmin.StoreCreationPage;
import Pages.SuperAdmin.StoreListPage;
import Utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateStoreTest extends BaseClass {

	HashMap<String, String> user, storeDetails, metadata, contactDetails, locationDetails;
	String userName, password, storeName;

	@Before()
	public void preCondition() throws Exception {
		initialization();
		Properties data = Utils.loadProperties("Testdata/StoreCreation_TestData.properties");
		user = Utils.jsonToMap(data.getProperty("superAdmin"));
		storeDetails = Utils.jsonToMap(data.getProperty("createStore"));
		metadata = Utils.getSubMap(storeDetails, "metadata");
		userName = user.get("username");
		password = user.get("password");
		String timestamp = new SimpleDateFormat("ddmmss").format(new Date());
		metadata.put("storeName", metadata.get("storeName")+timestamp);
		metadata.put("adminLoginId", metadata.get("adminLoginId")+timestamp);
		metadata.put("storeKey", Utils.getRandomString("[A-Z]{2}[0-9]{3}",5));
		String mobileNum = Utils.getRandomString("[0-9]{10}",10);
		metadata.put("storeMobile", mobileNum);
		contactDetails = Utils.getSubMap(storeDetails, "contactDetails");
		contactDetails.put("mobileNumber", mobileNum);
		contactDetails.put("email", timestamp+contactDetails.get("email"));
		locationDetails = Utils.getSubMap(storeDetails, "locationDetails");
	}

	@Given("User logs in with username and password")
	public void loginIntoUser() throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.login(userName, password);
		Dashboard dashboard = new Dashboard();
		dashboard.isDashboardLoaded();
	}

	@When("User clicks on Stores -> store creation")
	public void user_clicks_on_stores_store_creation() throws Exception {
		PageContainer container = new PageContainer();
		container.selectMenuOption(MenuOption.STORES, SubMenuOption.STORECREATION);
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		storeListpage.clickCreateStore();
	}

	@Then("Store list should be displayed")
	public void store_list_should_be_displayed() throws Exception {
		StoreCreationPage storeCreationPage = new StoreCreationPage();
		storeCreationPage.enterMetaData(metadata);
		storeCreationPage.enterContactDetails(contactDetails);
		storeCreationPage.enterLocationDetails(locationDetails);
		storeCreationPage.SubmitStore();
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		Assert.assertTrue("Check if store name displayed in store listing. Store name : " + metadata.get("storeName"),
				storeListpage.isStoreDisplayed(metadata.get("storeName")));
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
