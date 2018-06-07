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
	public void ReadValidateAllFields() throws InterruptedException
	{
		String computerName = "A1T1R1PC1";
		String introducedDate = "1998-06-01";
		String discontinuedDate = "2007-09-30";
		String company = "RCA";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, introducedDate, discontinuedDate, company);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, "01 Jun 1998", "30 Sep 2007", company);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateComputername_Specialcharacters() throws InterruptedException
	{
		String computerName = "!@&afda32323$~";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, null, null);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, null, null, null);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateComputernameAndIntroduceddate() throws InterruptedException
	{
		String computerName = "A1T1R1PC2";
		String introducedDate = "2001-11-19";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, introducedDate, null, null);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, "19 Nov 2001", null, null);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateComputernameAndDiscontinueddate() throws InterruptedException
	{
		String computerName = "A1T1R1PC3";
		String discontinuedDate = "2016-01-01";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, discontinuedDate, null);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, null, "01 Jan 2016", null);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateAllFieldsExceptCompany() throws InterruptedException
	{
		String computerName = "A1T1R1PC4";
		String introducedDate = "1997-09-30";
		String discontinuedDate = "2008-06-01";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, introducedDate, discontinuedDate, null);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, "30 Sep 1997", "01 Jun 2008", null);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateAllFieldsExceptDiscontinueddate() throws InterruptedException
	{
		String computerName = "A1T1R1PC5";
		String introducedDate = "1947-12-01";
		String company = "IBM";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, introducedDate, null, company);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, "01 Dec 1947", null, company);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateAllFieldsExceptIntroduceddate() throws InterruptedException
	{
		String computerName = "A1T1R1PC6";
		String discontinuedDate = "2018-03-30";
		String company = "Evans & Sutherland";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, discontinuedDate, company);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, null, "30 Mar 2018", company);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadValidateComputernameAndCompany() throws InterruptedException
	{
		String computerName = "A1T1R1PC7";
		String company = "E.S.R. Inc.";
		Assert.assertEquals("*Before create computer name already exists!*",false, ComputerDatabaseHelper.Search(webDriver,computerName));
		ComputerDatabaseHelper.Create(webDriver, computerName, null, null, company);
		
		ComputerDatabaseHelper.Read(webDriver, computerName, null, null, company);
		
		ComputerDatabaseHelper.Delete(webDriver, computerName);
		boolean res = ComputerDatabaseHelper.Search(webDriver, computerName);
		Assert.assertEquals("**search string "+ computerName +" found!**",false, res);
	}
	
	@Test
	public void ReadNonExistingComputername() throws InterruptedException
	{
		String computerName = "A1T1R1PC2Nonexisting";
		String company = "RCA";
		
		boolean result = ComputerDatabaseHelper.Read(webDriver, computerName, "01 Jun 1998", "30 Sep 2007", company);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void ReadEntryWithoutAnyComputername() throws InterruptedException
	{
		String computerName = "";		
		boolean result = ComputerDatabaseHelper.Read(webDriver, computerName, null, null, null);
		Assert.assertEquals(false, result);
	}
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}
}
