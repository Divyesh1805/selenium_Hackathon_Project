package com.usedCars;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.baseClass.Wait;
import com.utility.ExcelData;
import com.utility.ScreenShot;

public class Usedcars {
	WebDriver driver;
	public String filePath = null;
	ScreenShot ss = new ScreenShot();
	//Constructor to initialize WebElements
	String	file = System.getProperty("user.dir")+"\\Book.xlsx";
	public Usedcars(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// WebElements for used cars page
	
	@FindBy(xpath = "//a[normalize-space()='Used Cars']")
	WebElement usedCarsMenu;
	
	@FindBy(xpath = "//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	WebElement chennaiUsedCars;
	
	@FindBy(xpath = "//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li")
	List<WebElement> modelName;
	
	
	// Mouse hover on 'Used cars' menu from the main menu bar
	
	public void usedCarsMenu() throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(usedCarsMenu).perform();
		Thread.sleep(3000); // for taking screenshot
		filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsMenu.png";
		ss.captureTestScreenshot(driver, filePath);
		System.out.println("Done Mouse hover on 'Used cars' from Menu");
	}
	
	// Click on 'Used cars in Chennai' link
	
	public void selectChennaiUsedCars() {
		
		chennaiUsedCars.click();
		
//		filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsInChennai.png";

	}
	
	// Get list of all popular models of used cars in Chennai and write to excel file
	
	public void modelList() throws InterruptedException, IOException {
		 
		// Scroll down to make all elements visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("scroll(0, 500)");
	    filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsInChennai1.png";
		ss.captureTestScreenshot(driver, filePath);
	    Wait wait = new Wait();
		wait.waitListExplicit(30, modelName, driver);
		filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsInChennai2.png";
		ss.captureTestScreenshot(driver, filePath);
	    // Display the list of popular models on the console
	    System.out.println("Following is the list of Popular models:");
	    ExcelData.setNewSheet(file, "UsedCar", 0, 1, "Car Model and Make");
		for (int i = 0; i < modelName.size(); i++) {
			System.out.println(modelName.get(i).getText());
			String name = modelName.get(i).getText();
			ExcelData.setCellRowAgain(file, "UsedCar", i+2, 1, name);
		}
	}


}
