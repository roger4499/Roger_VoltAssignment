package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.Cart;
import Pages.Inventory;
import Pages.LogIn;
import helper.DemoHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class RemoveStepDef {
	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	DemoHelper helper=new DemoHelper();
	Cart cart = new Cart();
	LogIn login = new LogIn();
	Inventory inv = new Inventory();
	
	
	int previousIconBadgeCount;
	int currentIconBadgeCount;
	@When("I add {string} to the cart, Remove button appears for given item")
	public void i_add_to_the_cart_remove_button_appears_for_given_item(String itemName) {
		inv.verifyRemoveButtonAppearsForItem(itemName);
		previousIconBadgeCount=cart.getCurrentCartIconBadge();
	}
	@When("I click Remove for {string} then add to cart button appears")
	public void i_click_remove_for_then_add_to_cart_button_appears(String itemName) {
	   inv.verifyRemoveSuccessfull(itemName);
	   currentIconBadgeCount=cart.getCurrentCartIconBadge();
	}
	@When("The icon badge decrements by {int}")
	public void the_icon_badge_decrements_by(Integer int1) {
	    helper.trueAssert(previousIconBadgeCount-currentIconBadgeCount==int1, "Failure, badge count does not match after removing an item!");
	}
	@Then("{string} is not found in cart")
	public void is_not_found_in_cart(String itemName) {
	  helper.falseAssert(helper.isElementPresent(By.xpath("//*[@class='inventory_item_name' and text()='"+itemName+"']"))
			  , "Failure! Element is found in cart even when it is supposed to be not found!");
	}
	
	@When("I click Remove for {string} on cart page")
	public void i_click_remove_for_on_cart_page(String itemName) {
	    cart.removeItemFromCartPage(itemName);
	}
	@Then("Remove button does not appear for {string} on inventory page")
	public void remove_button_does_not_appear_for_on_inventory_page(String itemName) {
	    inv.verifyRemoveButtonDoesNotAppearForItem(itemName);
	}
}
