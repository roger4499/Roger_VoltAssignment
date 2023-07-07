package helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Pages.Cart;

public class DemoHelper {
	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	//method to launch given URL
	public void launchURL(String url)
	{
		driver.get(url);
	}
	
	//method to verify element on UI with text
	public void verifyText(String text, String ui) {
		try {
			driver.findElement(
					By.xpath("//*[contains(text(),'"+ text + "')]"));
			System.out.println("Found Element with text " + text + " on " + ui + " UI");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find Element with text " + text + " on " + ui + " UI");
		}
	}
	

	//method to verify if an element is present on the page! returns boolean - use with assert
	public boolean isElementPresent(By by)
	{
		if(driver.findElements(by).size()>0)
			{
			System.out.println("Element is Present! "+by);
			return true;
			}
		
		else {
			System.out.println("Element is not Present!"+by);
			return false;
		}
	}
	
	//method to go to hamburger menu icon and logout of the application
	public void logout() {

		if(isElementPresent(By.id("react-burger-menu-btn")))
		{
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("logout_sidebar_link")).click();
		}
		else
		{
			System.out.println("FAILURE! BURGER MENU ICON NOT FOUND, Can not log out!");
			failAssert("FAILURE! Can not log out! BURGER MENU ICON NOT FOUND");
		}
	}
	
	//Helper method to generate RandomString - can use for username/password or other inputs
	public String generateRandomString(int length)
	{
		String randomString = UUID.randomUUID().toString().substring(0, length);
		return randomString;
	}
	
	

	//method to verify the user is on correct page url! takes substring of URL as input
	public void verifyOnPage(String pageName)
	{
		if(driver.getCurrentUrl().toLowerCase().contains(pageName.toLowerCase()))
		{
			System.out.println("Current URL: "+driver+"\nOn correct page: "+pageName);
		}
		else
		{
			System.out.println("Failure! Current URL: "+driver+"\nNot On correct page: "+pageName);
			failAssert("Failure! Not on expected page! please check url and page name passed as parameter.");
		}
	}
	
	//Assertion methods using testNG Assert class
	public void trueAssert(boolean condition, String message)
	{
		Assert.assertTrue(condition, message);
	}
	
	public void falseAssert(boolean condition, String message)
	{
		Assert.assertFalse(condition, message);
	}
	
	public void failAssert(String message)
	{
		Assert.fail(message);
	}
	



}
