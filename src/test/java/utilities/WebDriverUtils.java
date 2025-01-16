package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverUtils {
	
	public static WebDriver createWebDriver(WebDriver driver, String browserChoice) {
		switch(browserChoice.toLowerCase())
		{
		case "chrome": 
			driver=new ChromeDriver();
			break;
		case "edge" : 
			driver=new EdgeDriver();
			break;
		default: 
			System.out.println("Invalid browser");
		}
		return driver;
	}
}
