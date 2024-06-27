package com.test.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWrapper extends GlobalObjectRepository
{
	public static void enterURL(String url) {
		try {
			driver.navigate().to(url);
			log.info("Entering url :{}" , url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static WebElement findElement(By locator)
	{
		WebElement element = driver.findElement(locator);
		return element;
	}

	//working
	public static void WaitForElementToBeClickable(By locator)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	//working
	public static void WaitForElementToBrVisible(By locator)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static String getTextOfWebelement(By by) throws Exception {
		return getTextOfWebelement(getWebElement(by));
	}

	public static String getTextOfWebelement(String locator) throws Exception {
		return getTextOfWebelement(getWebElement(locator));
	}

	public static WebElement getWebElement(WebElement webElement) throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(webElement));
			return wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (StaleElementReferenceException e) {
			hardWait(5);
			System.err.println("stale element handled");
			return wait.until(ExpectedConditions.elementToBeClickable(webElement));

		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	public static String getTextOfWebelement(WebElement webElement) throws Exception {
		try {
			return webElement.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void hardWait(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			log.error(e);
		}
	}

	public static WebElement getWebElement(String locator) throws Exception {
		return getWebElement(getByLocator(locator));
	}
	public static WebElement getWebElement(By locator) throws Exception {
		WebElement webElement;
		try {
			webElement = driver.findElement(locator);
		} catch (StaleElementReferenceException e) {
			hardWait(2);
			webElement = driver.findElement(locator);
			System.err.println("stale element handled");
		}

		return getWebElement(webElement);
	}

	public static By getByLocator(String locator) throws Exception {

		//Splitting locator to extract the Locator_type and Locator_Value 
		String locatorType  = locator.split(":",2)[0]; //Example: locator = id:ABC, then locatorType = id
		String locatorValue = locator.split(":",2)[1]; //Example: locator = id:ABC, then locatorValue = ABC

		By by;

		locatorType = locatorType.toLowerCase();
		switch (locatorType) {
		case "id":
			by = By.id(locatorValue);
			break;

		case "name":
			by = By.name(locatorValue);
			break;

		case "classname":
			by = By.className(locatorValue);
			break;

		case "tagname":
			by = By.tagName(locatorValue);
			break;

		case "linktext":
			by = By.linkText(locatorValue);
			break;

		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;

		case "cssselector":
			by = By.cssSelector(locatorValue);
			break;

		case "xpath":
			by = By.xpath(locatorValue);
			break;

		default:
			by = null;
			log.error("Unknown Locator type: {} " , locatorType);
		}
		return by;
	}
	public static String getPageTitle() 
	{
		return driver.getTitle();
	}

	public static String getOptionFromDropdown(By element)
	{
		Select dropdown = new Select(findElement(element));
		WebElement option = dropdown.getFirstSelectedOption();
		String optionValue = option.getText();
		return optionValue;

	}

	public static int generateRandomNumber()
	{
		Random var = new Random();
		int number =var.nextInt(1000);
		return number;

	}

	public static String captureScreenshot()
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		String image = screenshot.getScreenshotAs(OutputType.BASE64);
		return image;
	}

	public static void getScreenshot()
	{
		test.info(MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
	}
	
	public static void enterText(By by ,String text) throws Exception {
		hardWait(2);
		enterText(getWebElement(by), text);
		hardWait(2);
	}
	public static void enterText(WebElement webElement, String text) throws Exception {
		try {
			waitTillVisible(webElement).clear();
			webElement.sendKeys(text);
			log.info("Entering Text :{} in WebElement :{}", text, webElement);
		} catch (StaleElementReferenceException staleElementException) {
			hardWait(2);
			wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(text);
			log.info("Handled StaleElement Exception, Entering Text :{} in WebElement :{}", text, webElement);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	public static WebElement waitTillVisible(WebElement webElement) throws Exception {
		try {
			return wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	public static void selectVisibleText(WebElement webElement,String text) throws Exception {
		try { 
			hardWait(2);
			Select select = new Select(webElement);
			select.selectByVisibleText(text);
			hardWait(2);
		}		
		catch (Exception e) {
			log.error(e);
			throw e;
		}

	}

	public static void selectByIndex(WebElement webElement, int data) throws Exception {
		try {
			hardWait(2);
			Select select = new Select(webElement);
			select.selectByIndex(data);
		}		
		catch (Exception e) {
			log.error(e);
			throw e;
		}
	}

	public static void clickUsingJavaScript(By by) throws Exception {
		try {
			clickUsingJavaScript(getWebElement(by));
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	public static void clickUsingJavaScript(WebElement webElement) {
		hardWait(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
	}
	public static void openBrowser() throws Exception {
		openBrowser(BROWSER);
	}
	private static void openBrowser(String browserName) throws Exception {
		try {
			browserName = browserName.toLowerCase();
			switch (browserName) {

			case "chrome": {
				
				//++++Driver.exe file with GUI mode....+++++
				//System.setProperty("webdriver.chrome.driver", WEBDRIVER_EXECUTABLES_PATH +"chromedriver.exe");
				//driver = new ChromeDriver(getChromeBrowserProfile());
				//++++.................................+++++
				
				//****WebDriver manager with headless mode *******
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(getChromeBrowserProfile());
				//****.....................................*******
				
				log.info("Opening {} browser.", browserName);
				break;
			}

			case "firefox": {
				System.setProperty("webdriver.gecko.driver", WEBDRIVER_EXECUTABLES_PATH +"geckodriver.exe");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
				driver = new FirefoxDriver();
				log.info("Opening {} browser.", browserName);
				break; 
			}
			default:
				log.error("Pass a proper name for the Browser i.e. Chrome, firefox, IE");
				System.exit(0);
			}
			actions	= new Actions(driver); //Initialisied Actions
			jse 	= (JavascriptExecutor) driver;//Initialisied JavaScriptExecutor 
			wait 	= new WebDriverWait(driver, WEBDRIVER_WAIT_TIME);
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	//This method is used to fetch the default Chrome profile
		public static ChromeOptions getChromeBrowserProfile() 
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			//add on
			//chromeOptions.addArguments("--headless=new");
			//..
			chromeOptions.addArguments("chrome.switches", "--disable-extensions");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-save-password");
			chromeOptions.addArguments("disable-infobars");
			chromeOptions.addArguments("--disable-notifications");
			return chromeOptions;
		}
		
		public static void scrolldown()
		{
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)", "");
			hardWait(2);
		}

		public static void scrolldUp()
		{
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
			hardWait(2);
		}
		
		public static void pageRefresh()
		{
			hardWait(1);
			driver.navigate().refresh();
			hardWait(1);
		}
		
		public static ExtentReports extentReport()
		{
			//To get current date

			SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			Date date = new Date();
			String ActualDate = formate.format(date);

			String reportPath = System.getProperty("user.dir")+"/Report/ExecutionReport_"+ActualDate+".html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

			extent = new ExtentReports();
			extent.attachReporter(spark);

			spark.config().setTheme(Theme.DARK);
			spark.config().setReportName("Kapture Functional test cases");

			return extent;

		}

}
