package com.googleSignIn;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.baseClass.Wait;
import com.utility.ScreenShot;

public class GoogleSignIn {
	WebDriver driver;
	public String filePath = null;
	ScreenShot ss = new ScreenShot();
	
	//Constructor to initialize the web driver and page objects	
	 public GoogleSignIn(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	 // page objects 
	 @FindBy(xpath = "/html/body/div[12]/div/div/div/div[1]/div/div[3]/div[6]/div")
	 WebElement google;
	 
	 @FindBy(xpath ="//div[@id='forum_login_title_lg']")
	 WebElement signInButton;
	 
	 @FindBy(id = "identifierId")
	 WebElement email;
	 
	 @FindBy(xpath = "//span[contains(text(),'Next')]")
	 WebElement emailNextButton;
	 
	 @FindBy(xpath = "//div[@class='o6cuMc Jj6Lae' and contains(text(),'Enter a valid email or phone number')]")
	 WebElement errorMessage;
	 
	 @FindBy(id = "continue")
	 WebElement continueWithGoogle;
	
	// method to click on Sign In button	 
	public void clickSignIn() throws InterruptedException {	
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/SignInButton.png";	
		signInButton.click();	
		Thread.sleep(5000);
		ss.captureTestScreenshot(driver, filePath);
	}
	
	// method to sign in with Google	
	public void googleSignIn() throws InterruptedException {		
		Wait wait = new Wait();
		wait.waitExplicit(30,google,driver);
		System.out.println("line 66--"+google.isDisplayed());
		Thread.sleep(5000);
		google.click();
	}
     
	// method to enter invalid email ID
   public void emailInput(String emailId) throws InterruptedException {
		
		//new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));	
      try {
		// Get all window handles
		Set<String> windows = driver.getWindowHandles();	
		new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.numberOfWindowsToBe(2));
	    
		// Initialize an iterator for the window handles
		Iterator<String> iterator = windows.iterator();
		
		// Get the handle of the parent window
		String parent = iterator.next();
		
		// Get the handle of the child window
		String child = iterator.next();
		
		driver.switchTo().window(child);
		Thread.sleep(3000);
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/EmailIdField.png";
		ss.captureTestScreenshot(driver, filePath);	
		email.sendKeys(emailId);	
	   } catch (NoSuchElementException e) {
         System.err.println("Exception occurred: " + e.getMessage());
      }
    }
	
    // method to click on Next button after entering email ID  
    public void emailNext() {	
		Actions action = new Actions(driver);
		action.moveToElement(emailNextButton).click().perform();	
	}
    
    // method to capture the error message   
    public void getErrorMessage() throws InterruptedException {	
    	Wait wait = new Wait();
		wait.waitExplicit(30,errorMessage,driver);	
		String message= errorMessage.getText();	
		filePath = System.getProperty("user.dir") + "/ScreenShots/GoogleSignIn/ErrorMessage.png";
		ss.captureTestScreenshot(driver, filePath);
		Thread.sleep(3000);
		System.out.println(message);
	}

}
