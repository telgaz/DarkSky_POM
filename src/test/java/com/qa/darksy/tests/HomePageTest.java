package com.qa.darksy.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.page.HomePage;
import com.qa.darksky.util.ElementUtil;

public class HomePageTest {

	
	WebDriver driver;
	HomePage homePage;
	BasePage basePage;
	Properties prop;
	ElementUtil elementUtil;
	
	
	@BeforeTest
	public void setup() {
		
		basePage =new BasePage();
		prop=basePage.init_properties();	
		String browserName=prop.getProperty("browser");
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		homePage=new HomePage(driver);
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() throws InterruptedException {
		homePage.verifyPageTitle();
		
	}
	
	@Test(priority=3)
	public void compareMaxDegressTest() throws InterruptedException {
		homePage.compareMaxDegrees();
	}
	@Test(priority=2)
	public void compareMinDegreesTest() throws InterruptedException {
		homePage.compareMinDegrees();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
