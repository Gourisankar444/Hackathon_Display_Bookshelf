package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pageObjects.BookShelvesPage;
import pageObjects.HomePage;
import pageObjects.ULServices;
import utilities.WebDriverUtils;

public class BaseClass {
    public static WebDriver driver;
	
    public static String baseUrl="https://www.urbanladder.com/";
    public static HomePage hp;
    public static BookShelvesPage bp;
    public static ULServices up;
    
    @BeforeTest
    @Parameters({"Browser"})
	public void setup(@Optional("chrome")String browserChoice)
	{
		driver=WebDriverUtils.createWebDriver(driver, browserChoice);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
    @BeforeClass
    public void pages() {
//    	System.out.println("Working");
    	hp=new HomePage(driver);
    	bp=new BookShelvesPage(driver);
    	up=new ULServices(driver);
    }
    
//    @AfterClass
//    public void homePage1() {
////    	System.out.println(driver.getTitle());
//    	hp=new HomePage(driver);
//    }
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
