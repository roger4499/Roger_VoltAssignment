package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.CheckOut;
import helper.DemoHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckOutStepDef {
	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	DemoHelper helper=new DemoHelper();
	CheckOut checkout = new CheckOut();

	@Then("Checkout button is displayed in cart and is clicked")
	public void checkout_button_is_displayed_in_cart_and_is_clicked() {
	    helper.trueAssert(helper.isElementPresent(By.xpath("//*[@id='checkout']")), "Check out button not found as expected! failure!");
	    driver.findElement(By.xpath("//*[@id='checkout']")).click();
	}
	@When("I click cancel")
	public void i_click_cancel() {
		helper.trueAssert(helper.isElementPresent(By.xpath("//*[@id='cancel']")), "cancel button not found as expected! failure!");
	    driver.findElement(By.xpath("//*[@id='cancel']")).click();
	}
	
	@When("I do not enter any details and click continue then verify error")
	public void i_do_not_enter_any_details_and_click_continue_then_verify_error() {
		checkout.verifyErrorOnClickingContinue();
	}
	
	@When("I enter firstName as {string} lastName as {string} and zip as {int} and click continue")
	public void i_enter_first_name_as_last_name_as_and_zip_as_and_click_continue(String fname, String lname, Integer zip) {
	    checkout.enterCheckoutFormValues(fname,lname,zip);
	}
	@Then("{string} item exists with payment information")
	public void item_exists_with_payment_information(String itemName) {
	    checkout.verifyCheckoutItemAndPaymentDetails(itemName);
	}
	@When("I click finish")
	public void i_click_finish() {
	    driver.findElement(By.xpath("//*[@id='finish']")).click();
	}
	@Then("verify checkout complete information and click back home")
	public void verify_checkout_complete_information_and_click_back_home() {
	    checkout.verifyCheckoutCompleteInformation();
	    driver.findElement(By.xpath("//*[@id='back-to-products']")).click();
	}
	
}
