package com.computer.sample;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComputerDatabaseUpdateTest {
	
	private static WebDriver webDriver = null;
	
	@Before
	public void InitializeDriver()
	{
		webDriver = TestUtility.InitializeDriver();			
	}	
	
	@Test
	public void UpdateComputerName() throws InterruptedException
	{
		String computerName = "A1T1U1";
		ComputerDatabaseHelper.Create(webDriver, computerName);

		List<WebElement> webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(webDriver, computerName);
		WebElement link = webElements.get(0).findElement(By.tagName("a"));
		
		link.click();
		
		Thread.sleep(1000);
		
		WebElement inputName = SeleniumHelper.fetchElementByID(webDriver, "name");
		inputName.sendKeys("updated");
		
		WebElement saveButton = SeleniumHelper.fetchElementByXPath(webDriver, "//div[@class='actions']/input[@class='btn primary']");
		saveButton.click();		
		
		Thread.sleep(1000);
		
		String afterUpdateEntry = "A1T1U1"+"updated";		
		
		boolean res = ComputerDatabaseHelper.Search(webDriver,afterUpdateEntry);
		Assert.assertEquals("**search string "+ afterUpdateEntry +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, afterUpdateEntry);
		res = ComputerDatabaseHelper.Search(webDriver, afterUpdateEntry);
		Assert.assertEquals("**search string "+ afterUpdateEntry +" found!**",false, res);
	}	
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
