package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.LogIn;
import helper.DemoHelper;
import helper.DriverRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInSauceDemo {
	
	DriverRunner driverRunnerObject=DriverRunner.getInstanceOfDriverRunner();
	WebDriver driver = driverRunnerObject.getDriver();
	
	String[] usernames; 
	String password;
	
	DemoHelper helper = new DemoHelper();
	LogIn login = new LogIn();
	
	@Given("I open sauce demo on web")
	public void i_open_sauce_demo_on_web() {
	 helper.launchURL("https://www.saucedemo.com/");
	}
	@Then("I am on the login page")
	public void i_am_on_the_login_page() {
	   helper.trueAssert(helper.isElementPresent(By.xpath("//*[@id='login-button']")), "Failure! Not on Login Page!");
	}
	@Then("Get all usernames and password")
	public void get_all_usernames_and_password() {
	   usernames = login.getLoginUserName();
	   password = login.getLoginPassword();
	}
	@When("I enter a random username and the given password it shows me an error")
	public void i_enter_a_random_username_and_the_given_password_it_shows_me_an_error() {
		String randomUsername=helper.generateRandomString(12);
		login.login(randomUsername, password);
		login.verifyLoginError();
	}
	@When("I enter a given username and random password it shows me an error")
	public void i_enter_a_given_username_and_random_password_it_shows_me_an_error() {
		String randomPassword=helper.generateRandomString(12);
		login.login(usernames[1], randomPassword);
		login.verifyLoginError();  
	}
	@Then("Login with each given username and given password to verify all users are able to login")
	public void login_with_each_given_username_and_given_password_to_verify_all_users_are_able_to_login() {
		System.out.println(usernames.length);
		for(int i = 1; i<=usernames.length-1;i++)
		{
			//since locked out user is locked out and hence unable to login to the application
			if(usernames[i].equals("locked_out_user"))
				continue;
			login.login(usernames[i], password);
			login.isLoggedIn();
			helper.logout();
		}
	}
}
