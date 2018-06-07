package com.computer.sample;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompDBHelper {

	public static boolean HelperSearchComputer(WebDriver webDriver, String searchString) throws InterruptedException
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

}
