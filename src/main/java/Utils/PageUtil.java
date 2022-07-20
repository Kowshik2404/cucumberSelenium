package Utils;

import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
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
			element = driver.findElement(By.id(jsonObject.getJSONObject(platform).getString("id")));
			break;
		case "name":
			element = driver.findElement(By.name(jsonObject.getJSONObject(platform).getString("name")));
			break;
		case "css":
			element = driver.findElement(By.cssSelector(jsonObject.getJSONObject(platform).getString("css")));
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
		scrollIntoView(element);
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

	public static void waitForStoreCreation() {
		System.out.println("Wait for processing");
		isDisplayedByLocator(By.xpath("//div[text()='Processing']"), 5);
		waitUntilDisappear(By.xpath("//div[text()='Processing']"), 10);
		System.out.println("processing complete");
		isDisplayedByLocator(By.xpath("//div[text()='Save Successfully']"), 5);
		System.out.println("login complete");
	}

	public static void waitForStoreDelete() {
		System.out.println("Wait for processing");
		isDisplayedByLocator(By.xpath("//div[text()='Processing']"), 5);
		waitUntilDisappear(By.xpath("//div[text()='Processing']"), 10);
		System.out.println("processing complete");
		isDisplayedByLocator(By.xpath("//div[text()='Successfully Delete"), 5);
		System.out.println("delete complete");
	}

	public static void waitForStoreInactiveStore() {
		System.out.println("Wait for processing");
		isDisplayedByLocator(By.xpath("//div[text()='Processing']"), 5);
		waitUntilDisappear(By.xpath("//div[text()='Processing']"), 10);
		System.out.println("processing complete");
		isDisplayedByLocator(By.xpath("//div[text()='Status Successfully Changed"), 5);
		System.out.println("status change complete");
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

	public static void clickElement(WebElement element) {
		try {
			element.click();
		} catch (ElementClickInterceptedException error) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public static void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollRight(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 1000", element);
	}

	public static void scrollLeft(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft = 0", element);
	}

	public static boolean verifyIfElementNotPresent(String locatorJson, LocatorType locatorType, int timeout) {
		JSONObject jsonObject = new JSONObject(locatorJson);
		List<WebElement> element = null;
		switch (locatorType.getValue()) {

		case "xpath":
			element = driver.findElements(By.xpath(jsonObject.getJSONObject(platform).getString("xpath")));
			break;
		case "id":
			element = driver.findElements(By.id(jsonObject.getJSONObject(platform).getString("id")));
			break;
		case "name":
			element = driver.findElements(By.name(jsonObject.getJSONObject(platform).getString("name")));
			break;
		case "css":
			element = driver.findElements(By.cssSelector(jsonObject.getJSONObject(platform).getString("css")));
			break;
		default:
			break;
		}

		return element.size() > 0;
	}

	public static List<WebElement> getElements(String locatorJson, LocatorType locatorType, int timeout) {
		JSONObject jsonObject = new JSONObject(locatorJson);
		List<WebElement> element = null;
		switch (locatorType.getValue()) {

		case "xpath":
			element = driver.findElements(By.xpath(jsonObject.getJSONObject(platform).getString("xpath")));
			break;
		case "id":
			element = driver.findElements(By.id(jsonObject.getJSONObject(platform).getString("id")));
			break;
		case "name":
			element = driver.findElements(By.name(jsonObject.getJSONObject(platform).getString("name")));
			break;
		case "css":
			element = driver.findElements(By.cssSelector(jsonObject.getJSONObject(platform).getString("css")));
			break;
		default:
			break;
		}

		return element;
	}

}
