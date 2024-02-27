package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.baseClass.Base;
import com.upcomingBikes.UpcomingBikes;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UpcomingBikesFile {
	public static WebDriver driver;
	Base base = new Base();
	public static UpcomingBikes up;
	
	@Given("Be on the zigwheel site")
	public void be_on_the_zigwheel_site() {
		driver = base.driverSetup();
		 up = new UpcomingBikes(driver);
		System.out.println("Present on ZigWheels");
	}

	@When("Hover to new bikes and select upcoming bikes")
	public void hover_to_new_bikes_and_select_upcoming_bikes() throws Exception {
	   up.newBikesMenu();
	   up.selectUpcomingBike();
	   
	}

	@When("Select the manufacture as Honda from DropDown")
	public void select_the_manufacture_as_honda_from_drop_down() throws Exception {
	   up.selectManufacturer();
	}

	@Then("Get the deatils for each bike and store in excel")
	public void get_the_deatils_for_each_bike_and_store_in_excel() throws Exception {
	   up.viewMoreBikes();
	   up.bikeModels();
	   driver.close();
	}

}
