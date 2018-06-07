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
		String updateComputerName = "A!@#+updated";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.Update(webDriver, computerName, updateComputerName, null, null, null);
		
		boolean res = ComputerDatabaseHelper.Search(webDriver, updateComputerName);
		Assert.assertEquals("**search string "+ updateComputerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, updateComputerName);
		res = ComputerDatabaseHelper.Search(webDriver, updateComputerName);
		Assert.assertEquals("**search string "+ updateComputerName +" found!**",false, res);
	}	

	@Test
	public void UpdateAllFields() throws InterruptedException
	{
		String computerName = "A1T1U2";
		String company = "IBM";		
		
		String updateComputerName = "A1T1U2 version 2.0";
		String updateCompany = "Thinking Machines";		
		String updateIntroducedDate = "1934-03-16";
		String updateDiscontinuedDate  = "1997-05-28";
		
		String updateIntroducedDateFormatted = "16 Mar 1934";
		String updateDiscontinuedDateFormatted  = "28 May 1997";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName, null, null, company);

		ComputerDatabaseHelper.Update(webDriver, computerName, updateComputerName, updateIntroducedDate, updateDiscontinuedDate, updateCompany);
		
		boolean res = ComputerDatabaseHelper.Read(webDriver, updateComputerName, updateIntroducedDateFormatted, updateDiscontinuedDateFormatted, updateCompany);
		Assert.assertEquals("**search string "+ updateComputerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, updateComputerName);
		res = ComputerDatabaseHelper.Search(webDriver, updateComputerName);
		Assert.assertEquals("**search string "+ updateComputerName +" found!**",false, res);
	}	

	@Test
	public void UpdateComputernameAndCancel() throws InterruptedException
	{
		String computerName = "A1T1U3";
		String updateComputerName = "A1T1U3!@#+updated";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.UpdateCancel(webDriver, computerName, updateComputerName, null, null, null);
		
		boolean res = ComputerDatabaseHelper.Search(webDriver, updateComputerName);
		Assert.assertNotEquals("**search string "+ updateComputerName +" found!**",true, res);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void UpdateCancel() throws InterruptedException
	{
		String computerName = "A1T1U5";
		//String updateComputerName = "A1T1U3!@#+updated";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.UpdateCancel(webDriver, computerName, null, null, null, null);
		
		boolean res = ComputerDatabaseHelper.Read(webDriver, computerName, null, null, null);
		Assert.assertEquals("**search string "+ computerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void UpdateIntroduceddate() throws InterruptedException
	{
		String computerName = "A1T1U4";
		String introducedDate = "1987-02-13";
		String introducedDateFormatted = "13 Feb 1987";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.Update(webDriver, computerName, null, introducedDate, null, null);
		
		boolean res = ComputerDatabaseHelper.Read(webDriver, computerName, introducedDateFormatted, null, null);
		Assert.assertEquals("**search string "+ computerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void UpdateInvalidIntroduceddate() throws InterruptedException
	{
		String computerName = "A1T1U7";
		String introducedDate = "1987-22-13";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.UpdateFail(webDriver, computerName, null, introducedDate, null, null);
		
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" not found!**",true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}


	@Test
	public void UpdateDiscontinueddate() throws InterruptedException
	{
		String computerName = "A1T1U6";
		String discontinuedDate = "2017-04-30";
		String discontinuedDateFormatted = "30 Apr 2017";
		
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));		
		ComputerDatabaseHelper.Create(webDriver, computerName);

		ComputerDatabaseHelper.Update(webDriver, computerName, null, null, discontinuedDate, null);
		
		boolean res = ComputerDatabaseHelper.Read(webDriver, computerName, null, discontinuedDateFormatted, null);
		Assert.assertEquals("**search string "+ computerName +" not found!**", true, res);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**", false, res);
	}
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
