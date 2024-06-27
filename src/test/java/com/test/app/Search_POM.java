package com.test.app;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.test.framework.BaseClass;
import com.test.framework.CommonStaticproperties;

public class Search_POM extends BaseClass
{

	public static By byAskQuestion 				= By.xpath("//a[text()='Ask a Question']");
	public static By byAskQuestion_title 		= By.xpath("//h2[text()='Ask a Question ']");
	public static By byFirstName 				= By.xpath("//label[text()='First name']//..//input");
	public static By byLastName 				= By.xpath("//label[text()='Last name']//..//input");
	public static By byEmailAddress 			= By.xpath("//label[text()='Email address']//..//input");
	public static By bySubject 					= By.xpath("//label[text()='Subject ']//..//input");
	public static By byQuestion					= By.xpath("//label[text()='Question ']//..//textarea");
	public static By byAccount_drp 				= By.xpath("//select[@class='form-select']");
	public static By byAttach_document 			= By.xpath("//input[@id='file-input']");
	public static By bySubmit_btn 				= By.xpath("//button[text()='Submit Your Question ']");
	public static By byHome_btn                 = By.xpath("//a[text()='Home']");
	public static By byPopupText			    = By.xpath("//div[@id='swal2-html-container']");
	public static By bySuccess                  = By.xpath("//h2[@id='swal2-title'][text()='Success']");
	public static By byCaseNumber               = By.xpath("//b[@class='caseNumberClas']");
	public static By byOK_btn                       = By.xpath("//button[text()='Ok']");

	// Error text
	public static By byFirstname_error 			= By.xpath("//span[text()='Please enter first name']");
	public static By byLastname_error 			= By.xpath("//span[text()='Please enter last name']");
	public static By byEmailID_error 			= By.xpath("//span[text()='Please enter email ID']");

	public static By bySubject_error 			= By.xpath("//span[text()='Please enter subject']");
	public static By byQuestion_error 			= By.xpath("//span[text()='Please enter question']");
	public static By byAccount_error 			= By.xpath("//span[text()='Please choose account']");
	public static By byEmailID_invalid 			= By.xpath("//span[text()='Invalid email ID']");
	public static By bySpecialChar_error 		= By.xpath("//span[text()='Allows alphabets only']");

	static Exception error;

	public static void verifyScreen(String screen,By locator) throws Exception
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		Boolean state = false;
		switch(screen)
		{
		case "Ask a Question":
			state = findElement(locator).isDisplayed();
			break;
		default:
			log.info(screen + ", is not maching with any cases");
			throw error; 
		}

		Assert.assertTrue(state, screen +" : is not displayed");
		//highlightWebElement(locator);
		log.info(screen + ": Screen is displayed successfully");

		test.log(Status.PASS, screen + ": Screen is displayed successfully");

	}

	public static void verifyButton(String screen, String button, By locator) throws Exception
	{

		Boolean state = false;

		switch(screen)
		{
		case "Ask a Question":
			switch(button)
			{
			case "Submit Your Question":
				state = findElement(locator).isDisplayed();
				break;
			case "Choose File":
				state = findElement(locator).isDisplayed();
				break;

			default:
				log.info(button + ", is not maching with any cases");
				throw error;  		 
			}
			break;
		default:
			log.info(screen + ", is not maching with any cases");
			throw error; 
		}
		Assert.assertTrue(state, button+ " :********button is not present********** : ");
		//highlightWebElement(locator);
		log.info(button + " : button is displayed in " + screen + " Screen");
		test.log(Status.PASS, button + " : button is displayed in " + screen + " Screen");
	}

	public static void verifyLabel(String screen, String Label, By locator) throws Exception
	{

		Boolean state = false;
		switch(screen)
		{
		case "Ask a Question":

			switch(Label)
			{
			case "ID":
				state = findElement(locator).isDisplayed();
				break;
			default:
				log.info(Label + ", is not maching with any cases");
				throw error; 
			}
			break;
		}
		Assert.assertTrue(state, Label +" : is not displayed");
		//highlightWebElement(locator);
		log.info(Label + ": label is displayed on " + screen);
		test.log(Status.PASS, Label + ": label is displayed on " + screen);
	}

	public static void verifyFields(String screen, String Field, By locator) throws Exception
	{
		WaitForElementToBeClickable(locator);
		Boolean state = false;

		switch(screen)
		{
		case "Ask a Question":
			switch(Field)
			{
			case "First name":
				state = findElement(locator).isDisplayed();
				break;
			case "Last name":
				state = findElement(locator).isDisplayed();
				break;
			case "Email address":
				state = findElement(locator).isDisplayed();
				break;
			case "Subject":
				state = findElement(locator).isDisplayed();
				break;
			case "Question":
				state = findElement(locator).isDisplayed();
				break;
			case "Account":
				state = findElement(locator).isDisplayed();
				break;
			case "Attach Document":
				state = findElement(locator).isDisplayed();
				break;

			default:
				log.info(Field + ", is not maching with any cases");
				throw error;  		 
			}
			break;
		default:
			log.info(screen + ", is not maching with any cases");
			throw error; 
		}
		Assert.assertTrue(state, Field+ " :********field is not present********** : ");
		//highlightWebElement(locator);
		log.info(Field + " : field is displayed in " + screen + " Screen");
		test.log(Status.PASS, Field + " : field is displayed in " + screen + " Screen");
	}

	public static void verifyErrorText(String popup, By locator, String expected) throws Exception
	{
		//WaitForElementToBeClickable(locator);

		Boolean state = false;
		switch(popup)
		{
		case "Error":
			state = driver.findElement(locator).isDisplayed();
			break;
		default:
			log.info(popup + ", is not maching with any cases ");
			throw error;
		}
		//highlightWebElement(locator);
		Assert.assertTrue(state,  " expected text is not displayed");
		log.info(popup + " message is displayed with text : " + expected);
		test.log(Status.PASS,popup + " message is displayed with text : " + expected);

	}

	public static void verifyPopuptext(String popup, By locator) throws Exception
	{

		wait.until(ExpectedConditions.visibilityOf(findElement(locator)));
		boolean Actual = false;
		switch(popup)
		{
		case "Success":
			Actual = findElement(locator).isDisplayed();
			CommonStaticproperties.caseNumber =	getTextOfWebelement(byCaseNumber);
			Assert.assertTrue(findElement(bySuccess).isDisplayed(), " Success message not displayed");
			Assert.assertTrue(Actual);;
			//log.info(popup + " popup is displayed with case number : " + CommonStaticproperties.caseNumber);
			log.info(popup + " popup is displayed with case number : " + CommonStaticproperties.caseNumber);
			test.log(Status.INFO,popup + " popup is displayed with case number : " + CommonStaticproperties.caseNumber);
			break;
		case "Failed":
			Actual = findElement(locator).isDisplayed();
			log.info("Popup is displayed with text " + getTextOfWebelement(locator));
			test.log(Status.INFO,popup + " popup is displayed with  : " + getTextOfWebelement(locator));
			break;
		default:
			log.info(popup + ", is not maching with any cases ");
			throw error;
		}
	}

	public static void enterDataToField(String fields,String data) throws Exception
	{
		switch(fields)
		{
		case "First name":
			enterText(byFirstName, data);
			break;
		case "Last name":
			enterText(byLastName, data);
			break;
		case "Email address":
			enterText(byEmailAddress, data);
			break;
		case "Subject":
			enterText(bySubject, data);
			break;
		case "Question":
			enterText(byQuestion, data);
			break;

		default:
			log.info(fields + " : is not matching with any case");
			throw error;
		}
		log.info("Data to "+ fields + " is entered successfully");
		test.log(Status.INFO,  data +" : is entered successfully to : "+ fields + " field");
	}

	public static void selectAccount(String field) throws Exception
	{
		switch(field)
		{
		case "SoftClouds LLC":						
			//driver.findElement(byAccount_drp);
			selectVisibleText(findElement(byAccount_drp), field);
			break;
		default:
			log.info(field + " : is not matching with any case");
			throw error;
		}
	}

	public static void scenarioFailed(String scenario,Exception e) throws Exception
	{
		switch(scenario)
		{
		case "AQ_TC001":
			break;
		case "AQ_TC002":
			break;
		case "AQ_TC003":
			break;
		case "AQ_TC004":
			break;
		case "AQ_TC005":
			break;
		case "AQ_TC006":
			break;
		case "AQ_TC007":
			break;
		case "AQ_TC008":
			break;
		case "AQ_TC009":
			break;
		case "AQ_TC010":
			break;
		case "AQ_TC011":
			break;
		case "AQ_TC012":
			break;
		default:
			log.info(scenario + " is not maching with any case");
			throw error;
		}
		fail( scenario +"_Verification is un successful" + e );
		test.log(Status.WARNING, e);
		test.log(Status.FAIL, e);
		getScreenshot();
	}

}
