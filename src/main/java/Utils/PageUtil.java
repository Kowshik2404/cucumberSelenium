package Utils;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseClass;

public class PageUtil extends BaseClass {

	public enum LocatorType {
		XPATH("xpath"), ID("id"), CSS("css"), NAME("name");

		private String value;

		public String getValue() {
			return this.value;
		}

		private LocatorType(String option) {
			this.value = option;
		}
	}

	public static WebElement getElement(String locatorJson, LocatorType locatorType, int timeout) {
		JSONObject jsonObject = new JSONObject(locatorJson);
		WebElement element = null;
		switch (locatorType.getValue()) {

		case "xpath":
			element = driver.findElement(By.xpath(jsonObject.getJSONObject(platform).getString("xpath")));
			break;
		case "id":
			element = driver.findElement(By.xpath(jsonObject.getJSONObject(platform).getString("id")));
			break;
		default:
			break;
		}

		return element;
	}

	public static boolean isDisplayedByLocator(By element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
			return driver.findElement(element).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementDisplayed(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public static void waitUntilDisappear(By element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
	}

	public static void waitUntilElementDisappear(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitForLogin() {
		System.out.println("Wait for processing");
		isDisplayedByLocator(By.xpath("//div[text()='Processing']"), 5);
		waitUntilDisappear(By.xpath("//div[text()='Processing']"), 10);
		System.out.println("processing complete");
		isDisplayedByLocator(By.xpath("//div[text()='Login Successfully']"), 5);
		System.out.println("login complete");
	}

	public static void waitForLoadSpinner() throws InterruptedException {
		System.out.println("chk for loadin");
		if (PageUtil.isDisplayedByLocator(By.xpath("//div[contains(@class,'MuiAlert')]"), 4)) {
			System.out.println("wait for loadin");
			PageUtil.waitUntilDisappear(By.xpath("//div[contains(@class,'MuiAlert')]"), 10);
			System.out.println("loadin compelte and wait 5 sec");
		}
		Thread.sleep(2000);
		// add timeout and catch NoSuchElementException
	}

}