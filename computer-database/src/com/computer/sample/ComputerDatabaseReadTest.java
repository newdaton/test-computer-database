package com.computer.sample;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComputerDatabaseReadTest {
	
	private static WebDriver webDriver = null;
	
	@Before
	public void InitializeDriver()
	{
		webDriver = TestUtility.InitializeDriver();			
	}	
	
	@Test
	public void ValidateReadWithAllParameters() throws InterruptedException
	{
		String cName = "A1T1R1PC1";
		String introducedDate = "1998-06-01";
		String discontinuedDate = "2007-09-30";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,cName));
		ComputerDatabaseHelper.Create(webDriver, cName, introducedDate, discontinuedDate, null);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,cName));
		
		List<WebElement> webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(webDriver, cName);
		
		WebElement element = webElements.get(0).findElement(By.tagName("a"));
		Assert.assertEquals(cName, element.getText());
		
		element = webElements.get(1);
		Assert.assertEquals("01 Jun 1998", element.getText());
		
		element = webElements.get(2);
		Assert.assertEquals("30 Sep 2007", element.getText());
		
		ComputerDatabaseHelper.Delete(webDriver, cName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, cName);
		Assert.assertEquals("**search string "+ cName +" found!**",false, res);
	}	
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
