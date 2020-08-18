package com.qa.darksky.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.darksky.base.BasePage;

public class ElementUtil extends BasePage{

WebDriver driver;
	
	public ElementUtil(WebDriver driver){
		this.driver = driver;
	}

	public void mouseOver(By locator) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(locator);
		action.moveToElement(element).build().perform();	
	}

	public WebElement getElement(By locator) {	
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = driver.findElement(locator);
		return element; 
	}
	
	public void waitForElementAndClick(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public void waitForElement(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}
	
	public void clickOn(By locator) {
		driver.findElement(locator).click();
	}
	
	public void sendKeys(By locator, String key) {
		driver.findElement(locator).sendKeys(key);	
	}
	
	public String  verifyGetText(By locator, String verifyText ) {
		String text = driver.findElement(locator).getText();
		if (text.equals(verifyText)) {
			System.out.println("Text is correct.");
		} else {
			System.out.println("Text is not correct.");
		}
		return text;
	}
	public String doGetPageTitle() {
		
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting the title...");
		}
		return null;
	}	
	
	public void doClick(By locator) {
		
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occured while clicking the web element");
		}
	}
	public void mouseOverClick(By locator, WebElement elementClicked) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		WebElement mouseOverAction = driver.findElement(locator);
		action.moveToElement(mouseOverAction).click(elementClicked).build().perform();	
	}
	public String doGetText(By locator) {
		
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting text...");
		}
		return null;
	}
	
}



