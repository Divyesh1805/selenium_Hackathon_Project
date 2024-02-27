package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.baseClass.Base;
import com.usedCars.Usedcars;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class usedCarFile {
	WebDriver driver;
	Base base = new Base();
	public static Usedcars uc;
	
	@Given("Be on the ZigWheel Page")
	public void be_on_the_zig_wheel_page() {
		driver = base.driverSetup();
		uc = new Usedcars(driver);
	   System.out.println("present on the zig wheel"); 
	}

	@When("Hover to Used car and select Chennai")
	public void hover_to_used_car_and_select_chennai() throws InterruptedException {
	    uc.usedCarsMenu();
	    uc.selectChennaiUsedCars();
	}

	@Then("Get the popular models and store it in list")
	public void get_the_popular_models_and_store_it_in_list() throws InterruptedException, IOException {
	    uc.modelList();
	    driver.close();
	}
}
