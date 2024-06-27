package com.test.framework;

public class ApplicationWrapper extends SeleniumWrapper 
{

	public static void launchURL() throws Exception 
	{
		enterURL(appUrl);
		hardWait(1);
		try {findElement(byPageReload).isDisplayed();
		clickUsingJavaScript(byPageReload);}
		catch(Exception e) {log.info("Application launched successfully");}
	}
	
}
