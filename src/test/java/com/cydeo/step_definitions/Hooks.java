package com.cydeo.step_definitions;

import com.cydeo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

// In this class we will be able to create "pre" and "post"
//condition for all the SCENARIOS and even STEPS
public class Hooks {
    @Before
    public void setUp() {
        System.out.println("----> This is @Before Each Scenario");
    }
    @Before ("@library ")
    public void login_scenario_before() {
        System.out.println("----> This is @Before Each Scenario");
    }
    @After
    public void tearDown(Scenario scenario) {
if (scenario.isFailed()) {
    byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    scenario.attach(screenshot, "image/png", scenario.getName());
}

        System.out.println("----> This is @After Each Scenario");
      // Driver.closeDriver();

    }
@BeforeStep
    public void beforeStep() {
        System.out.println("----> Before Step: ");
    }


}
