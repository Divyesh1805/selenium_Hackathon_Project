package com.upcomingBikes;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.baseClass.Wait;
import com.utility.ScreenShot;
import com.utility.ExcelData;

public class UpcomingBikes {
	WebDriver driver;
	public String filePath = null;
	ScreenShot ss = new ScreenShot();
	public static ExcelData data;
	
	String	file = System.getProperty("user.dir")+"\\Book.xlsx";
	//Constructor to initialize WebElements
	
	public UpcomingBikes(WebDriver driver) {
		PageFactory.initElements(driver, this);  //to initialize the web elements present in the UpcomingBikes class.
		this.driver = driver;
	}
	
	// WebElements for Upcoming Bikes Page
	
	@FindBy(xpath= "//a[contains(text(),'New Bikes')]")
	WebElement newBikesMenu;
	
	@FindBy(xpath = "//span[contains(text(),'Upcoming Bikes')]")
	WebElement upcomingBikes;
	
	@FindBy(id = "makeId")
	WebElement manufacturer;
	
	@FindBy(xpath = "//*[@id=\"modelList\"]/li//span[contains(text(),'View More Bikes')]")
	WebElement viewMore;
	
	@FindBy(xpath = "//*[@id='carModels']/ul")
	WebElement hondaBikeModels;
	
	int count = 0;
	
	// Hover over 'New Bikes' menu to view 'Upcoming Bikes'
	
	public void newBikesMenu() throws Exception {
		
		Actions action = new Actions(driver);
		action.moveToElement(newBikesMenu).perform(); // Move mouse to 'New Bikes' menu
		Thread.sleep(3000);  // To capture the screenShot
		filePath = System.getProperty("user.dir") + "/ScreenShots/UpcomingBikes/NewBikesMenu.png";
		ss.captureTestScreenshot(driver, filePath);
	}
	
	// Select 'Upcoming Bikes'
	public void selectUpcomingBike() throws Exception {
		upcomingBikes.click(); // Click 'Upcoming Bikes'
	}
	
	// Select 'Honda' as manufacturer
	public void selectManufacturer() throws Exception {
		Wait wait = new Wait();
		wait.waitExplicit(30,manufacturer,driver);
		Select select = new Select(manufacturer);
		select.selectByVisibleText("Honda");  // Select 'Honda'
		
	}
     
	// Click 'View More Bikes'
	public void viewMoreBikes() throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewMore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/ManufacturerDropdown.png";
		ss.captureTestScreenshot(driver, filePath);
	}
	
	// Get bike models, prices, and expected launch date
	public void bikeModels() throws Exception {
//		data= new ExcelData();
		// Get text of all bike models available on the page
		String bikeModels = hondaBikeModels.getText();
		
		// Split the text by new line character and store it in an ArrayList
		ArrayList<String> bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements, bikeModels.split("\n"));
        
		// Initialize ArrayLists to store names, launch dates and prices of bikes
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		ArrayList<String> priceList = new ArrayList<String>();
		String[] arr = null;
		ExcelData.setCellData(file, "UpComingBikes", 0, 1, "Bike name");
		ExcelData.setCellDataAgain(file, "UpComingBikes", 0, 5, "Price");
		ExcelData.setCellDataAgain(file, "UpComingBikes", 0, 8, "Launch Date");
		// Iterate through the list of bike models and extract the required information
		for (int i = 0; i < bikeModelsElements.size(); i++) {
			String s = bikeModelsElements.get(i);
			if (s.contains("Honda")) {
				nameList.add(s);
			}
			if (s.contains("Rs. ")) {
				arr = s.split(" ");
				priceList.add(arr[1]);
			}
			if (s.contains("Launch Date : ")) {
				dateList.add(s);
			}
		}
//		System.out.println("divyesh----"+priceList);
		// Wait for page elements to load
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Initialize an ArrayList to store upcoming bikes with price less than 4 Lakhs
		ArrayList<String> upcomingBikes = new ArrayList<String>();
		if(nameList.size()>0) {
			for (int i = 0; i <nameList.size(); i++) {
			String temp = nameList.get(i);
			
			// Convert bike price to a double value
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // parse numbers in French-style format
			Number number = format.parse(priceList.get(i));
			double price = number.doubleValue();
			if(priceList.get(i).contains(",")) {
				price = price/100;
				
			}
			
			// Combine bike name, price and launch date to a single string
			String info = temp + "  " + priceList.get(i) + "   " + dateList.get(i);
			
			// Check if bike name is present in the string and price is less than 4 Lakhs
			if (info.contains(temp)) {
				if (Double.compare(price, 4d) < 0) {
					upcomingBikes.add(info);			
					ExcelData.setCellRowAgain(file, "UpComingBikes", i+2, 1, temp);
					if(priceList.get(i).contains(",")) {
						ExcelData.setCellDataAgain(file, "UpComingBikes", i+2, 5, priceList.get(i));	
					}else {
						ExcelData.setCellDataAgain(file, "UpComingBikes", i+2, 5, priceList.get(i)+"  Lakh");
					}					
					ExcelData.setCellDataAgain(file, "UpComingBikes", i+2, 8, dateList.get(i));							
				}
		  }
		} 
			
		// Print the list of upcoming bikes to the console
		System.out.println("Upcoming Honda Bikes Below 4 Lakhs are as follows:");
		for (int i = 0; i < upcomingBikes.size(); i++) {
			System.out.println(upcomingBikes.get(i));
		}

	}


}
}
