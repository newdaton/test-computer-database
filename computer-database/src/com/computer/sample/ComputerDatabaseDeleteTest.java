package com.computer.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ComputerDatabaseDeleteTest {
	
	private static WebDriver webDriver = null;
	
	@Before
	public void InitializeDriver()
	{
		webDriver = TestUtility.InitializeDriver();			
	}	
	
	@Test
	public void DeleteBasic() throws InterruptedException
	{
		String computerName = "A1T1D1";
		
		ComputerDatabaseHelper.Create(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver,computerName);
		Assert.assertEquals("**search string "+ computerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
