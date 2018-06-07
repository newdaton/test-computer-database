package com.computer.sample;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ComputerDatabaseHelper {
	final static String sampleAppUrl = "http://computer-database.herokuapp.com/computers";	

	
	public static void Create(WebDriver driver, String computerName) throws InterruptedException
	{
		Create(driver, computerName, null, null, null);	
	}
	
	public static void Create(WebDriver driver, String computerName, String introducedDate, String discontinuedDate, String company) throws InterruptedException
	{
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement addButton = SeleniumHelper.fetchElementByID(driver, "add");
		addButton.click();
		Thread.sleep(500);
		
		if (computerName != null) {
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.sendKeys(computerName);
		}
		
		if (introducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.sendKeys(introducedDate);
		}
		
		if (discontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.sendKeys(discontinuedDate);
		}
		
		if (company != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(company);
		}
		
		WebElement createButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		createButton.click();
		Thread.sleep(1000);
		
		WebElement element = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='alert-message warning']/strong");
		Assert.assertEquals("Create not successful !", "Done!", element.getText());		
	}
	
	public static void CreateFail(WebDriver driver, String computerName, String introducedDate, String discontinuedDate, String company) throws InterruptedException
	{
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement addButton = SeleniumHelper.fetchElementByID(driver, "add");
		addButton.click();
		Thread.sleep(500);
		
		if (computerName != null) {
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.sendKeys(computerName);
		}
		
		if (introducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.sendKeys(introducedDate);
		}
		
		if (discontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.sendKeys(discontinuedDate);
		}
		
		if (company != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(company);
		}
		
		WebElement createButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		createButton.click();
		Thread.sleep(1000);
		
		WebElement element = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='clearfix error']");
		Assert.assertNotEquals("clearfix error element is null", null, element);		
	}
	
	public static void CreateCancel(WebDriver driver, String computerName, String introducedDate, String discontinuedDate, String company) throws InterruptedException
	{
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement addButton = SeleniumHelper.fetchElementByID(driver, "add");
		addButton.click();
		Thread.sleep(500);
		
		if (computerName != null) {
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.sendKeys(computerName);
		}
		
		if (introducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.sendKeys(introducedDate);
		}
		
		if (discontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.sendKeys(discontinuedDate);
		}
		
		if (company != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(company);
		}
		
		WebElement cancelButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/a[@class='btn']");
		cancelButton.click();
		Thread.sleep(200);		
	}


	
	public static boolean Search(WebDriver webDriver, String searchString) throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement searchbox = SeleniumHelper.fetchElementByID(driver, "searchbox");
		searchbox.sendKeys(searchString);
		
		WebElement searchsubmit = SeleniumHelper.fetchElementByID(driver, "searchsubmit");
		searchsubmit.click();
		
		Thread.sleep(500);
		WebElement yourTable = null;
		try {
			yourTable = driver.findElement(By.tagName("table"));
		}catch (Exception e) {
		    System.out.println(e.toString());
		}
		if (yourTable == null)
		{
			System.out.println("Table is empty");
			return false;
		}
		
		List<WebElement> tableRows = yourTable.findElements(By.tagName("tr"));
		
		
		boolean flag = false;
		
		for(int i=1; i<tableRows.size(); i++){
		    WebElement row  = tableRows.get(i);
		    List<WebElement> rowColumns = row.findElements(By.tagName("td"));
		    System.out.println(rowColumns.get(0).getText());
		    if (searchString.equals(rowColumns.get(0).getText()) )
		    {
		    	flag = true;
		    	break;		    	
		    }		    
		}
		
		//Assert.assertEquals("search string "+ searchString +" not found",true, flag);
		return flag;		
	}
	
	public static List<WebElement> SearchAndReturnWebElements(WebDriver driver, String searchString) throws InterruptedException
	{	
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		//String searchString = "ACE";
		WebElement searchbox = SeleniumHelper.fetchElementByID(driver, "searchbox");
		searchbox.sendKeys(searchString);
		
		WebElement searchsubmit = SeleniumHelper.fetchElementByID(driver, "searchsubmit");
		searchsubmit.click();
		
		Thread.sleep(500);
		
		WebElement yourTable = null;
		try {
			yourTable = driver.findElement(By.tagName("table"));
		}catch (Exception e) {
		    System.out.println(e.toString());
		}
		if (yourTable == null)
		{
			System.out.println("Table is empty");
			return null;
		}
		
		List<WebElement> tableRows = yourTable.findElements(By.tagName("tr"));		
		
		boolean flag = false;
		int i;
		List<WebElement> rowColumns = null;
		for(i=1; i<tableRows.size(); i++){
		    WebElement row  = tableRows.get(i);
		    rowColumns = row.findElements(By.tagName("td"));
		    //System.out.println(rowColumns.get(0));		    
		    //System.out.println(rowColumns.get(0).getText());
		    if (searchString.equals(rowColumns.get(0).getText()) )
		    {
		    	flag = true;
		    	break;		    	
		    }		    
		}
		
		//Assert.assertEquals("search string "+ searchString +" not found",true, flag);
		if (flag == true)
		{	
			return rowColumns;
		}
		else
		{
			System.out.println("search string "+ searchString +" not found");
			return null;
		}
	}
	
	public static boolean Read(WebDriver driver, String computerName, String introducedDateFormatted, String discontinuedDateFormatted, String company) throws InterruptedException
	{
		List<WebElement> webElements = null;
		try {
			webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(driver, computerName);			
		}catch (Exception e) {
		    System.out.println(e.toString());
		}		
		if (webElements == null)
		{
			System.out.println("Entry with computer name not found!*");
			return false;
		}
		
		WebElement element = webElements.get(0).findElement(By.tagName("a"));
		Assert.assertEquals(computerName, element.getText());
		
		if (introducedDateFormatted != null)
		{
			element = webElements.get(1);
			Assert.assertEquals(introducedDateFormatted, element.getText());
		}
		
		if (discontinuedDateFormatted != null)
		{			
			element = webElements.get(2);
			Assert.assertEquals(discontinuedDateFormatted, element.getText());
		}
		
		if (company != null)
		{			
			element = webElements.get(3);
			Assert.assertEquals(company, element.getText());
		}
		
		return true;
	}
	
	public static void Update(WebDriver driver, String computerName, String updateComputerName, String updateIntroducedDate, String updateDiscontinuedDate, String updateCompany) throws InterruptedException
	{		
		List<WebElement> webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(driver, computerName);
		
		WebElement link = webElements.get(0).findElement(By.tagName("a"));		
		link.click();		
		Thread.sleep(500);
		
		if (updateComputerName != null)
		{
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.clear();
			inputName.sendKeys(updateComputerName);
		}
		
		if (updateIntroducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.clear();			
			introduced.sendKeys(updateIntroducedDate);
		}
		
		if (updateDiscontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.clear();
			discontinued.sendKeys(updateDiscontinuedDate);
		}
		
		if (updateCompany != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(updateCompany);
		}		
		
		WebElement saveButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		saveButton.click();
		Thread.sleep(200);
	}
	
	public static void UpdateCancel(WebDriver driver, String computerName, String updateComputerName, String updateIntroducedDate, String updateDiscontinuedDate, String updateCompany) throws InterruptedException
	{		
		List<WebElement> webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(driver, computerName);
		
		WebElement link = webElements.get(0).findElement(By.tagName("a"));		
		link.click();		
		Thread.sleep(500);
		
		if (updateComputerName != null)
		{
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.clear();
			inputName.sendKeys(updateComputerName);
		}
		
		if (updateIntroducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.clear();			
			introduced.sendKeys(updateIntroducedDate);
		}
		
		if (updateDiscontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.clear();
			discontinued.sendKeys(updateDiscontinuedDate);
		}
		
		if (updateCompany != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(updateCompany);
		}		
		
		WebElement cancelButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/a[@class='btn']");
		cancelButton.click();
		Thread.sleep(200);
	}

	public static void UpdateFail(WebDriver driver, String computerName, String updateComputerName, String updateIntroducedDate, String updateDiscontinuedDate, String updateCompany) throws InterruptedException
	{		
		List<WebElement> webElements = ComputerDatabaseHelper.SearchAndReturnWebElements(driver, computerName);
		
		WebElement link = webElements.get(0).findElement(By.tagName("a"));		
		link.click();		
		Thread.sleep(500);
		
		if (updateComputerName != null)
		{
			WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
			inputName.clear();
			inputName.sendKeys(updateComputerName);
		}
		
		if (updateIntroducedDate != null) {		
			WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
			introduced.clear();			
			introduced.sendKeys(updateIntroducedDate);
		}
		
		if (updateDiscontinuedDate != null) {			
			WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
			discontinued.clear();
			discontinued.sendKeys(updateDiscontinuedDate);
		}
		
		if (updateCompany != null) {			
			Select select = new Select(driver.findElement(By.id("company")));
			select.selectByVisibleText(updateCompany);
		}		
		
		WebElement saveButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		saveButton.click();
		Thread.sleep(100);
		
		WebElement element = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='clearfix error']");
		Assert.assertNotEquals("clearfix error element is null", null, element);		
	}
	
	
	public static void Delete(WebDriver driver, String computerName) throws InterruptedException
	{
		List<WebElement> webElements = SearchAndReturnWebElements(driver, computerName);
		
		WebElement link = webElements.get(0).findElement(By.tagName("a"));		
		link.click();
		
		Thread.sleep(1000);
		
		WebElement deleteButton = SeleniumHelper.fetchElementByXPath(driver, "//form[@class='topRight']/input[@class='btn danger']");
		deleteButton.click();
		Thread.sleep(1000);
		WebElement element = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='alert-message warning']/strong");
		Assert.assertEquals("Create not successful !", "Done!", element.getText());
	}
	

}
