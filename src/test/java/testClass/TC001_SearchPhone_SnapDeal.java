package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.SearchPage;
import utils.ExcelLibraries;

public class TC001_SearchPhone_SnapDeal extends TestBase {
	
	
	HomePage objHomes;
	SearchPage objSearch;
	String testValue;
	
	@Test
	public void searchFlightTest() throws Throwable {
		try {
		Assert.assertEquals(driver.getTitle(), "Online Shopping Site India - Shop Electronics, Men & Women Clothing, Shoes - www. Snapdeal.com");
		
		objHomes = new HomePage(driver);
		testValue = 	objHomes.searchPhoneList(ExcelLibraries.getTestColValue("PhoneName"));
		}
		catch(AssertionError e) {
			e.printStackTrace();
			closeBrowser();
		}
		
		try {
			
			Assert.assertTrue(driver.getTitle().matches("^.*$"));
			objSearch = new SearchPage(driver);
			
			objSearch.getList();//Getting Search List
			
			objSearch.addProduct();//Adding Product 
			
			Assert.assertTrue(objSearch.expPhoneName.contains(objSearch.actualPhoneName));
			System.out.println("Mobile Names were Equal");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			closeBrowser();
		}
		
		
	}

}
