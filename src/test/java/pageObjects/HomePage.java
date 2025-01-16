package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtils;

public class HomePage {
	public WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[normalize-space()='Oasis Collection']")
	WebElement oasisEle;
	
	@FindBy(xpath="//span[normalize-space()='Storage Furniture']")
	WebElement furnitureEle;
	
	@FindBy(xpath="//li[@class='header__topBarIconList_profile-icon']")
	WebElement loginIcon;
	
	@FindBy(xpath="//header[@id='header']/div[1]/div/section[3]/ul/li[2]/span/ul")
	WebElement loginDropdown;
	
	@FindBy(xpath="//span[normalize-space()='Oasis Collection']/following-sibling::div//a")
	List<WebElement> ele;
	
	@FindBy(id="login_dialog")
	WebElement modal;
	
	@FindBy(id="header-icon-login")
	WebElement loginLink;
	
	@FindBy(xpath="//div[@id='password-credentials']//input[@id='spree_user_email']")
	WebElement emailInput;
	
	@FindBy(xpath="//input[@id='spree_user_password' and @placeholder='Password']")
	WebElement passInput;
	
	@FindBy(id="ul_site_login")
	WebElement loginBtn;
	
	public boolean checkStorageFurniture() {
		return furnitureEle.isDisplayed();
	}
	
	public String returnUrl() {
		return driver.getCurrentUrl();
	}
	public String returnTitle() {
		return driver.getTitle();
	}
	public boolean checkOasisCol() {
		return furnitureEle.isDisplayed();
	}

	public boolean checkLogin() {
		// TODO Auto-generated method stub
		return (loginIcon.isDisplayed() && loginIcon.isEnabled());
	}

	public boolean invokeDropDown() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		
		Actions action=new Actions(driver);
		action.moveToElement(oasisEle).perform();
		
		try {
//			System.out.println(driver.findElement(By.xpath("//span[normalize-space()='Oasis Collection']//following-sibling::div")));
			Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='topnav_wrapper']/ul/li[1]/div")))).isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean collectAndCheckOasisItems() throws IOException  {
		// TODO Auto-generated method stub
//        Actions action=new Actions(driver);
//		action.moveToElement(oasisEle).perform();
		List<WebElement> subMenu=driver.findElements(By.xpath("//span[normalize-space()='Oasis Collection']/following-sibling::div/div/ul/li/ul/li/a/span"));
		String oasisCollection[]=new String[6];
		
		// ANSI escape code for blue text
        String ANSI_BLUE = "\u001B[34m";
        // ANSI escape code to reset color
        String ANSI_RESET = "\u001B[0m";

		System.out.println(ANSI_BLUE+"Oasis Collections Sub-Menu: "+ANSI_RESET);
		for(int i=0;i<subMenu.size();i++) {
			oasisCollection[i]=subMenu.get(i).getText();
			System.out.println(subMenu.get(i).getText());
		}
		ExcelUtils util=new ExcelUtils("src\\test\\resources\\outputData.xlsx");
		util.setCellData("Sub-Menu","Oasis Collection",oasisCollection);
		return (oasisCollection.length==6);
	}
	
	public boolean checkForBookshelvesSubmenu() {
		try {
			furnitureEle.click();
			return furnitureEle.findElement(By.xpath("//span[normalize-space()='Bookshelves']/parent::a")).isEnabled();
		}
		catch(Exception e) {
			return false;
		}
	}

	
     public void clickBookShelves() throws Exception{
		// TODO Auto-generated method stub
		Actions action=new Actions(driver);
		action.moveToElement(furnitureEle).perform();
		String bookShelveLink=furnitureEle.findElement(By.xpath("//span[normalize-space()='Bookshelves']/parent::a")).getDomProperty("href");
		driver.get(bookShelveLink);
	}

	public boolean checkLoginDropDown() {
		// TODO Auto-generated method stub
		Actions action=new Actions(driver);
		action.moveToElement(loginIcon).perform();
//		Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(loginDropdown));
		return loginDropdown.isDisplayed();
	}

	public void clickLoginLink() {
		// TODO Auto-generated method stub
		Actions action=new Actions(driver);
		action.moveToElement(loginIcon).perform();
		((JavascriptExecutor)driver).executeScript("arguments[0].click()",loginLink);
		Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(modal));
	}



	public boolean checkModalPresent() {
		// TODO Auto-generated method stub
		
		return modal.isDisplayed();
	}

	public void enterValidEmail(String email) {
		// TODO Auto-generated method stub
//		Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("signup-module"))));
//		Thread.sleep(5000);
//		emailInput.sendKeys(email);
		((JavascriptExecutor)driver).executeScript("arguments[0].value='"+email+"'", emailInput);
	}
	public void enterInvalidPassword(String pass) {
		((JavascriptExecutor)driver).executeScript("arguments[0].value='"+pass+"'", passInput);
	}
	public void clickLogin() {
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", loginBtn);
	}
	

}
