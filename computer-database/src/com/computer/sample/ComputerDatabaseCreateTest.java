package com.computer.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ComputerDatabaseCreateTest {
	
	private static WebDriver webDriver = null;
	
	@Before
	public void InitializeDriver()
	{
		webDriver = TestUtility.InitializeDriver();			
	}	
	
	@Test
	public void VerifyApplicationPageTitle() throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
			
		webDriver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		String actualTitle = webDriver.getTitle();
		String expectedTitle = "Computers database";
		Assert.assertEquals("title mismatch", expectedTitle, actualTitle);
	}	
	
	@Test
	public void CreateWithOnlyComputerName() throws InterruptedException
	{
		String cName = "A1T1C1";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,cName));
		ComputerDatabaseHelper.Create(webDriver, cName);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,cName));
		
		ComputerDatabaseHelper.Delete(webDriver, cName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, cName);
		Assert.assertEquals("**search string "+ cName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithAllInputFields() throws InterruptedException
	{
		String cName = "A1T1C1PC1";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,cName));
		ComputerDatabaseHelper.Create(webDriver, cName, "1993-06-12", "1997-09-30", "Tandy Corporation");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,cName));
		
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
