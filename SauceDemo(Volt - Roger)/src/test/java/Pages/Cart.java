package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DemoHelper;
import helper.DriverRunner;

public class Cart {
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	DemoHelper helper = new DemoHelper();
	
	//method to add item to cart from inventory, accepts single item at a time
	public String addToCartFromInventory(String itemName)
	{
		String price="";
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		String priceXpath = itemNameXpath +"/../../following-sibling::div//div[@class='inventory_item_price']";
		String addToCartXpath = priceXpath +"/following-sibling::button";
		
		if(helper.isElementPresent(By.xpath(itemNameXpath)))
		{
			System.out.println("Item Present in Inventory");
			
			//Getting Price if element exists
			price = driver.findElement(By.xpath(priceXpath)).getText();
			
			//If already added then Remove and then add
			if(driver.findElement(By.xpath(addToCartXpath)).getText().contains("Remove"))
			{
				System.out.println("Warning! The item is already added to cart! Removing and adding again!");
				driver.findElement(By.xpath(addToCartXpath)).click();
			}
			
			//Clicking to add item to cart
			driver.findElement(By.xpath(addToCartXpath)).click();
			
			
		}
		else
		{
			helper.failAssert("Failure! Item does not exist in inventory");
		}
		
		return price;
	}

	//method to get cart container icon badge count(Returns -1 if icon not found)
	public int getCurrentCartIconBadge() {
		String cartContainerXpath="//*[@id='shopping_cart_container']";
		String badgeXpath="//*[@class='shopping_cart_badge']";
		
		if(helper.isElementPresent(By.xpath(cartContainerXpath)))
		{
			//if cart icon found check if badge is present
			if(helper.isElementPresent(By.xpath(badgeXpath)))
			{
				return Integer.parseInt(driver.findElement(By.xpath(badgeXpath)).getText());
			}
			else
				return 0; //if badge not present then cart item count is 0
		}
		
		else
		{
			helper.failAssert("Cart Icon not found");
			return -1;
		}
	}
	
	//method to click on cart icon
	public void clickOnCartIcon()
	{
		String cartIconXpath = "//*[@id='shopping_cart_container']";
		if(helper.isElementPresent(By.xpath(cartIconXpath)))
		{
			driver.findElement(By.xpath(cartIconXpath)).click();
		}
		else
			helper.failAssert("Cart icon not present on page");
		
	}

	//method to verify given cart item name, price and current cart icon badge count
	public boolean verifyCartItemPriceAndBadge(String itemName, String price, int currentCartIconBadge) {
		
		String itemNameXpath, priceXpath, cartBadgeXpath;
		itemNameXpath="//*[@class='inventory_item_name']";
		priceXpath="//*[@class='inventory_item_price']";
		cartBadgeXpath="//*[@class='shopping_cart_badge']";
		
		if(helper.isElementPresent(By.xpath(itemNameXpath)) && helper.isElementPresent(By.xpath(priceXpath)) && 
				helper.isElementPresent(By.xpath(cartBadgeXpath)))
		{
			if(itemName.equals(driver.findElement(By.xpath(itemNameXpath)).getText())
					&& price.equals(driver.findElement(By.xpath(priceXpath)).getText())
					&& currentCartIconBadge == Integer.parseInt(driver.findElement(By.xpath(cartBadgeXpath)).getText()))
			{
				System.out.println("Passed! Item Name, Price and Badge Count matches as expected!");
				return true;
			}
			
			else
			{
				helper.failAssert("Failure: Item, price and badge details do not match cart values");
				return false;
			}
		}
		
		else {
			System.out.println("Failure! One of the element does not exist!");
			return false;
		}
	}

	//continue shopping from cart
	public void continueShoppingFromCart() {
		
		String continueShoppingXpath= "//*[@id='continue-shopping']";
		
		//fail if continue shopping button not found on cart page
		if(!helper.isElementPresent(By.xpath(continueShoppingXpath)))
			helper.failAssert("Continue Shopping button not found!");
		
		else
		{
			driver.findElement(By.xpath(continueShoppingXpath)).click();
			//verify navigated back to inventory
			
			helper.verifyOnPage("inventory");
		}
		
	}
	
	//Removes item from cart page
	public void removeItemFromCartPage(String itemName) {
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		String removeXpath = itemNameXpath +"/../../..//div[@class='item_pricebar']//button";
		
		driver.findElement(By.xpath(removeXpath)).click();
		
		helper.falseAssert(helper.isElementPresent(By.xpath(itemNameXpath))
				,"Failure! Element is present in cart even when remove was clicked!");
	}
}
