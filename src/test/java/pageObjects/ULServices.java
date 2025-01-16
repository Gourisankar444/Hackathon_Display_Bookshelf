package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ExcelUtils;

public class ULServices {
	public WebDriver driver;
	public static List<String> errorMessages = new ArrayList<>();
			
	public ULServices(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()=\"UL Services\"]")
	WebElement ULServicesLink;
	
	@FindBy(xpath="(//input[@type='text'])[1]")
	WebElement nameTextBox;
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	WebElement mobTextBox;
	
	@FindBy(xpath="(//input[@type='email'])[1]")
	WebElement mailTextBox;
	
	@FindBy(xpath="(//input[@type='text'])[3]")
	WebElement cityTextBox;
	
	@FindBy(xpath="//*[@id='mG61Hd']/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div[1]/div[1]")
	WebElement timeSlotDropDown;
	
	public String returnTitle() {
		return driver.getTitle();
	}
	
	public boolean checkULButton() {
		return ULServicesLink.isDisplayed();
	}
	
	public void navigateToULServices() {
		JavascriptExecutor js=(JavascriptExecutor)driver;  
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		js.executeScript("arguments[0].click()",ULServicesLink);

	}
	
	@SuppressWarnings("deprecation")
	public static void checkForError(WebDriver driver, String xpath) throws IOException { 
		try { 
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
			String errMsg = driver.findElement(By.xpath(xpath)).getText(); 
//			System.out.println("Error : " + errMsg); 
			
			errorMessages.add(errMsg);
			
		} catch (NoSuchElementException e) { 
//			System.out.println("No error message found."); 
		}
	}
	
	public void fillFormWithInvalid() throws IOException {
		WebElement formFrame = driver.findElement(By.xpath("//*[@id=\"page_content\"]/p[4]/iframe"));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;  
		js.executeScript("arguments[0].scrollIntoView();", formFrame);
		
		driver.switchTo().frame(0);
		
		ExcelUtils utilsin = new ExcelUtils("src\\test\\resources\\inputData.xlsx");
		ArrayList<String> al=new ArrayList<>();
		for(int i=1; i<=5; i++)
		{
			String val=utilsin.getCellData("ULData", 2, i);
			al.add(val);
		}
		
		nameTextBox.sendKeys(al.get(0));
		checkForError(driver,"//*[@id='i3']/span");
		
		mobTextBox.sendKeys(al.get(1));
		checkForError(driver,"//*[@id='i8']/span");
		
		mailTextBox.sendKeys(al.get(2));
		checkForError(driver,"//*[@id='i13']/span");
		
		cityTextBox.sendKeys(al.get(3));
		checkForError(driver,"//*[@id='i18']/span");
		
		timeSlotDropDown.sendKeys(al.get(4));
		checkForError(driver,"//*[@id='i23']/span");
		
		ExcelUtils utils = new ExcelUtils("src\\test\\resources\\outputData.xlsx"); 
		utils.setCellData("Errors", "Error Message", errorMessages.toArray(new String[0]));
		
		}
	
	public void fillFormWithValid() throws IOException {
		
		ExcelUtils utilsin = new ExcelUtils("src\\test\\resources\\inputData.xlsx");
		ArrayList<String> al=new ArrayList<>();
		for(int i=1; i<=5; i++)
		{
			String val=utilsin.getCellData("ULData", 1, i);
			al.add(val);
		}

		nameTextBox.sendKeys(al.get(0));
		checkForError(driver,"//*[@id='i3']/span");
		
		mobTextBox.sendKeys(al.get(1));
		checkForError(driver,"//*[@id='i8']/span");
		
		mailTextBox.sendKeys(al.get(2));
		checkForError(driver,"//*[@id='i13']/span");
		
		cityTextBox.sendKeys(al.get(3));
		checkForError(driver,"//*[@id='i18']/span");
		
		timeSlotDropDown.sendKeys(al.get(4));
		checkForError(driver,"//*[@id='i23']/span");
		
		ExcelUtils utils = new ExcelUtils("src\\test\\resources\\outputData.xlsx"); 
		utils.setCellData("Errors", "Error Message", errorMessages.toArray(new String[0]));
		
		driver.navigate().back();
		}
}
