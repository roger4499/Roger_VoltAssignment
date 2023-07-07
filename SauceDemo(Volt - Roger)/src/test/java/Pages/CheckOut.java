package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DemoHelper;
import helper.DriverRunner;

public class CheckOut {
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	DemoHelper helper = new DemoHelper();
	
	//verifies error on checkout first page details
	public void verifyErrorOnClickingContinue() {
		helper.trueAssert(helper.isElementPresent(By.xpath("//*[@id='continue']")), "continue button not found as expected! failure!");
	    driver.findElement(By.xpath("//*[@id='continue']")).click();
		
	    helper.trueAssert(helper.isElementPresent(By.xpath("//*[@data-test='error']")),"Error not found as expected on leaving checkout form values blank!");
	}

	//method to enter firstName, lastName and zipcode values in checkout form
	public void enterCheckoutFormValues(String fname, String lname, Integer zip) {
		
		String fnameXpath="//*[@data-test='firstName']";
		String lnameXpath="//*[@data-test='lastName']";
		String zipXpath="//*[@data-test='postalCode']";
		
		driver.findElement(By.xpath(fnameXpath)).sendKeys(lname);
		driver.findElement(By.xpath(lnameXpath)).sendKeys(fname);
		driver.findElement(By.xpath(zipXpath)).sendKeys(zip.toString());
		
		driver.findElement(By.xpath("//*[@id='continue']")).click();
		helper.falseAssert(helper.isElementPresent(By.xpath("//*[@data-test='error']")),"Error found even when not expected on entering checkout form values details	!");
		
	}
	//method to verify checkout two page contains added itemName and other payment information
	public void verifyCheckoutItemAndPaymentDetails(String itemName) {
		String itemNameXpath = "//*[@class='inventory_item_name' and text() = '"+itemName+"']";
		
		 helper.trueAssert(helper.isElementPresent(By.xpath(itemNameXpath)),"Expected item not found while checking out! FAILURE!");
		 helper.trueAssert(helper.isElementPresent(By.xpath("//*[contains(text(),'Payment Information')]")), "Payment Information not found on checkout page!");
		 helper.trueAssert(helper.isElementPresent(By.xpath("//*[contains(text(),'Price Total')]")), "Price Total not found on checkout page!");
		 helper.trueAssert(helper.isElementPresent(By.xpath("//*[contains(text(),'Shipping Information')]")), "Shipping Information not found on checkout page!");
	}

	//method to verify checkout complete page details! including complete and ThankYou text
	public void verifyCheckoutCompleteInformation() {
		helper.trueAssert(helper.isElementPresent(By.xpath("//*[contains(text(),'Checkout: Complete!')]")), "Checkout complete label not viewed after finishing checkout! FAILURE!");
		helper.trueAssert(helper.isElementPresent(By.xpath("//*[contains(text(),'Thank you for your order!')]")), "Thank You message not displayed after finishing checkout! FAILURE!");
		
	}
}
