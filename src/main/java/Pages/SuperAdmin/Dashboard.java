package Pages.SuperAdmin;

import java.util.Properties;

import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Utils.PageUtil;
import Utils.PageUtil.LocatorType;
import Utils.Utils;;

public class Dashboard extends BaseClass {

	Properties locators = new Properties();

	public enum MenuOption {
		DASHBOARD("Dashboard"), STORES("Stores"), ORDERS("Orders"), INVOICES("Invoices"),
		ABANDON_CARTS("Abandon Carts"), CUSTOMERS("Customers"), PRODUCTS("Products"), CATALOGUE("Catalogue"),
		IMPORTEXPORT("Imports / Exports");

		private String value;

		public String getValue() {
			return this.value;
		}

		private MenuOption(String option) {
			this.value = option;
		}
	}

	public enum SubMenuOption {
		STORECREATION("Store Creation"), STORECATEGORY("Store Category"), ORDERS("Orders"),
		PICKER_ASSIGNMENT("Picker Assignment"), RIDER_ASSIGNMENT("Rider Assignment"), RETURN_ORDERS("Return Orders"),
		SALES_INVOICES("Sales Invoices"), ABANDON_CARTS("Abandon Carts"), CUSTOMERS("Customers"),
		CUSTOMERS_SEG("Customer Segmentation"), GLOBAL_PRODUCT("Global Product"), PRODUCT_UNIT("Product Unit"),
		CATALOGUE("Catalogue"), CATEGORY("Category"), SUB_CATEGORY("Sub Category"), CLASSIFICATION("Classification"),
		PORDUCT_HIGHLIGHT("Product Highlights"), SPECIAL_PRODUCTS("Special Products"),
		CROSS_SELLING_PRODUCT("Cross Selling Products"), FLASH_SALE_CATALOGUE("Flash Sales Catalogue"),
		IMPORTEXPORT("Imports / Exports");

		private String value;

		public String getValue() {
			return this.value;
		}

		private SubMenuOption(String option) {
			this.value = option;
		}
	}

	public Dashboard() {
		PageFactory.initElements(driver, this);

		try {
			locators = Utils.loadProperties("Locators/Dashboard.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isDashboardLoaded() throws InterruptedException {
		PageUtil.waitForLoadSpinner();
		PageUtil.isElementDisplayed(PageUtil.getElement(locators.getProperty("countCards"), LocatorType.XPATH, 10), 1);
	}

}
