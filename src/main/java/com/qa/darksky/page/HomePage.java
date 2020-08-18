package com.qa.darksky.page;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.util.ElementUtil;
import com.qa.darksky.util.JavaScriptUtil;


public class HomePage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil js;
	
	By todayMin=By.xpath("(//span[@class='minTemp'])[position()=1]");
	By todayMax=By.xpath("(//span[@class='maxTemp'])[position()=1]");
	By today=By.xpath("(//span[@class='name'])[position()=1]");
//	By today=By.xpath("span[contains(text(),'Today')]");
//	By timeline=By.xpath("(//div[@class='timeline']//div[@class='temps']//span[@class='first'])[position()=2]");
	By timeline=By.xpath("//div[@class='temps'][1]");
	By asssertionMin=By.xpath("(//span[@class='temp'])[1]");
	By assertionMax=By.xpath("(//span[@class='temp'])[2]");
	//By assertionMax=By.xpath("(//span[@class='lowTemp swap'])[position()=1]");
	By currentLocation=By.xpath("//form[@id='searchForm']//input[@type='text']");

	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
		js=new JavaScriptUtil(driver);
		
	}

	public void verifyPageTitle() throws InterruptedException {
		String title=elementUtil.doGetPageTitle();
		System.out.println(title);
		Thread.sleep(2000);
		Assert.assertEquals(title, "Dark Sky - Broadway, New York, NY");
		
	}

	public void compareMinDegrees() throws InterruptedException {

		JavaScriptUtil.scrollDownPage(900, driver);
	//	elementUtil.waitForElementAndClick(today);
	    Thread.sleep(2000);
		elementUtil.clickOn(today);
	//	Thread.sleep(2000);
		
		
		WebElement minimum=driver.findElement(todayMin);
		String minDegree=minimum.getText();
		System.out.println(minDegree);
		Thread.sleep(2000);
		
		WebElement assertMin=driver.findElement(asssertionMin);
		String min=assertMin.getText();
		System.out.println(min);

		
		Assert.assertEquals(minDegree, min);
	}
	
	public void compareMaxDegrees() throws InterruptedException {

		Thread.sleep(2000);
	    WebElement max=driver.findElement(todayMax);
		String maxDegree=max.getText();
		System.out.println(maxDegree);
	
		WebElement assertMax=driver.findElement(assertionMax);
		String maximum=assertMax.getText();
		System.out.println(maximum);
		
		Assert.assertEquals(maxDegree, maximum);
		
	}
	
	
}
