package com.computer.sample;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUtility {
	
	//final static String CHROME_DRIVER_PATH = "C:\\Users\\Kartheek\\Downloads\\chromedriver_win32\\chromedriver.exe";	
	final static String CHROME_DRIVER_PATH = "..\\drivers\\chromedriver.exe";

	public static WebDriver InitializeDriver()
	{
		File chromeDriverFile = new File(CHROME_DRIVER_PATH);
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
		return new ChromeDriver();			
	}

}
