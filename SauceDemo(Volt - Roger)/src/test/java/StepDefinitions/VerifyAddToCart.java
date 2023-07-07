package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.Cart;
import Pages.Inventory;
import Pages.LogIn;
import helper.DemoHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerifyAddToCart {


	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	String password,price;
	int currentCartIconBadge,existingCartIconBadge;
	
	DemoHelper helper = new DemoHelper();
	Cart cart = new Cart();
	LogIn login = new LogIn();

	@When("I login with {string}")
	public void i_login_with(String username) {
		password = login.getLoginPassword();
		login.login(username, password);
	}
	@Then("I am on the {string} Page")
	public void i_am_on_the_page(String pageName) {
		helper.verifyOnPage(pageName);
		existingCartIconBadge=cart.getCurrentCartIconBadge();
	}

	@When("I add {string} to the cart")
	public void i_add_to_the_cart(String itemName) {	
		price=cart.addToCartFromInventory(itemName);
	}
	@Then("The cart icon badge increments by {int}")
	public void the_cart_icon_badge_increments_by(Integer int1) {
		currentCartIconBadge=cart.getCurrentCartIconBadge();
		if(!(currentCartIconBadge-existingCartIconBadge==int1))
			helper.failAssert("Badge is not incremented by "+ int1);
		else
			System.out.println("Passed Badge is incremented by "+ int1);
	}
	@When("I click on the cart icon")
	public void i_click_on_the_cart_icon() {
		cart.clickOnCartIcon();
	}


	@Then("{string} item appears in cart with the same price and badge icon is unchanged")
	public void item_appears_in_cart_with_the_same_price_and_badge_icon_is_unchanged(String itemName) {
		helper.trueAssert(cart.verifyCartItemPriceAndBadge(itemName,price,currentCartIconBadge)
				,"ItemName, Price and badge count Value does not match cart value!");
	}

	@When("I click on continue shopping")
	public void i_click_on_continue_shopping() {
		cart.continueShoppingFromCart();
	}

	@When("I click on hamburger icon and logout")
	public void i_click_on_hamburger_icon_and_logout() {
		helper.logout();
	}

}
