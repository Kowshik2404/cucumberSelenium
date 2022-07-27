package Implementation;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseClass;
import Pages.LoginPage;
import Pages.PageContainer;
import Pages.SuperAdmin.Dashboard;
import Pages.SuperAdmin.Dashboard.MenuOption;
import Pages.SuperAdmin.Dashboard.SubMenuOption;
import Pages.SuperAdmin.StoreListPage.StoreActions;
import Pages.SuperAdmin.StoreCreationPage;
import Pages.SuperAdmin.StoreListPage;
import Utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
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
		metadata.put("storeName", metadata.get("storeName") + timestamp);
		metadata.put("adminLoginId", timestamp + metadata.get("adminLoginId"));
		metadata.put("storeKey", Utils.getRandomString("[A-Z]{2}[0-9]{3}", 5));
		String mobileNum = Utils.getRandomString("[0-9]{10}", 10);
		metadata.put("storeMobile", mobileNum);
		contactDetails = Utils.getSubMap(storeDetails, "contactDetails");
		contactDetails.put("mobileNumber", mobileNum);
		contactDetails.put("email", timestamp + contactDetails.get("email"));
		locationDetails = Utils.getSubMap(storeDetails, "locationDetails");
		System.out.println(metadata.get("storeName"));
	}

	@Given("User enters username and password and clicks on Login")
	public void loginIntoUser() throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.login(userName, password);
	}

	@Then("User should be able to see Dashboard")
	public void verifyDashboard() throws InterruptedException {
		Dashboard dashboard = new Dashboard();
		dashboard.isDashboardLoaded();
	}

	@When("User navigates Stores -> store creation")
	public void navigateToCreateStore() throws Exception {
		log.info("Navigate");
		PageContainer container = new PageContainer();
		container.selectMenuOption(MenuOption.STORES, SubMenuOption.STORECREATION);
	}

	@And("Enter store details and clicks on submit")
	public void user_clicks_on_stores_store_creation() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.clickCreateStore();
		StoreCreationPage storeCreationPage = new StoreCreationPage();
		storeCreationPage.enterMetaData(metadata);
		storeCreationPage.enterContactDetails(contactDetails);
		storeCreationPage.enterLocationDetails(locationDetails);
		storeCreationPage.SubmitStore(metadata.get("storeName"));
	}

	@Then("Create store should be listed in store listing")
	public void store_list_should_be_displayed() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		Assert.assertTrue("Check if store name displayed in store listing. Store name : " + metadata.get("storeName"),
				storeListpage.isStoreDisplayed(metadata.get("storeName")));
	}

	@Then("Edit store")
	public void editStore() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		storeListpage.performActionOnStore(metadata.get("storeName"), StoreActions.EDIT);
		StoreCreationPage storeCreationPage = new StoreCreationPage();
		storeCreationPage.isEditStoreLoaded();
		metadata.put("storeName", metadata.get("storeName") + " edited");
		storeCreationPage.enterStoreName(metadata.get("storeName"));
		metadata.put("storeMobile", Utils.getRandomString("[0-9]{10}", 10));
		storeCreationPage.enterStoreMobile(metadata.get("storeMobile"));
	}
	
	@Then("Delete store")
	public void deleteStore() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		storeListpage.performActionOnStore(metadata.get("storeName"), StoreActions.DELETE);
		PageContainer container = new PageContainer();
		container.selectMenuOption(MenuOption.DASHBOARD, null);
		container.selectMenuOption(MenuOption.STORES, SubMenuOption.STORECREATION);
		Assert.assertTrue("Check if store is not displayed. Store name : " + metadata.get("storeName"),
				storeListpage.verifyIfStoreNotDisplayed(metadata.get("storeName")));
	}
	
	@Then("Inactivate store")
	public void inactiveStore() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		storeListpage.inActivateStore(metadata.get("storeName"));
		Assert.assertTrue("Check if store is in InActive state. Store name : " + metadata.get("storeName"),
				storeListpage.isStoreInActive(metadata.get("storeName")));
	}
	
	@Then("Activate store")
	public void activateStore() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		storeListpage.activateStore(metadata.get("storeName"));
		Assert.assertTrue("Check if store is in Active state. Store name : " + metadata.get("storeName"),
				storeListpage.isStoreActive(metadata.get("storeName")));
	}

	@Then("Open store catalogue")
	public void storeCatalogue() throws Exception {
		StoreListPage storeListpage = new StoreListPage();
		storeListpage.isStoreListLoaded();
		JSONObject jsonObject = Utils.readSavedData();
		JSONArray stores = (JSONArray)jsonObject.get("stores");
		storeListpage.performActionOnStore(stores.get(0).toString(), StoreActions.CATALOGUE);
		Assert.assertTrue("Check if store catalogue is Displayed. Store name : " + metadata.get("storeName"),
				storeListpage.verifyIfStoreCatalgueIsDisplayed(metadata.get("storeName")));
		
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
