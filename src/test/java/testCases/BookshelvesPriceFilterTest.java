package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import utilities.DataProviders;

public class BookshelvesPriceFilterTest extends BaseClass{

	@Test(priority=4)
	public void bookShelvesSubmenuPresence() {
		Assert.assertTrue(hp.checkForBookshelvesSubmenu());
	}
	@Test(priority=5,dependsOnMethods= {"bookShelvesSubmenuPresence"})
	public void bookShelvesClick() {
		try {
			hp.clickBookShelves();
			Assert.assertTrue(true);
		}
		catch(Exception e) {
			Assert.fail();
		}
	}
	@Test(priority=6,dependsOnMethods= {"bookShelvesClick"},dataProvider="configData",dataProviderClass=DataProviders.class)
	public void checkBookshelvesPageTitle(String data[]) {
		Assert.assertEquals(bp.returnTitle().trim(),data[2]);
	}
	
	@Test(priority=7,dependsOnMethods= {"checkBookshelvesPageTitle"})
	public void checkFilters() {
		Assert.assertTrue(bp.checkAllFilters());
	}
	
	@Test(priority=8)
	public void checkPriceTagSlider() {
		Assert.assertTrue(bp.checkslider());
	}
	
	@Test(priority=9)
	public void testPriceTagSliderWithValue() {
		Assert.assertTrue(bp.dragMaxSliderAndCheck());
	}
	@Test(priority=10)
	public void checkStorageTypeTag() {
		Assert.assertTrue(bp.checkOpenType());
	}
	
	@Test(priority=11)
	public void testStorageTypeTagWithValue() {
		bp.selectOpenStorageTypeAndCheck();
	}
	
	@Test(priority=12)
	public void testExcludeOutOfStock() {
		Assert.assertTrue(bp.selectExOutOfStockAndCheck());
	}
	
	@Test(priority=13)
	public void testSortBy() {
		Assert.assertTrue(bp.checkAndSelectSortBy());
	}
	
	@Test(priority=14,dependsOnMethods= {"testPriceTagSliderWithValue","checkStorageTypeTag","testExcludeOutOfStock", "testSortBy"})
	public void testPresenceOfBookshelves() throws IOException {
		List<WebElement> itemsList=bp.getReqItems();
		List<String> nameList=bp.getNameOfReqItems(itemsList);
		List<String> priceList=bp.getPriceOfReqItems(itemsList);
		bp.storeNameList(nameList.toArray(new String[0]));
		bp.storePriceList(priceList.toArray(new String[0]));
		
		// ANSI escape code for blue text
        String ANSI_BLUE = "\u001B[34m";
        // ANSI escape code to reset color
        String ANSI_RESET = "\u001B[0m";

//		System.out.println("\nTop 3 Affordable Open Storage Bookshelves Under Rs. 15000 (In Stock Only): ");
        // Print Blue text
		System.out.println(ANSI_BLUE + "\nTop 3 Affordable Open Storage Bookshelves Under Rs. 15000 (In Stock Only): " + ANSI_RESET);
		
		for (int i = 0; i < nameList.size(); i++) { 
			System.out.println(nameList.get(i) + ", Price: " + priceList.get(i));
		}
		System.out.println();
		Assert.assertTrue(nameList.size()==3);
	}
	
	@Test(priority=15,dataProvider="configData",dataProviderClass=DataProviders.class, dependsOnMethods="testPresenceOfBookshelves")
	public void testUrlAfterBack(String data[]) {
		Assert.assertTrue(bp.backToHomePage(data[4]));
	}
}
