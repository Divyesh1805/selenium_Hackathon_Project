package com.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.googleSignIn.GoogleSignIn;
import com.baseClass.Base;
import com.upcomingBikes.UpcomingBikes;
import com.usedCars.Usedcars;

public class RegressionTests {

	public static WebDriver driver;

	// Setting up the driver before each test method
	@BeforeMethod
	public void setup() {
		Base base = new Base();
		driver = base.driverSetup();
	}
    
	// Test method for Upcoming Bikes
	@Test(priority = 1)
	public void upComingBikes() throws Exception{
		
		UpcomingBikes up = new UpcomingBikes(driver);
		up.newBikesMenu();
		up.selectUpcomingBike();
		up.selectManufacturer();
		up.viewMoreBikes();
		up.bikeModels();
		
	}
	
	// Test method for Used Cars
	@Test(priority = 2)
	public void usedCars() throws InterruptedException, IOException {
		
		Usedcars userCars = new Usedcars(driver);
		userCars.usedCarsMenu();
		userCars.selectChennaiUsedCars();
		userCars.modelList();
	}
   
	// Test method for Google Sign In
	@Test(priority = 3)
	public void testGoogleSignIn() throws InterruptedException {
		
		GoogleSignIn signin = new GoogleSignIn(driver);
		signin.clickSignIn();
		signin.googleSignIn();
		signin.emailInput("abc@abc");
		signin.emailNext();
		signin.getErrorMessage();
		
	}
	
	// Closing the driver after each test method
	@AfterMethod
	public void closeDriver() {
		Base base = new Base();
		base.quitBrowser();
	}
}
