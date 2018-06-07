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
		
		WebElement yourTable = driver.findElement(By.tagName("table"));
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
		
		Assert.assertEquals("search string "+ searchString +" not found",true, flag);
		if (flag == true)
		{	
			return rowColumns;
		}
		else
		{
			return null;
		}
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
	}
	

}
