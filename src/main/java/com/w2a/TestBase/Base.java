package com.w2a.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelData;
import utilities.ExtentManager;

import utilities.Utilities;

public class Base {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties objects_repo = new Properties();
	public static FileInputStream fis;
	public static ExcelData excel = new ExcelData(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\PageObjectModel.xlsx");
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest currenttest;
	public static String browser;
	public static TopMenu menu;
	
	/*
	 * Logs
	 * Properties - config and objects_repo
	 * Excel
	 * Implicit and Explicit Waits
	 * Extent Report	 * 
	 */

	public Base() throws IOException {
		if (driver == null) {
			// Load config and obj_repo (Properties) files
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			System.out.println("Config file loaded");

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\ObjectRepository.properties");
			objects_repo.load(fis);
			System.out.println("Object Repository loaded");

			// Setting Up and environment -- Jenkin Browser Filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser").toString();
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			// Calling browser API based on the Jenkin's selection
			if (config.get("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\geckodriver.exe");
			} else if (config.get("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\chromedriver.exe");
				// creating ChromeOptions object here will allow us to get rid of windows alerts
				// alerts and popups:

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				// adding desired capabilities in webdriver object
				driver = new ChromeDriver(options);
			}

			driver.get(config.getProperty("url"));
			log.debug("Navigated to the site : " + config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")),
					TimeUnit.SECONDS);

			wait = new WebDriverWait(driver, 5);
			// TopMenu object has been created here and can be accessed using classname
			// as both WebDriver and TopMenu class's objects are static
			menu = new TopMenu();
		}
	}

	//Common Keywords
	public static void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(objects_repo.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(objects_repo.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(objects_repo.getProperty(locator))).click();
		}
		else if(locator.endsWith("_LINK")){
			driver.findElement(By.linkText(objects_repo.getProperty(locator))).click();
		}
		log.debug("Clicking on the element : "+locator);
		currenttest.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(objects_repo.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(objects_repo.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(objects_repo.getProperty(locator))).sendKeys(value);
		}
		log.debug("Entering value : "+value+ " in a locator : "+locator);
		currenttest.log(LogStatus.INFO, "Entering value in : " + locator);
	}
	
	static WebElement dropdown;
	public static void select(String locator, String value)
	{
		if(locator.endsWith("_CSS"))
		{
			dropdown = driver.findElement(By.cssSelector(objects_repo.getProperty(locator)));
		}
		else if(locator.endsWith("_XPATH"))
		{
			dropdown = driver.findElement(By.xpath(objects_repo.getProperty(locator)));
		}
		else if(locator.endsWith("_ID"))
		{
			dropdown = driver.findElement(By.id(objects_repo.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		log.debug("Selecting from an element : "+locator+ "Value Selected : "+value);
		currenttest.log(LogStatus.INFO, "Selecting the dropdown element : "+locator+ "by value : "+value);
	}
	
	public static boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public static void verifyEquals(String expected, String actual) throws IOException
	{
		try {
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t)
		{
			Utilities.captureScreenshot();
			Reporter.log("<br>"+ "Verification Failure : "+t.getMessage());
			Reporter.log("<a target=\"_blank\" href="+Utilities.ScreenShotName+"<img src="+Utilities.ScreenShotName+" height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			currenttest.log(LogStatus.FAIL, "Verification failed with exception : "+t.getMessage().toString());
			currenttest.log(LogStatus.FAIL, currenttest.addScreenCapture(Utilities.ScreenShotName));
		}
	}
	
	public static void quit()
	{
		driver.quit();
	}
}
