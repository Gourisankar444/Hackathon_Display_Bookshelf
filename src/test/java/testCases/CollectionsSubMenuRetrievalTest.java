package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class CollectionsSubMenuRetrievalTest extends BaseClass{
	
	@Test(priority=16)
	public void dropDownAppearanceValidation() {
		Assert.assertTrue(hp.invokeDropDown());
	}
	
	@Test(priority=17,dependsOnMethods= {"dropDownAppearanceValidation"})
	public void dropDownSubMenuValidation() throws IOException {
		Assert.assertTrue(hp.collectAndCheckOasisItems());
	}
}
