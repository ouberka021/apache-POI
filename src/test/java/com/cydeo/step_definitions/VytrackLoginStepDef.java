package com.cydeo.step_definitions;

import com.cydeo.pages.VyTrackDashboardPage;
import com.cydeo.pages.VyTrackLoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import com.cydeo.utilities.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class VytrackLoginStepDef {

    VyTrackDashboardPage dashboardPage = new VyTrackDashboardPage();
    VyTrackLoginPage loginPage = new VyTrackLoginPage();
    String userName;
    String password;
    String firstName;
    String lastName;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
    Driver.getDriver().get(ConfigurationReader.getProperty("vytrack.url"));
    }

    @When("the user login with given credentials from {string} and {int}")
    public void the_user_login_with_given_credentials_from_and(String sheetName, Integer rowNumber) {

        ExcelUtil excelUtil = new ExcelUtil("VyTrackQa2Users.xlsx",sheetName);

        userName = excelUtil.getCellData(rowNumber,0);
        password = excelUtil.getCellData(rowNumber,1);
        firstName = excelUtil.getCellData(rowNumber,2);
        lastName = excelUtil.getCellData(rowNumber,3);
        loginPage.login(userName, password);

    }


    @Then("the user should be able to log in and see their name")
    public void the_user_should_be_able_to_log_in_and_see_their_name() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.fullName));

        System.out.println("Full name = " + dashboardPage.fullName.getText());

        String actualFullName = dashboardPage.fullName.getText();

        Assert.assertTrue(actualFullName.contains(firstName) && actualFullName.contains(lastName));

    }
}
