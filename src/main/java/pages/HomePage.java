package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;


public class HomePage extends PageBase {

	
	@FindBy(xpath = "//input[@onkeypress='clickGo(event, this)']")
	public WebElement searchBar;
	
	
	
	//a[@class='clear-search-keyword']

	public HomePage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	public void searchPhone(String phone) throws InterruptedException {
		waitForPageLoad();
		searchBar.click();
		searchBar.clear();
		Thread.sleep(2000);
		searchBar.sendKeys(phone,Keys.ENTER);

	
		
	}
	
	public String searchPhoneList(String strFromCity) throws Throwable {
		Thread.sleep(5000);
		waitForPageLoad();
		searchPhone(strFromCity);
		return pbDriver.getTitle();
	}
}
