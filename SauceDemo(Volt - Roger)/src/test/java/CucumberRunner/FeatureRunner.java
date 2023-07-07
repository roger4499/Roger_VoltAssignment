package CucumberRunner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import helper.TestNGRunner;
import io.cucumber.testng.CucumberOptions;





@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinitions"},
        tags="@SauceDemo",
 //       dryRun = true,
        monochrome= true,
        plugin = {
                "pretty","html:target/cucumber-reports/cucumber-pretty/cucumber.html",
                //"json:target/cucumber-reports/CucumberTestReport.json",
                //"junit:target/cucumber-reports/CucumberTestReport.xml",
        		
        }
        )

public class FeatureRunner extends TestNGRunner{
	
}
