package com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.googleSignIn.GoogleSignIn;
import com.baseClass.Base;
import com.upcomingBikes.UpcomingBikes;
import com.usedCars.Usedcars;

public class SmokeTests {
	public static WebDriver driver;

	@BeforeMethod
	public void setup() {
		Base base = new Base();
		driver = base.driverSetup();
	}
    
	@Test(priority = 1)
	public void upComingBikes() throws Exception{
		
		UpcomingBikes up = new UpcomingBikes(driver);
		up.newBikesMenu(); // Clicks on the New Bikes menu
		up.selectUpcomingBike(); // Clicks on the Upcoming Bikes submenu
		System.out.println("Upcoming Bikes submenu test passed");
		
	}
	
	@Test(priority = 2)
	public void usedCars() throws InterruptedException {
		
		Usedcars userCars = new Usedcars(driver);
		userCars.usedCarsMenu(); // Clicks on the Find Used Cars menu
		System.out.println("Find Used Cars submenu test passed");
	}
   
	@Test(priority = 3)
	public void testGoogleSignIn() throws InterruptedException {
		
		GoogleSignIn signin = new GoogleSignIn(driver);
		signin.clickSignIn(); // Clicks on the Sign In button
		System.out.println("Sign In Button functionality passed");
	}
	
	@AfterMethod
	public void closeDriver() {
		
		Base base = new Base();
		base.quitBrowser();
	}

}
