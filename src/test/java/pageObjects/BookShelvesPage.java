package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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

public class BookShelvesPage {
	public WebDriver driver;
	public BookShelvesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='item' and @data-group='price']")
	WebElement priceTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='storage type']")
	WebElement storageTypeTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='material']")
	WebElement materialTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='brand']")
	WebElement brandTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='mount type']")
	WebElement mountTypeTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='no of shelves']")
	WebElement noOfShelvesTag;
	
	@FindBy(xpath="//li[@class='item' and @data-group='ul assured']")
	WebElement ulAssuredTag;
	
	@FindBy(xpath="//label[@for='filters_availability_In_Stock_Only']")
	WebElement exOutOfStock;
	
	@FindBy(xpath="//div[@class='item' and @data-group='sorting']")
	WebElement sortBy;
	
	@FindBy(xpath="//div[@class='noUi-base']")
	WebElement slider;
	
	@FindBy(xpath="//label[@for='filters_storage_type_Open']")
	WebElement openCheckBox;
	
	@FindBy(xpath="//label[@for='filters_storage_type_Open_And_Closed']")
	WebElement openAndClosedCheckBox;
	
	@FindBy(xpath="//label[@for='filters_storage_type_Closed']")
	WebElement closedCheckBox;
	
	@FindBy(xpath="//div[@class='noUi-handle noUi-handle-upper']")
	WebElement maxSlider;
	
	@FindBy(xpath="//li[@class='selrange-filter' and @data-filter-name='price']")
	WebElement priceRangeTag;
	
	@FindBy(xpath="//label[@for='filters_storage_type_Open']")
	WebElement storageInput;
	
	@FindBy(xpath="//li[@data-filter-name='storage_type' and @data-option-name='Open']")
	WebElement storageInputTag;
	
	@FindBy(xpath="//a[@class='close-reveal-modal hide-mobile']")
	WebElement closeModal;
	
	@FindBy(xpath="//li[@data-option-name='In Stock Only']")
	WebElement outOfStockTag;
	
	@FindBy(xpath="//li[@class='selected']")
	WebElement sortByOption;
	
	@FindBy(xpath="//ul[@class='productlist withdivider clearfix bookshelves productgrid']/li[1]")
	WebElement listEle1;
	
	@FindBy(xpath="//ul[@class='productlist withdivider clearfix bookshelves productgrid']/li[2]")
	WebElement listEle2;
	
	@FindBy(xpath="//ul[@class='productlist withdivider clearfix bookshelves productgrid']/li[3]")
	WebElement listEle3;
	
	public String returnTitle() {
		return driver.getTitle();
	}
	
	public boolean checkPriceTag() {
		Actions action=new Actions(driver);
		Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(closeModal));
		closeModal.click();
		action.moveToElement(priceTag).perform();
		return priceTag.isDisplayed();
	}
	
	public boolean checkStorageTypeTag() {
		return storageTypeTag.isDisplayed();
	}
	
	public boolean checkMaterialTag() {
		return materialTag.isDisplayed();
	}
	
	public boolean checkBrandTag() {
		return brandTag.isDisplayed();
	}
	
	public boolean checkMountTypeTag() {
		return mountTypeTag.isDisplayed();
	}
	
	public boolean checkNoOfShelvesTag() {
		return noOfShelvesTag.isDisplayed();
	}
	
	public boolean checkULAssuredTag() {
		return ulAssuredTag.isDisplayed();
	}
	
	public boolean checkExcludeOutOfStock() {
		return exOutOfStock.isDisplayed();
	}
	
	public boolean checkSortBy() {
		return sortBy.isDisplayed();
	}
	
	public boolean checkAllFilters() {
		if(checkPriceTag() && checkStorageTypeTag() && checkMaterialTag() && checkBrandTag() && checkMountTypeTag() && checkNoOfShelvesTag() && checkULAssuredTag() && checkExcludeOutOfStock() && checkSortBy())
			return true;
		else
			return false;
	}
	
	
	public boolean checkslider() {
		return slider.isDisplayed();
	}
	
	public boolean checkOpenType(){
		Actions action = new Actions(driver);
		action.moveToElement(storageTypeTag).perform();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='dropdown-content' and @style='display: block;']"))));
		return openCheckBox.isDisplayed(); 
	}
	
	public boolean dragMaxSliderAndCheck() {
		try {
			Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			Actions action=new Actions(driver);
			action.moveToElement(priceTag).dragAndDropBy(maxSlider, -272, 0).moveToElement(exOutOfStock).perform();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("listFetchLoader"))));
			WebElement nameOfItem=wait.until(ExpectedConditions.visibilityOf(listEle1.findElement(By.xpath("./child::div//div[@class='product-title product-title-sofa-mattresses']/span"))));
			new Actions(driver).moveToElement(nameOfItem).perform();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public boolean selectOpenStorageTypeAndCheck() {
		try {
			Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			
			storageInput.click();
			new Actions(driver).moveToElement(exOutOfStock).perform();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("listFetchLoader"))));
			WebElement nameOfItem=wait.until(ExpectedConditions.visibilityOf(listEle1.findElement(By.xpath("./child::div//div[@class='price-number']/span"))));
			new Actions(driver).moveToElement(nameOfItem).perform();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean selectExOutOfStockAndCheck() {
		try {
//			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", exOutOfStock);
			Wait<WebDriver> wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			Actions action=new Actions(driver);
			action.moveToElement(exOutOfStock).perform();
			exOutOfStock.click();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("listFetchLoader"))));
			WebElement nameOfItem=wait.until(ExpectedConditions.visibilityOf(listEle1.findElement(By.xpath("./child::div//div[@class='price-number']/span"))));
			action.moveToElement(nameOfItem).perform();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public List<WebElement> getReqItems(){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", listEle1);
		List<WebElement> reqListOfEle=new ArrayList<>();
		reqListOfEle.add(listEle1);
		reqListOfEle.add(listEle2);
		reqListOfEle.add(listEle3);
		return reqListOfEle;
	}
	
	public List<String> getNameOfReqItems(List<WebElement> reqList){
		List<String> nameList=new ArrayList<>();
		nameList.add(reqList.get(0).findElement(By.className("name")).getText());
		nameList.add(reqList.get(1).findElement(By.className("name")).getText());
		nameList.add(reqList.get(2).findElement(By.className("name")).getText());
		return nameList;
	}

	public List<String> getPriceOfReqItems(List<WebElement> itemsList) {
		// TODO Auto-generated method stub
		List<String> priceList=new ArrayList<>();
		priceList.add(itemsList.get(0).findElement(By.xpath("./child::div//div[@class='price-number']/span")).getText());
		priceList.add(itemsList.get(1).findElement(By.xpath("./child::div//div[@class='price-number']/span")).getText());
		priceList.add(itemsList.get(2).findElement(By.xpath("./child::div//div[@class='price-number']/span")).getText());
		return priceList;
	}

	public boolean backToHomePage(String title) {
		// TODO Auto-generated method stub
		while(true) {
			driver.navigate().back();
			if((driver.getTitle().trim()).equals(title)) {
				break;
			}
		}
		return true;
	}

	public boolean checkAndSelectSortBy() {
		try {
			WebElement selectedOptionEle=sortBy.findElement(By.xpath("./child::div/span"));
			return selectedOptionEle.getText().trim().equals("Recommended");
		}
		catch(Exception e) {
			return false;
		}
	}

	public void storeNameList(String[] nameList) throws IOException {
		// TODO Auto-generated method stub
		ExcelUtils utils=new ExcelUtils("src\\test\\resources\\outputData.xlsx");
		utils.setCellData("BookShelves", "Item Name", nameList);
	}

	public void storePriceList(String[] priceList) throws IOException {
		// TODO Auto-generated method stub
		ExcelUtils utils=new ExcelUtils("src\\test\\resources\\outputData.xlsx");
		utils.setCellData("BookShelves", "Price", priceList);
		
	}

	
}