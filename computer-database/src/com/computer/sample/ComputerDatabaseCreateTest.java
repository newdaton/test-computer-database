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
		String computerName = "A1T1C1";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithNoComputerName() throws InterruptedException
	{
		String computerName = "";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, null, null, null);
		Assert.assertNotEquals("*After create computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithComputerName_SpecialCharacters() throws InterruptedException
	{
		String computerName = "!@#$";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}

	
	@Test
	public void CreateWithAllInputFields() throws InterruptedException
	{
		String computerName = "A1T1C1PC1";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, "1993-06-12", "1997-09-30", "Tandy Corporation");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateCancel() throws InterruptedException
	{
		String computerName = "A1T1C1PCancel1";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateCancel(webDriver, computerName, "1993-06-12", "1997-09-30", "Tandy Corporation");
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithAllInputFieldsExceptComputername() throws InterruptedException
	{
		String computerName = "";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, "1993-03-22", "2007-05-03", "Thinking Machines");
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));		
	}
	
	/*
	@Test
	public void CreateWithDuplicateComputerName() throws InterruptedException
	{
		String computerName = "A1T1CD1";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Create(webDriver, computerName);
		WebElement element = SeleniumHelper.fetchElementByXPath(webDriver, "//div[@class='alert-message warning']/strong");
		Assert.assertNotEquals("Duplicates for computername are not allowed. But, duplicate is created !", "Done!", element.getText());
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	//Should be executed when CreateWithDuplicateComputerName test case fails 
	public void CleanupForDuplicateComputerName() throws InterruptedException
	{
		String computerName = "A1T1CD1";
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);		
	}
	*/
	
	@Test
	public void CreateWithComputernameAndCompany() throws InterruptedException
	{
		String computerName = "A1T1C1CC01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, null, "Canon");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithComputernameAndIntroduceddate() throws InterruptedException
	{
		String computerName = "A1T1C1CI01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, "1983-07-04", null, null);
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithInvalidIntroduceddate_Text() throws InterruptedException
	{
		String computerName = "A1T1C0I01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, "this is some text", null, null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithInvalidIntroduceddate_Month() throws InterruptedException
	{
		String computerName = "A1T1C0I02";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, "2003-13-20", null, null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithIntroduceddate_Leapyear_EdgeCase() throws InterruptedException
	{
		String computerName = "A1T1C1CI03";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, "2004-02-29", null, null);
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}

	@Test
	public void CreateWithIntroduceddate_NonLeapyear_EdgeCase() throws InterruptedException
	{
		String computerName = "A1T1C1CI04";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, "2003-02-29", null, null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithComputernameAndDiscontinueddate() throws InterruptedException
	{
		String computerName = "A1T1C1CD01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, "1999-12-31", null);
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithInvalidDiscontinueddate_Text() throws InterruptedException
	{
		String computerName = "A1T1C0D01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, null, "random text", null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithInvalidDiscontinueddate_Day() throws InterruptedException
	{
		String computerName = "A1T1C0D02";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, null, "2003-03-32", null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithDiscontinueddate_Leapyear_EdgeCase() throws InterruptedException
	{
		String computerName = "A1T1C1CD03";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, "2008-02-29", null);
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}

	@Test
	public void CreateWithDiscontinueddate_NonLeapyear_EdgeCase() throws InterruptedException
	{
		String computerName = "A1T1C1CD04";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.CreateFail(webDriver, computerName, null, "2009-02-29", null);
		Assert.assertNotEquals("*After create, computer name exists in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
	}
	
	@Test
	public void CreateWithComputernameIntroduceddateAndDiscontinueddate() throws InterruptedException
	{
		String computerName = "A1T1C1CID01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, "1979-01-06", "1986-07-14", null);
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithComputernameIntroduceddateAndCompany() throws InterruptedException
	{
		String computerName = "A1T1C1CIC01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, "1979-01-06", null, "Samsung Electronics");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void CreateWithComputernameDiscontinueddateAndCompany() throws InterruptedException
	{
		String computerName = "A1T1C1CDC01";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, "2009-11-01", "Apple Inc.");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, ComputerDatabaseHelper.Search(webDriver,computerName));
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}

	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
