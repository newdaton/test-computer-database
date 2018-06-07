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
		/*
		String updateIntroducedDateFormatted = "A1T1U1";
		String updateDiscontinuedDateFormatted  = "A1T1U1";
		String updateCompany = "A1T1U1";
		*/
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

	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
