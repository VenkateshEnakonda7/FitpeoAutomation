package com.fitpeo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//*[@id=\"calendly\"]/div[1]/h5")
	WebElement validHomePage;
	
	
	
	public String validateHomepage() {
		try {
			return validHomePage.getText();			
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
	

	

	
}
