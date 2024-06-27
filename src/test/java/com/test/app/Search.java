package com.test.app;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

@Listeners(com.test.framework.TestNGListeners.class)
public class Search extends Search_POM
{

	@BeforeClass
	public void testSignIn() throws Exception {
		try {
			openBrowser();
			launchURL();
			wait.until(ExpectedConditions.presenceOfElementLocated(byAskQuestion));
			randomNumer=generateRandomNumber();
		} catch (Exception e) {
			log.error("URL launch Un-successfull");
			e.printStackTrace();
			throw e;
		}
	}

	@Test(priority = 1, description = "Verify Ask question page GUI elements")
	public void AQ_TC001_Verify_Ask_a_Question_page_GUI_elements() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);

		verifyFields("Ask a Question", "First name", byFirstName);
		verifyFields("Ask a Question", "Last name", byLastName);
		verifyFields("Ask a Question", "Email address", byEmailAddress);
		verifyFields("Ask a Question", "Subject", bySubject);
		getScreenshot();
		scrolldown();
		verifyFields("Ask a Question", "Question", byQuestion);
		verifyFields("Ask a Question", "Account", byAccount_drp);
		verifyButton("Ask a Question", "Choose File", byAttach_document);
		verifyButton("Ask a Question", "Submit Your Question", bySubmit_btn);
		getScreenshot();
		clickUsingJavaScript(byHome_btn);}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC001", e);
		}
	}

	@Test(priority = 2, description = "Verify Create new case functionality")
	public void AQ_TC002_Create_new_case() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling"+ "_"+ randomNumer);
		scrolldown();
		enterDataToField("Question", "Data crawling steps from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			WaitForElementToBeClickable(byOK_btn);
			verifyPopuptext("Success", byPopupText);
			getScreenshot();
			clickUsingJavaScript(byOK_btn);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Unable to create case number");
			scenarioFailed("AQ_TC002", e);
			clickUsingJavaScript(byOK_btn);
			
		}}catch(Exception e)
		{
			scenarioFailed("AQ_TC002", e);
		}finally {
			pageRefresh();
			clickUsingJavaScript(byHome_btn);
		}

	}


	@Test(priority = 3, description = "Verify error message when First name field is empty")
	public void AQ_TC003_Verify_error_message_when_First_name_field_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyErrorText("Error", byFirstname_error, "Please enter first name");
		}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC003", e);
		}finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC003", e);
		}
	}

	@Test(priority = 4, description = "Verify error message when Last name field is empty")
	public void AQ_TC004_Verify_error_message_when_Last_name_field_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyErrorText("Error", byLastname_error, "Please enter last name");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Error message is not as expected");
			scenarioFailed("AQ_TC004", e);

		}finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC004", e);
		}
	}

	@Test(priority = 5, description = "Verify error message when Email field is empty")
	public void AQ_TC005_Verify_error_message_when_Email_field_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyErrorText("Error", byEmailID_error, "Please enter email ID");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Error message is not as expected");
			scenarioFailed("AQ_TC005", e);
		}finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC005", e);
		}

	}

	@Test(priority = 6, description = "Verify error message when Subject field is empty")
	public void AQ_TC006_Verify_error_message_when_Subject_field_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyFields("Ask a Question", "Subject", bySubject);
		}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC006", e);
		}finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC006", e);
		}
	}

	@Test(priority = 7, description = "Verify error message when Question field is empty")
	public void AQ_TC007_Verify_error_message_when_Question_field_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyErrorText("Error", byQuestion_error, "Please enter question");
		}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC007", e);
		}
		finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC007", e);
		}
	}

	@Test(priority = 8, description = "Verify error message when Account dropdown is empty")
	public void AQ_TC008_Verify_error_message_when_Account_dropdown_is_empty() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhishek");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//pro_number.jpg");
		clickUsingJavaScript(bySubmit_btn);
		try {
			verifyErrorText("Error", byAccount_error, "Please choose account");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Error message is not as expected");
			scenarioFailed("AQ_TC008", e);
		}
		finally {
			clickUsingJavaScript(byHome_btn);
		}}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC008", e);
		}

	}

	@Test(priority = 9, description = "Verify error message when Firstname contains special character or numbers")
	public void AQ_TC09_Verify_error_message_when_Firstname_is_specialCharacter() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "@12Abhi");
		enterDataToField("Last name", "Lingaraju");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		clickUsingJavaScript(bySubmit_btn);
		scrolldUp();
		verifyErrorText("Error", bySpecialChar_error, "Allows alphabets only");
		clickUsingJavaScript(byHome_btn);}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC009", e);
		}
	}
	@Test(priority = 10, description = "Verify error message when Last name contains special character or number")
	public void AQ_TC010_Verify_error_message_when_Lastname_is_specialCharacter() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhi");
		enterDataToField("Last name", "!@");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		clickUsingJavaScript(bySubmit_btn);
		scrolldUp();
		verifyErrorText("Error", bySpecialChar_error, "Allows alphabets only");
		clickUsingJavaScript(byHome_btn);}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC010", e);
		}
	}
	@Test(priority = 11, description = "Verify error message when EmailID is invalid")
	public void AQ_TC011_Verify_error_message_when_EmailID_is_invalid() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhi");
		enterDataToField("Last name", "L");
		enterDataToField("Email address", "abhishek");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		clickUsingJavaScript(bySubmit_btn);
		scrolldUp();
		verifyErrorText("Error", byEmailID_invalid, "Invalid email ID");
		clickUsingJavaScript(byHome_btn);}
		catch(Exception e)
		{
			scenarioFailed("AQ_TC011", e);
		}
	}

	@Test(priority = 12, description = "Verify error message when empty file is attached")
	public void AQ_TC012_Verify_error_message_when_empty_file_is_attached_for_case_creation() throws Exception 
	{
		try {
		clickUsingJavaScript(byAskQuestion);
		verifyScreen("Ask a Question", byAskQuestion);
		enterDataToField("First name", "Abhi");
		enterDataToField("Last name", "L");
		enterDataToField("Email address", "abhishekl@softclouds.com");
		enterDataToField("Subject", "Data crawling");
		scrolldown();
		enterDataToField("Question", "Data crawling from Slack");
		selectAccount("SoftClouds LLC");
		findElement(byAttach_document).sendKeys(System.getProperty("user.dir")+"//src//test//resources//TestData//KaptureCrawlingJIRA_empty.docx");
		clickUsingJavaScript(bySubmit_btn);
		try {
			WaitForElementToBeClickable(byOK_btn);
			verifyPopuptext("Failed", byPopupText);
			clickUsingJavaScript(byOK_btn);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Error message not specific");
			scenarioFailed("AQ_TC012", e);
			clickUsingJavaScript(byOK_btn);
		}}catch(Exception e)
		{
			scenarioFailed("AQ_TC012", e);
		}
	}

	@AfterClass
	public static void teardown()
	{
		try {
			driver.close();
		}catch(Exception e)
		{
			log.info("Unable to close browser due to :" + e);
		}
	}
	
}
