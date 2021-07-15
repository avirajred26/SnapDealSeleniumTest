package pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;
import utils.TestUtil;

public class SearchPage extends PageBase{
	
	@FindBy(xpath = "//div[@id='products']//span[2]")
	public List<WebElement> phoneResult;
	
	@FindBy(xpath = "//div[@id='products']//p")
	public List<WebElement> phoneName;
	
	//div[@id='products']//p
	@FindBy(xpath = "//label[@for='Brand-Samsung']")
	public WebElement selectBrand;
	
	@FindBy(xpath = "//span[normalize-space()='add to cart']")
	public WebElement addToCart;
	
	@FindBy(xpath = "//span[@class='product-name']")
	public WebElement prodName;
	@FindBy(xpath = "//div[@class='you-pay']//span[@class='price']")
	public WebElement prodPrice;
	
	//div[@class='you-pay']//span[@class='price']
	
public	String strPrice,strleastPrice,prodActPrice,actualPhoneName,expPhoneName,pWindow;


	int actualprice = 0 ,leastPrce,getPrice;
	int Iterate,stepno=1;

	
	//label[@for='Brand-Samsung']

	public SearchPage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	public Integer searchFlightDetails() {
		return phoneResult.size();
	}
	
	
	public void getList() throws Throwable {
		
		waitForPageLoad();
		Thread.sleep(5000);
		selectBrand.click();
		Thread.sleep(3000);
		
	
		strPrice = phoneResult.get(0).getText();
		strPrice = 	TestUtil.getPrice(strPrice);
		actualprice = Integer.parseInt(strPrice);
		System.out.println(strPrice);
		for(int i = 0; i<phoneResult.size();i++) {
			strleastPrice = phoneResult.get(i).getText();
			strleastPrice = TestUtil.getPrice(strleastPrice);
			System.out.println(strleastPrice);
				try {
					if(!strleastPrice.isEmpty()) {
						ExcelLibraries.setExcelOutput("Mobile Name " +stepno, phoneName.get(i).getText());
						leastPrce = Integer.parseInt(strleastPrice);
						stepno = stepno+1;
					}
				
				}
				catch(Exception E) {
					System.out.println(E.toString());
				}
			
				 if(leastPrce <= actualprice)  {
					 
				 actualprice = leastPrce;  
				   Iterate= i;
		        }  
				
		}
		System.out.println(actualprice);
		
		ExcelLibraries.setExcelOutput("Least Price",Integer.toString(actualprice) );
		
		actualPhoneName = phoneName.get(Iterate-1).getText();
		
		ExcelLibraries.setExcelOutput("Least Mobile  Name ",actualPhoneName );
		
		phoneResult.get(Iterate-1).click();
		pWindow =  pbDriver.getWindowHandle();
		
	}
	
	public void addProduct() {
		waitForPageLoad();
		
		Set<String> setWindows = pbDriver.getWindowHandles();
		for(String x:setWindows) {
			if(!pWindow.equals(x)) {
				pbDriver.switchTo().window(x);
				
			}
			
		}
		
		addToCart.click();
		prodActPrice = prodPrice.getText();
		prodActPrice = TestUtil.getPrice(prodActPrice);
		getPrice= 	Integer.parseInt(prodActPrice);
		
		
		expPhoneName = prodName.getText();
		
		
		
		
		if(getPrice>actualprice) {
			System.out.println("difference in price" + (getPrice-actualprice));
		}
		else if(actualprice>getPrice) {
			System.out.println("differnce in price "+ (actualprice-getPrice) );
		}
		else {
			System.out.println("Price Were Equal");
		}
		
		
	}
}
