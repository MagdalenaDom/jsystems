package com.jsystems.qa.qagui.cucumber.steps;

import com.jsystems.qa.qagui.Configuration;
import com.jsystems.qa.qagui.cucumber.ConfigStepCucumber;
import com.jsystems.qa.qagui.cucumber.page.LoginPage;
import com.jsystems.qa.qagui.cucumber.page.MainWordpressPage;
import com.jsystems.qa.qagui.cucumber.page.UserPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.google.common.truth.Truth.assertThat;
import static com.jsystems.qa.qagui.cucumber.page.LoginPage.*;
import static com.jsystems.qa.qagui.cucumber.page.MainWordpressPage.loginIconSelector;
import static com.jsystems.qa.qagui.cucumber.page.UserPage.userAvatarSelector;
import static com.jsystems.qa.qagui.cucumber.page.UserPage.userDisplayNameSelector;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    
    WebDriver driver;
    public LoginSteps(ConfigStepCucumber configStepCucumber) {
        driver = configStepCucumber.setUp();
       
    }

    MainWordpressPage mainWordpressPage;
    LoginPage loginPage;
    UserPage userPage;

    @Given("^User start on main page$")
    public void userStartOnMainPage() {
        driver.navigate().to(Configuration.BASE_URL);
        MainWordpressPage mainWordpressPage = new MainWordpressPage(driver);
    }

    @When("^User logs to the user panel$")
    public void userLogsToTheUserPanel() {
        mainWordpressPage.waitForElementToBeVisibility(By.cssSelector(loginIconSelector));
        mainWordpressPage.waitForElementToBeClickable(mainWordpressPage.loginIcon);
        mainWordpressPage.loginIcon.click();
        loginPage = new LoginPage(driver);
        loginPage.waitForElementToBeVisibility(By.id(usernameOrEmailSelector));
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(Configuration.LOGIN);
        loginPage.waitForElementToBeClickable(By.cssSelector(primaryButtonSelector));
        loginPage.usernameButton.click();
        loginPage.waitForElementToBeClickable(By.id(passwordInputSelector));
        loginPage.inputPassword.clear();
        loginPage.inputPassword.sendKeys(Configuration.PASSWORD);
        loginPage.waitForElementToBeClickable(By.cssSelector(primaryButtonSelector));
        loginPage.usernameButton.click();
        userPage = new UserPage(driver);
    }

    @Then("^Use can modify user profile$")
    public void useCanModifyUserProfile() {
            userPage.waitForElementToBeClickable(By.cssSelector(userAvatarSelector));
            userPage.userAvatar.click();
            userPage.waitForElementToBeClickable(By.cssSelector(userDisplayNameSelector));

            String userDisplayNameText = userPage.userDisplayName.getText();
            assertThat(userDisplayNameText).isEqualTo("testautomation112019");

            userPage.waitForElementToBeVisibility(By.cssSelector(UserPage.primaryButtonSelector));
            assertTrue(userPage.saveUserDetailsButton.isDisplayed());
            assertFalse(userPage.saveUserDetailsButton.isEnabled());
    }
}
