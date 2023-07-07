package helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverRunner {

	private static DriverRunner instanceOfDriverRunner = null;

	private WebDriver driver;
	public static String TS;
	
	// Restrict by using private, so other classes can not create object of driver
	private DriverRunner() {

		try {
			// Initializing driver object
			WebDriverManager.chromedriver().setup();


			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Chrome Driver launched");
		}

		catch (Exception e) {
			System.out.println("Exception while launching the driver, FAILURE");
			e.printStackTrace();
		}

		// implicit max timeout for all elements
		// Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

	}

	//Will return the existing driver if driver is already instantiated, if not then it will create a new Driver
	public static DriverRunner getInstanceOfDriverRunner() {
		if (instanceOfDriverRunner == null) {
			instanceOfDriverRunner = new DriverRunner();
		}
		return instanceOfDriverRunner;
	}

	// public method to return the driver object!
	public WebDriver getDriver() {
		return driver;
	}

}
