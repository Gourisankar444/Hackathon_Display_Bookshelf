package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import utilities.DataProviders;

public class ULServicesFormTest extends BaseClass{

	@Test(priority=18,dataProvider="configData",dataProviderClass=DataProviders.class)
	public void checkULPageTitle(String data[]) {
		up.navigateToULServices();
		Assert.assertEquals(up.returnTitle().trim(),data[5]);
	}
	
	@Test(priority=19, dependsOnMethods= {"checkULPageTitle"})
	public void FillULInvalidData() throws IOException {
		// ANSI escape code for blue text
        String ANSI_BLUE = "\u001B[34m";
        // ANSI escape code to reset color
        String ANSI_RESET = "\u001B[0m";

		System.out.println(ANSI_BLUE+"\nForm submission: "+ANSI_RESET);
		up.fillFormWithInvalid();
		System.err.println("Fill all the details correctly in the form!");
	}
	
	@Test(priority=20, dependsOnMethods= {"FillULInvalidData"})
	public void FillULValidData() throws IOException {
		up.fillFormWithValid();
	}
}
