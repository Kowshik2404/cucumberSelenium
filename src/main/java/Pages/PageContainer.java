package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseClass;
import Pages.SuperAdmin.Dashboard.MenuOption;
import Pages.SuperAdmin.Dashboard.SubMenuOption;
import Utils.PageUtil;

public class PageContainer extends BaseClass{
	
	@FindBy(css=".MuiDrawer-paperAnchorDockedLeft .MuiList-root")
	WebElement leftMenu;
	
	public PageContainer(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectMenuOption(MenuOption option, SubMenuOption submenu) throws InterruptedException {
		WebElement menuElement = leftMenu.findElement(By.xpath("//*[@title='" + option.getValue() + "']/.."));
		if (menuElement.getTagName().equalsIgnoreCase("a")) {
			menuElement.click();
		} else {
			if (!menuElement.getAttribute("class").contains("head_color"))
				menuElement.click();
			WebElement subMenuElement = driver.findElement(
					By.xpath("//div[contains(@class,'MuiCollapse-entered')]//*[@title='" + submenu.getValue() + "']"));
			subMenuElement.click();
		}
		PageUtil.waitForLoadSpinner();
	}

}
