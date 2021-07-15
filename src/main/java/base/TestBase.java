package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.FileHandler;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utils.ExcelLibraries;




public class TestBase {
	protected static WebDriver driver;
	protected static PageBase basePage;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	boolean append = true;
    static FileHandler handler;
   
    static Map<String, Object> prefs;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Parameters("browser")  
	@BeforeTest
    public void launchApplication(String Browser) throws Throwable {
		
		ExcelLibraries.getTestName(getClass().getSimpleName());
		setDriverProperty(Browser);
	}

	

	
    @AfterTest
    public static void closeBrowser() throws Throwable {
    
    	
        driver.close();
        driver.quit();
    }
    
    private static void setDriverProperty(String Browser) throws Throwable{
    	ExcelLibraries.setExcelOutput("Browser Name", Browser);
	if(Browser.contains("Chrome") ||Browser.equalsIgnoreCase("Chrome") ) {
		prefs = new HashMap<String, Object>();
    	prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		option.setExperimentalOption("prefs", prefs);
		option.setAcceptInsecureCerts(true);
		option.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		System.getProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver(option); 
		
		
	}else if(Browser.contains("FireFox")  ||Browser.equalsIgnoreCase("FireFox")  ) {
		System.getProperty("webdriver.gecko.driver","geckodriver.exe");
		driver = new FirefoxDriver();
	}
	  
		driver.manage().window().maximize();
		driver.get(prop.getProperty("AppUrl"));
	}
    
    
    
}