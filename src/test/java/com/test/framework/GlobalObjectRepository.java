package com.test.framework;

import org.openqa.selenium.By;

public class GlobalObjectRepository extends GlobalVariablesFramework
{
	public static By byPageReload = By.xpath("//button[text()='Reload']");
	public static By byLogin      = By.xpath("//a[text()=' Log in ']");
}
