package com.test.framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;

public  class GlobalVariablesFramework 
{
	public static final Logger log = initLogger();

	public static Actions               actions; 
	public static Alert                 alert;
	public static By                    by; 
	public static String 			    currUserDirectory = System.getProperty("user.dir");
	public static WebDriver             driver;
	public static JavascriptExecutor    jse;
	public static SoftAssert 		    softAssert = new SoftAssert();
	public static WebDriverWait         wait; 
	public static WebElement            webElement;
	public static Select                select;
	public static ExtentReports         extent;
	public static ExtentTest            test;
	public static String                data;
	public static int                   randomNumer;
	public static int               publishedVersion;

	public final static String LOG4J2_CONFIG_FILE_PATH 	       = "./src/test/resources/config/logger/log4j2.xml";
	public final static String WEBDRIVER_EXECUTABLES_PATH 	   = "./src/test/resources/drivers/";

	public final static String BROWSER = "chrome";
	//public final static String BROWSER = YamlWrapper_jaJP.getBrowserName();

	public final static Integer WEBDRIVER_WAIT_TIME = 30;
	public static String appUrl 	= "https://demo.kapturekm.com/ksearch";
	public static String byEmail     = "Abhishekl@yopmal.com";
	public static String byPassword     = "";
	
	public static Logger initLogger() {
		System.setProperty("log4j.configurationFile",LOG4J2_CONFIG_FILE_PATH);
		return LogManager.getLogger(GlobalVariablesFramework.class.getName());
	}

	public static void renameAndMovePreviousTestNGReport() {
		try {
			String timeStamp     = new SimpleDateFormat("HH-mm a - dd MMM YYYY").format(new Date());
			File sourceFile 	 = new File("./test-output/emailable-report.html");
			File destinationFile = new File("./resources/reports/" + timeStamp + "_emailable-report.html" );
			Files.copy(sourceFile , destinationFile );

		} catch (IOException e) {
			log.error(e);
		}
	}
}
