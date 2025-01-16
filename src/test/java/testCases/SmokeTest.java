package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;


public class SmokeTest extends BaseClass{
	@Test(priority=0,groups= {"smoke"})
	public void testValidUrl() {
		
		Assert.assertEquals(baseUrl, hp.returnUrl());
	}
	
	@Test(priority=1,groups= {"smoke"})
	public void testFieldPresence() {
		Assert.assertTrue(hp.checkStorageFurniture());
	}
	@Test(priority=2,groups= {"smoke"})
	public void testFieldOasisPresence() {
		Assert.assertTrue(hp.checkOasisCol());
	}
	@Test(priority=3,groups= {"smoke"})
	public void testULServicesPresence() {
		Assert.assertTrue(up.checkULButton());
	}
}
