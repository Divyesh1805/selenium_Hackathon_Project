package com.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.googlesignin.GoogleSignin;
import com.baseClass.Base;
import com.googleSignIn.GoogleSignIn;
import com.upcomingBikes.UpcomingBikes;
import com.usedCars.Usedcars;

public class AllTests {
	public static WebDriver driver;
	public static ExtentSparkReporter reporter = new ExtentSparkReporter(
			System.getProperty("user.dir") + "/ExtentReport/extentReportFile.html");
	public static ExtentReports extent = new ExtentReports();
	Base base = new Base();
	String dropDown = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/ManufacturerDropdown.png";
	String bikeMenu = System.getProperty("user.dir") + "/ScreenShots/UpcomingBikes/NewBikesMenu.png";
	String usedCarMenu = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsMenu.png";
	String chennaiCars1 = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsInChennai1.png";
	String chennaiCars2 = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsInChennai1.png";
	String googleSignIn1 = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/SignInButton.png";
	String googleSignIn3 = System.getProperty("user.dir") + "/ScreenShots/GoogleSignIn/ErrorMessage.png";
	
	@BeforeMethod
	public void setup() {		
		driver = base.driverSetup();
		extent.attachReporter(reporter);
		reporter.config().setDocumentTitle("Automation Result");
		reporter.config().setReportName("Indentify New Bikes Automation Test");
		reporter.config().setTheme(Theme.DARK);
		ExtentTest logger = extent.createTest("Driver Test");
		logger.log(Status.INFO, "Browser launched");
		logger.log(Status.INFO, "Navigated to https://www.zigwheels.com/");	
	}
    
	@Test(priority = 1)
	public void upComingBikes() throws Exception{
		UpcomingBikes up = new UpcomingBikes(driver);
		
		up.newBikesMenu();
		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest("Upcoming Bikes Test");
		logger.log(Status.INFO, "Mouse Hover on 'New Bikes'");		
		up.selectUpcomingBike();
		logger.log(Status.INFO, "Upcoming Bikes submenu is selected");	
		logger.addScreenCaptureFromPath(bikeMenu);
		up.selectManufacturer();
		logger.log(Status.INFO, "Manufacturer name is selected as 'Honda'");	
		up.viewMoreBikes();
		logger.log(Status.INFO, "View More button is clicked");
		up.bikeModels();
		logger.log(Status.INFO, "Upcoming Bikes under 4 lac is displayed on console ");
		logger.log(Status.PASS, "Upcoming Bikes test is passed");		
		logger.addScreenCaptureFromPath(dropDown);
		extent.flush();
		
	}
	
	@Test(priority = 2)
	public void usedCars() throws InterruptedException, IOException {
		Usedcars userCars = new Usedcars(driver);
		
		userCars.usedCarsMenu();
		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest("Used Cars in Chennai Test");
		logger.log(Status.INFO, "Mouse Hover on 'Used Cars'");	
		userCars.selectChennaiUsedCars();
		logger.log(Status.INFO, "Find Used Cars in Chennai is selected as Location");
		userCars.modelList();
		logger.log(Status.INFO, "Popular Models List is Displayed on console");
		logger.log(Status.PASS, "Used cars in chennai test is passed");
		logger.addScreenCaptureFromPath(chennaiCars1);
		logger.addScreenCaptureFromPath(chennaiCars2);
		logger.addScreenCaptureFromPath(usedCarMenu);
		extent.flush();
	}
   
	@Test(priority = 3)
	public void testGoogleSignIn() throws InterruptedException {
		GoogleSignIn signin = new GoogleSignIn(driver);
		signin.clickSignIn();
		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest("Google Sign In Test");
		logger.log(Status.INFO, "Sign In button is clicked");
		signin.googleSignIn();
		logger.log(Status.INFO, "Continue with Google button is clicked");
		signin.emailInput("abc@abc");
		logger.log(Status.INFO, "An invalid email id is entered into Email field");
		signin.emailNext();
		logger.log(Status.INFO, "Next button is clicked after entering the invalid email id");
		signin.getErrorMessage();
		logger.log(Status.INFO,
				"Error message is captured successfully and displayed on console");
		logger.log(Status.PASS, "Google Sign In test is passed");
		logger.addScreenCaptureFromPath(googleSignIn1);
		logger.addScreenCaptureFromPath(googleSignIn3);
		extent.flush();	
	}
	
	
	@AfterMethod
	public void closeDriver() {
//		Base base = new Base();
		base.quitBrowser();
	}
	
}
