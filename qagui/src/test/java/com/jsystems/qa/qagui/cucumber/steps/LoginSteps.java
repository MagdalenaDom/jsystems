package com.jsystems.qa.qagui.cucumber.steps;

import com.jsystems.qa.qagui.cucumber.ConfigStepCucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    
    WebDriver driver;
    public LoginSteps(ConfigStepCucumber configStepCucumber) {
        driver = configStepCucumber.setUp();
       
    }
    

    @Given("^User start on main page$")
    public void userStartOnMainPage() {
    }

    @When("^User logs to the user panel$")
    public void userLogsToTheUserPanel() {
    }

    @Then("^Use can modify user profile$")
    public void useCanModifyUserProfile() {
    }
}
