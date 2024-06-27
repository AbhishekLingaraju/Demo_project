package com.test.framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestNGListeners extends GlobalVariablesFramework implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) 
	{
		System.out.println("\n" + ">>>>>>>>>>>> :"+ result.getName() + " :*****Test execution started*****:" );
		test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Abhishek").assignDevice("Windows").assignCategory("Functional Test cases");
		//test = extent.createTest( ""+result.getName()+"").assignAuthor("Abhishek").assignDevice("Windows").assignCategory("Functional Test cases");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		System.out.println(">>>>>>>>>>> : "+"Test Result ::"+ result.getName() + " : is passed " + "\n");
		test.log(Status.PASS, result.getMethod().getMethodName() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		System.out.println( ">>>>>>>>>>> : "+"Test Result ::"+ result.getName() + " : is failed " + "\n");
		test.log(Status.FAIL, result.getMethod().getMethodName() + " is failed");
		test.log(Status.FAIL, result.getThrowable());
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		//To get current date
		SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		String ScreenshotDate = formate.format(date);

		String screenshotPath = System.getProperty("user.dir")+"/Report/Screenshot/ExecutionReport_"+ScreenshotDate+".jpeg";

		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, "Test case failure image");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
	}

	

	@Override
	public void onStart(ITestContext context)
	{
		System.out.println("\n" + "****************Tests started*****************" + "\n");
		extent = SeleniumWrapper.extentReport();
	}

	@Override
	public void onFinish(ITestContext context)
	{
		System.out.println("\n"+ "****************Tests completed*****************" + context.getClass() + "\n");
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
