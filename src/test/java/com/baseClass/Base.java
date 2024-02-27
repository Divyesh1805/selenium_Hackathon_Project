package com.baseClass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
	public static WebDriver driver;
	public static String url = "https://www.zigwheels.com/";
	public static Properties prop;
	
	// Method to set up the WebDriver instance based on the browser specified in the configuration file
	
	public WebDriver driverSetup()
	{
		prop=new Properties();
		
		try 
		{
			// Loading the configuration file
			prop.load(new FileInputStream("src/test/resources/config/config.properties"));
		}	
		 catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("browserName"));
		
       // Checking which browser is specified in the configuration file and creating the corresponding WebDriver instance
		if(prop.getProperty("browserName").matches("edge")){		
			driver = new EdgeDriver();
		}else if(prop.getProperty("browserName").matches("chrome")){	
			driver = new ChromeDriver() ;
		}
		
		
		// Maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Navigating to the URL specified in the static variable
		driver.get(url);
		
		return driver;
		
	}
	
	// Method to quit the browser
	
	public void quitBrowser()
	{
		driver.quit();
	}


}
