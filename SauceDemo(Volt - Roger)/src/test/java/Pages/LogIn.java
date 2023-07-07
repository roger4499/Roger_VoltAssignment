package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.DemoHelper;
import helper.DriverRunner;

public class LogIn {

	DriverRunner driverRunnerObject = DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	DemoHelper helper=new DemoHelper();
	
	//method to fetch listed login usernames on the site
	public String[] getLoginUserName()
	{
		String username = driver.findElement(By.xpath("//*[@id='login_credentials']")).getText();
		
		String[] usernames=username.split("\n");
		
		System.out.println(usernames);
		for(String str:usernames) {
			System.out.println(str);
		}	
		
		return usernames;
	}
	
	
	//method to fetch listed Login Password
	public String getLoginPassword()
	{
		String password = driver.findElement(By.xpath("//*[@class='login_password']")).getText();
		System.out.println(password.split("\n")[1]);
		return password.split("\n")[1];
	}
	
	
	//method to login with given username and password
	public void login(String username, String password)
	{
		driver.navigate().refresh();
		if(helper.isElementPresent(By.id("login-button")))
		{
		driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//*[@id='login-button']")).click();
		}
		
		else {
			System.out.println("FAILURE! Not on LOG in page! LoginButton not found");
			helper.failAssert("LoginButton not found!");
		}
	}
	
	//method to Verify if the user is logged in, sees the hamburger and is on inventory page
	public void isLoggedIn()
	{
		if(helper.isElementPresent(By.id("react-burger-menu-btn")) && helper.isElementPresent(By.id("inventory_container")))
			System.out.println("Burger Icon and Inventory Present on Home Page. Login Successfull");
		else {
			System.out.println("Burger Icon and Inventory is NOT Present on Home Page. Login NOT Successfull");
		helper.failAssert("Burger Icon and Inventory is NOT Present on Home Page. Login NOT Successfull");
		}
	}
	
	
	//verify username password error on login page
	public void	verifyLoginError()
	{
		if(helper.isElementPresent(By.xpath("//*[@data-test='error']")))
			{
			System.out.println("Login Error is present!");
			System.out.println("Error displayed is: \n"+driver.findElement(By.xpath("//*[@data-test='error']")).getText());
			}
			
		else {
			System.out.println("Failure! Login Error not present!");
			helper.failAssert("Login Error not present as expected!");
		}
	}

}
