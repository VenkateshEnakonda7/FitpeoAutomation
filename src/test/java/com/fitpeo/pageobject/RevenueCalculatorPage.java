package com.fitpeo.pageobject;

import java.awt.Checkbox;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevenueCalculatorPage extends BasePage{

	Actions act = new Actions(driver);
	
	public RevenueCalculatorPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[text() = \"Revenue Calculator\"]")
	WebElement revenueCalculatorTab;
	
	@FindBy(xpath="//*[text()=\"Medicare Eligible Patients\"]")
	WebElement validRevenueCalculatorTxt;
	
	@FindBy(xpath="//*[text()=\"Medicare Eligible Patients\"]")
	WebElement scrollDownTill;
	
	@FindBy(xpath="//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']")
	WebElement slider;
	
	@FindBy(xpath="//input[@aria-invalid='false']")
	List<WebElement> sliderInput;
	
	@FindBy(xpath="//input[@aria-invalid='false']")
	WebElement sliderInputValue;
	
	@FindBy(xpath="//*[@class='MuiBox-root css-1p19z09']//p[@class='MuiTypography-root MuiTypography-body1 inter css-1s3unkt']")
	List<WebElement> cptLabels;
	
	@FindBy(xpath="//*[@class='MuiBox-root css-1p19z09']//label//input")
	List<WebElement> cptCheckBoxes;
	
	@FindBy(xpath="//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'][normalize-space()='$110700']")
	WebElement totalReimbursement;
	
	@FindBy(xpath="/html/body/div[2]/div[1]/div[2]/div[1]/p[1]")
	WebElement scrollToCpt;
	
	public RevenueCalculatorPage clickOnRevenueCalculatorTab() {
		revenueCalculatorTab.click();
		return this;
	}
	
	public String validateRevenueCalculator() {
		try {
			return validRevenueCalculatorTxt.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
	public RevenueCalculatorPage scrollDownToTheSliderSection() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",scrollDownTill);
		}catch(Exception e) {
			 e.getMessage();
		}
		return this;
	}
	
	public RevenueCalculatorPage adjustTheSliderTo(String value) {
		act.dragAndDropBy(slider, 93, 0).perform();   //(718, 663) 
        act.keyDown(Keys.ARROW_RIGHT).keyDown(Keys.ARROW_RIGHT).keyDown(Keys.ARROW_RIGHT).keyUp(Keys.ARROW_RIGHT).keyUp(Keys.ARROW_RIGHT).keyUp(Keys.ARROW_RIGHT).perform();
		return this;
	}
	
	public String getValueOfTheTextField() {
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElements(sliderInput)).get(0).getAttribute("value");
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public RevenueCalculatorPage updateTheTextField(String value) {
		sliderInputValue.click();
		act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		act.keyDown(Keys.BACK_SPACE).sendKeys(value).keyUp(Keys.BACK_SPACE).perform();
		return this;
	}
	
	public RevenueCalculatorPage  selectTheCPTCodeCheckBox(List<String> cptCodes) {
		for(String code:cptCodes) {
			for(int i=0;i<cptLabels.size();i++) {
				if(cptLabels.get(i).getText().equals(code)) {
					cptCheckBoxes.get(i).click();
					break;
				}
			}
		}
		return this;
	}

	public String getTotalReimbursementValue() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",scrollToCpt);
		String totalReim = wait.until(ExpectedConditions.visibilityOf(totalReimbursement)).getText();
		return totalReim;
	}
}
