package com.fitpeo.testcase;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fitpeo.pageobject.RevenueCalculatorPage;

public class TC002_RevenueCalculatorTest extends BaseClass{
	
	RevenueCalculatorPage revenueCal;
	String rcExpectedValue = "Medicare Eligible Patients";
	
	@Test(priority=1)
	public void verify_navigation_of_revenue_calculator() 
	{
		try 
		{
			revenueCal = new RevenueCalculatorPage(driver);
			revenueCal.clickOnRevenueCalculatorTab();
			assertEquals(rcExpectedValue, revenueCal.validateRevenueCalculator());
			logger.info("successfully navigated to revenue calculator page");
		}catch(Exception e) 
		{
			Assert.fail();
			logger.error(e.getMessage());
		}
	}
	
	@Test(priority=2)
	public void verify_page_is_scrollable_till_the_slider() 
	{
		revenueCal = new RevenueCalculatorPage(driver);
		revenueCal.scrollDownToTheSliderSection();
		logger.info("page is scrolled till the slider section");
	}

	@Test(priority=3)
	public void verify_the_slider_is_adjustable_till_the_value() 
	{
		revenueCal = new RevenueCalculatorPage(driver);
		revenueCal.adjustTheSliderTo(prop.getProperty("adjustoffset"));
		logger.info("slider is adjusted to the preffered value");
	}
	
	@Test(priority=4)
	public void validate_the_slider_value_and_input_value()
	{
		try 
		{
			revenueCal = new RevenueCalculatorPage(driver);
			String adjustedSliderValue = prop.getProperty("adjustedslidervalue");
			String sliderInputValue = revenueCal.getValueOfTheTextField();
			Assert.assertEquals(sliderInputValue,adjustedSliderValue);
			logger.info("validation got successful for the slider value and slider text input value");
		}
		catch(Exception e) 
		{
			Assert.fail();
			logger.error("validation got failed for slider value and input value");
		}
		
	}
	
	@Test(priority=5)
	public void verify_the_slider_input_value_updatable() 
	{
		revenueCal = new RevenueCalculatorPage(driver);
		revenueCal.updateTheTextField(prop.getProperty("updatesliderinputtext"));
		logger.info("slider input value is successfuly updated");
	}
	
	@Test(priority=6)
	public void validate_the_slidervalue_with_updated_sliderinput() 
	{
		try 
		{
			revenueCal = new RevenueCalculatorPage(driver);
			String adjustedSliderValue = prop.getProperty("updatesliderinputtext");
			String sliderInputValue = revenueCal.getValueOfTheTextField();
			Assert.assertEquals(sliderInputValue,adjustedSliderValue);
			logger.info("validation is successful for updated slider value and slider input value");
		}
		catch(Exception e) 
		{
			Assert.fail();
			logger.error("validation got failed for updated slider input and slider value");
		}
	}
	
	@Test(priority=7)
	public void verify_the_cptCodes_are_clickable() 
	{
		try 
		{
			revenueCal = new RevenueCalculatorPage(driver);
			revenueCal.updateTheTextField(prop.getProperty("adjustedslidervalue"));
			List<String> cptCodes = Arrays.asList("CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474");
			revenueCal.selectTheCPTCodeCheckBox(cptCodes);
			logger.info("selected checkbox of cptcodes are clicked successfully");
		}
		catch(Exception e) 
		{
			e.getMessage();
			logger.error(e.getMessage());
		}
	}
	
	@Test(priority=8)
	public void verify_the_total_recurring_reimbursement() {
		try {
			revenueCal = new RevenueCalculatorPage(driver);
			String ActualTotalReimbers = revenueCal.getTotalReimbursementValue();
			String ExpexctedTotalReimbers = prop.getProperty("actualtotalreimbers");
			Assert.assertEquals(ActualTotalReimbers, ExpexctedTotalReimbers);
			logger.info("successfully validated the total recurring reimbursement");
		}
			catch(Exception e) 
		{
				Assert.fail();
				logger.error("validation got failed for total recurring reimbursement");
		}
	}
}
