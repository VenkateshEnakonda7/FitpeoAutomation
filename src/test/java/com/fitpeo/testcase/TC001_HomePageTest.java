package com.fitpeo.testcase;

import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fitpeo.pageobject.HomePage;

public class TC001_HomePageTest extends BaseClass{
	HomePage homepage;
	String hpExpectedValue = "FitPeo helps you with";
	
	
	@Test
	public void verify_navigation_of_homepage() {
		try 
		{
		homepage = new HomePage(driver);
		assertEquals(hpExpectedValue, homepage.validateHomepage());
		logger.info("Successfully navigated to home page");
		}
		catch(Exception e) {
			Assert.fail();
			logger.error("unable to navigate to the homepage");
		}
		
	}
	
	

}
