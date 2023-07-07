package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DemoHelper;
import helper.DriverRunner;

public class Inventory {
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	DemoHelper helper = new DemoHelper();
	Cart cart = new Cart();
	
	//verifies Remove button on inventory page
	public void verifyRemoveButtonAppearsForItem(String itemName) {
		cart.addToCartFromInventory(itemName);
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		String priceXpath = itemNameXpath +"/../../following-sibling::div//div[@class='inventory_item_price']";
		String addToCartXpath = priceXpath +"/following-sibling::button";
		
		helper.trueAssert(driver.findElement(By.xpath(addToCartXpath)).getText().contains("Remove")
				,"Failure! Remove button not found even after adding to cart");
		
		System.out.println("Passed! Remove button is present for "+itemName);
	}

	
	//verifies item Removed in Inventory
	public void verifyRemoveSuccessfull(String itemName) {
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		String priceXpath = itemNameXpath +"/../../following-sibling::div//div[@class='inventory_item_price']";
		String removeXpath = priceXpath +"/following-sibling::button";
		
		
		if(driver.findElement(By.xpath(removeXpath)).getText().contains("Remove"))
		{
			driver.findElement(By.xpath(removeXpath)).click();
			
			helper.trueAssert(driver.findElement(By.xpath(removeXpath)).getText().contains("Add to cart"),
					"Button did not change to add to cart on clicking remove, FAILURE!");
		}
		
		else
			helper.failAssert("Remove button not present as expected");
	}

	
	//verify remove option does not appear for an item name on inventory page
	public void verifyRemoveButtonDoesNotAppearForItem(String itemName) {
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		String priceXpath = itemNameXpath +"/../../following-sibling::div//div[@class='inventory_item_price']";
		String addToCartXpath = priceXpath +"/following-sibling::button";
		
		helper.falseAssert(driver.findElement(By.xpath(addToCartXpath)).getText().contains("Remove")
				,"Failure! Remove button found even when not expected");
		
		System.out.println("Passed! Remove button is not present for "+itemName);
	}
}
