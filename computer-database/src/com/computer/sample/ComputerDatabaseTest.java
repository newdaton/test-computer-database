package com.computer.sample;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class returnValues{
	WebDriver driver;
	//WebElement element;
	List<WebElement> elements;
/*
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	*/
}

public class ComputerDatabaseTest {
	
final static String CHROME_DRIVER_PATH = "C:\\Users\\Kartheek\\Downloads\\chromedriver_win32\\chromedriver.exe";	
	
	private static WebDriver webDriver = null;
	
	@Before
	public void InitializeDriver()
	{
		File chromeDriverFile = new File(CHROME_DRIVER_PATH);
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
		webDriver = new ChromeDriver();			
	}
	
	
	@Test
	public void Computerdatabase_VerifyTitle_Basic_positive() throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Computers database";
		Assert.assertEquals("title mismatch",expectedTitle,actualTitle);
		
		
	}
	
	@Test
	public void Computerdatabase_VerifyTitle_Basic_negative() throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		String actualTitle = driver.getTitle() + "a";
		String expectedTitle = "Computers database";
		Assert.assertNotEquals("title mismatch",expectedTitle,actualTitle);
		
		
	}
	
	
	public void HelperCreateComputer(String computerName) throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement addButton = SeleniumHelper.fetchElementByID(driver, "add");
		addButton.click();
		Thread.sleep(500);
		
		//String computerName = "A1T1C1";
		WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
		inputName.sendKeys(computerName);
		
		WebElement createButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		createButton.click();	
				
	}
	
	public void HelperCreateComputerComplete(String computerName, String introducedDate, String discontinuedDate, String company) throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
		driver.get(sampleAppUrl);
		Thread.sleep(1000);
		
		WebElement addButton = SeleniumHelper.fetchElementByID(driver, "add");
		addButton.click();
		Thread.sleep(500);
		
		//String computerName = "A1T1C1";
		WebElement inputName = SeleniumHelper.fetchElementByID(driver, "name");
		inputName.sendKeys(computerName);
		
		WebElement introduced = SeleniumHelper.fetchElementByID(driver, "introduced");
		introduced.sendKeys(introducedDate);
		
		WebElement discontinued = SeleniumHelper.fetchElementByID(driver, "discontinued");
		discontinued.sendKeys(discontinuedDate);
		
		
		Select select = new Select(driver.findElement(By.id("company")));
		select.selectByVisibleText(company);
		
		
		WebElement createButton = SeleniumHelper.fetchElementByXPath(driver, "//div[@class='actions']/input[@class='btn primary']");
		createButton.click();	
				
	}

	
	@Test
	public void CreateComputerBasicPositive() throws InterruptedException
	{
		String cName = "A1T1C1";
		Assert.assertEquals("*Before create computer name already exists!*",false, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperCreateComputer(cName);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperDeleteComputer(cName);
	}
	
	@Test
	public void CreateComputer_Positive_AllParameters() throws InterruptedException
	{
		String cName = "A1T1C1PC1";
		Assert.assertEquals("*Before create operation, computer name already exists!*",false, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperCreateComputerComplete(cName, "1993-06-12", "1997-09-30", "Tandy Corporation");
		Assert.assertEquals("*After create, computer name doesnt exist in table!*",true, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperDeleteComputer(cName);
	}
	
	@Test
	public void ReadComputer_Positive_Complete() throws InterruptedException
	{
		String cName = "A1T1R1PC1";
		String introducedDate = "1998-06-01";
		String discontinuedDate = "2007-09-30";
		Assert.assertEquals("*Before create computer name already exists!*",false, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperCreateComputerComplete(cName, introducedDate, discontinuedDate, null);
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, CompDBHelper.HelperSearchComputer(webDriver,cName));
		
		returnValues r = new returnValues();		
		r = HelperSearchComputerAndReturnWebElement(cName);
		
		WebElement element = r.elements.get(0).findElement(By.tagName("a"));
		Assert.assertEquals(cName, element.getText());
		
		element = r.elements.get(1);
		Assert.assertEquals("01 Jun 1998", element.getText());
		
		element = r.elements.get(2);
		Assert.assertEquals("30 Sep 2007", element.getText());
		
		HelperDeleteComputer(cName);
	}
	
	
	public returnValues HelperSearchComputerAndReturnWebElement(String searchString) throws InterruptedException
	{
		String sampleAppUrl = "http://computer-database.herokuapp.com/computers";
		WebDriver driver = webDriver;	
			
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
		returnValues r = new returnValues();
		if (flag == true)
		{
			r.driver = driver;
			//r.element = rowColumns.get(0);
			r.elements = rowColumns;
			return r;
		}
		else
			return null;
	}
	
	@Test
	public void UpdateComputerBasicPositive() throws InterruptedException
	{
		String updateEntry = "A1T1U1";
		HelperCreateComputer(updateEntry);	
		
		returnValues r = new returnValues();		
		r = HelperSearchComputerAndReturnWebElement(updateEntry);
		
		WebElement link = r.elements.get(0).findElement(By.tagName("a"));
		
		link.click();
		
		Thread.sleep(1000);
		
		/*
		*/
		WebElement inputName = SeleniumHelper.fetchElementByID(r.driver, "name");
		inputName.sendKeys("updated");
		
		WebElement saveButton = SeleniumHelper.fetchElementByXPath(r.driver, "//div[@class='actions']/input[@class='btn primary']");
		saveButton.click();		
		
		Thread.sleep(1000);
		
		String afterUpdateEntry = "A1T1U1"+"updated";
		
		boolean res = CompDBHelper.HelperSearchComputer(webDriver,afterUpdateEntry);
		Assert.assertEquals("**search string "+ afterUpdateEntry +" not found!**",true, res);
		
		HelperDeleteComputer(afterUpdateEntry);
	}
	
	public void HelperDeleteComputer(String delEntry) throws InterruptedException
	{
		//String delEntry = "A1T1D1";
		
		returnValues r = new returnValues();		
		r = HelperSearchComputerAndReturnWebElement(delEntry);
		
		WebElement link = r.elements.get(0).findElement(By.tagName("a"));		
		link.click();
		
		Thread.sleep(1000);
		
		WebElement deleteButton = SeleniumHelper.fetchElementByXPath(r.driver, "//form[@class='topRight']/input[@class='btn danger']");
		deleteButton.click();
		Thread.sleep(1000);
		
		boolean res = CompDBHelper.HelperSearchComputer(webDriver,delEntry);
		Assert.assertEquals("**search string "+ delEntry +" found!**",false, res);		
	}
	
	@Test
	public void DeleteComputerBasicPositive() throws InterruptedException
	{
		String delEntry = "A1T1D1";
		HelperCreateComputer(delEntry);	
		
		returnValues r = new returnValues();		
		r = HelperSearchComputerAndReturnWebElement(delEntry);
		
		//WebElement link = r.element.findElement(By.tagName("a"));
		WebElement link = r.elements.get(0).findElement(By.tagName("a"));
		//System.out.println(link.getText());
		
		link.click();
		
		Thread.sleep(1000);
		
		WebElement deleteButton = SeleniumHelper.fetchElementByXPath(r.driver, "//form[@class='topRight']/input[@class='btn danger']");
		deleteButton.click();
		Thread.sleep(1000);
		
		boolean res = CompDBHelper.HelperSearchComputer(webDriver,delEntry);
		Assert.assertEquals("**search string "+ delEntry +" found!**",false, res);
		
	}
	
	@Test
	public void SearchComputerBasicPositive() throws InterruptedException
	{	
		String cName = "A1T1S1P";		
		Assert.assertEquals("*Before create computer name already exists!*",false, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperCreateComputer(cName);
		
		boolean res = CompDBHelper.HelperSearchComputer(webDriver,cName);
		Assert.assertEquals("search string "+ cName +" not found",true, res);
		
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, CompDBHelper.HelperSearchComputer(webDriver,cName));
		HelperDeleteComputer(cName);		
	}
	
/*	@Test
	public void SearchComputer_Positive_NotCaseSensitive() throws InterruptedException
	{	
		String cName = "A1T1S1P";		
		Assert.assertEquals("*Before create computer name already exists!*",false, HelperSearchComputer(cName));
		HelperCreateComputer(cName);
		
		boolean res = HelperSearchComputer(cName.toLowerCase());
		Assert.assertEquals("search string "+ cName.toLowerCase() +" not found",true, res);
		
		Assert.assertEquals("*After create computer name doesnt exist in table!*",true, HelperSearchComputer(cName));
		HelperDeleteComputer(cName);		
	}
*/	
	@Test
	public void SearchComputerBasicNegative() throws InterruptedException
	{		
		String randomName = "afailejijlfeiofe232323";
		boolean res = CompDBHelper.HelperSearchComputer(webDriver,randomName);
		Assert.assertEquals("*search string "+ randomName +" found! *",false, res);
	}
	
	@After
	public void Exit()
	{
		webDriver.quit();
	}

}
