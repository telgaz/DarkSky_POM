package com.qa.darksky.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.darksky.base.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//define initialize driver - initialize properties
	
//	WebDriver driver;
	Properties prop;
	
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver>tlDriver=new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		
		return tlDriver.get();
	}
	
	
	
	
	public WebDriver init_driver(String browserName) {
		
		highlightElement=prop.getProperty("highlight").equals("yes")?true:false;
		optionsManager=new OptionsManager(prop);
		
		System.out.println("Browser name is "+ browserName);
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
		
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
			
			
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			
			tlDriver.set(new SafariDriver());
		
		}
		else {
			System.out.println("Browser name "+ browserName + " is not found");
		
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
	//	return driver;
	
	}
	
	public Properties init_properties() {
		
		prop = new Properties();
		String path=null;
		String env=null;

		
		
		try {
			env=System.getProperty("env");
			if(env.equals("qa")) {
			//	path="/Users/tugbaelgaz/eclipse-workspace/Darksky_Project/src/main/java/com/qa/darksky/config/config.properties";
				path="./src/main/java/com/qa/darksky/config/config.properties";
				//we can use either of them
			}else if(env.equals("stg")) {
				path="./src/main/java/com/qa/darksky/config/config.properties";
			}
		} catch (Exception e) {
			path="./src/main/java/com/qa/darksky/config/config.properties";
			
		}
		
		
		
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("some issue happened with config properties... Correct the file");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * Screenshot
	 * @return
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshots/" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.err.println("screenshot captured failed...");
		}
		return path;
	}
	
	
	
	
	
	
	
	
}	
	