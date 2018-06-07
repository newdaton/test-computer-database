package com.computer.sample;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {
	private static int waitTimeoutInSeconds = 15;
	
	public static WebElement fetchElementByID(WebDriver driver, String id){
		waitTillLoadByID(driver, id);
		return driver.findElement(By.id(id));
	}
	
	public static WebElement waitTillLoadByID(WebDriver driver, String id) {
    	// wait until the google page shows the result
    	WebElement myDynamicElement = (new WebDriverWait(driver, waitTimeoutInSeconds))
    	.until(ExpectedConditions.presenceOfElementLocated(By
    	.id(id)));
    	return myDynamicElement;
    }
	
	public static List<WebElement> fetchElementsByXPath(WebDriver driver, String xpath){
		waitTillLoadBy(driver, By.xpath(xpath));
		return driver.findElements(By.xpath(xpath));
	}
	
	public static WebElement fetchElementByXPath(WebDriver driver, String xpath){
		waitTillLoadBy(driver, By.xpath(xpath));
		return driver.findElement(By.xpath(xpath));
	}
	
	public static WebElement waitTillLoadBy(WebDriver driver, By by) {
    	WebElement myDynamicElement = (new WebDriverWait(driver, waitTimeoutInSeconds))
    	.until(ExpectedConditions.presenceOfElementLocated(by));
    	return myDynamicElement;
    }	
	
	public static void maximizeBrowserWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public static void wait(WebDriver driver, int milliseconds) throws InterruptedException
	{
		// ImplicitWait is preferred over Thread.sleep().
		// TODO: Thread.sleep seems more stable than ImplicitWait. Further investigation needed.
		Thread.sleep(milliseconds);		
		//driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
	}	
}
