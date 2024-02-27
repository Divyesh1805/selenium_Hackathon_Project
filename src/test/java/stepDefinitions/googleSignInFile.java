package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.baseClass.Base;
import com.googleSignIn.GoogleSignIn;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class googleSignInFile {
	public static WebDriver driver;
	Base base = new Base();
	public static GoogleSignIn gs;
	@Given("Present on ZigWheel page")
	public void present_on_zig_wheel_page() {
		driver = base.driverSetup();
		gs= new GoogleSignIn(driver);
	    System.out.println("present on Zigwheels page");
	}

	@When("Click on Login button and select Google")
	public void click_on_login_button_and_select_google() throws InterruptedException {
	    gs.clickSignIn();
	    gs.googleSignIn();
	}

	@When("Try logging with invalid email")
	public void try_logging_with_invalid_email() throws InterruptedException {
	    gs.emailInput("abc@abc");
	    gs.emailNext();
	}

	@Then("Verify and capture the error message")
	public void verify_and_capture_the_error_message() throws InterruptedException {
	    gs.getErrorMessage();
	    driver.quit();
	}

}
