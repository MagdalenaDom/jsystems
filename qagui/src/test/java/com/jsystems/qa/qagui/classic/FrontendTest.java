package com.jsystems.qa.qagui.classic;

import com.jsystems.qa.qagui.Configuration;
import com.jsystems.qa.qagui.classic.pages.LoginPage;
import com.jsystems.qa.qagui.classic.pages.MainWordpressPage;
import com.jsystems.qa.qagui.classic.pages.UserPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.google.common.truth.Truth.assertThat;
import static com.jsystems.qa.qagui.classic.pages.LoginPage.primaryButtonSelector;
import static com.jsystems.qa.qagui.classic.pages.LoginPage.usernameOrEmailSelector;
import static com.jsystems.qa.qagui.classic.pages.UserPage.userAvatarSelector;
import static com.jsystems.qa.qagui.classic.pages.UserPage.userDisplayNameSelector;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FrontendTest extends ConfigFrontend {


    @Test
    public void frontTest() {

        driver.get("https://wordpress.com/");
      //  WebElement textElement_1 = driver.findElement(By.cssSelector("h1.lpc-headline-title span:nth-child(1)"));
        MainWordpressPage mainWordpressPage = new MainWordpressPage(driver);
        String text1 = mainWordpressPage.textElement_1.getText();
        assertTrue(text1.equals("WordPress powers"));

      //  WebElement textElement_2 = driver.findElement(By.cssSelector("h1.lpc-headline-title span:nth-child(2)"));
        String text2 = mainWordpressPage.textElement_2.getText();
        assertTrue(text2.contains("% of the internet."));
        assertThat(text2).matches("\\d+(% of the internet.)");
    }

      //  try{
      //      Thread.sleep(2000);
      //  }catch (InterruptedException e){
      //      e.printStackTrace();
      //  }

        @Test
        public void loginTest(){

            driver.navigate().to("https://www.wordpress.com/");
            MainWordpressPage mainWordpressPage = new MainWordpressPage(driver);

            String loginIconSelector = ".x-nav-item.x-nav-item--wide.x-nav-item--logged-in";
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loginIconSelector)));

            WebElement loginIcon = driver.findElement(By.cssSelector(loginIconSelector));
            wait.until(ExpectedConditions.elementToBeClickable(loginIcon));

            loginIcon.click();

            LoginPage loginPage = new LoginPage(driver);
            //String usernameOrEmailSelector = "usernameOrEmail";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(usernameOrEmailSelector)));

           // WebElement usernameInput = driver.findElement(By.id(usernameOrEmailSelector));

            loginPage.usernameInput.clear();
            loginPage.usernameInput.sendKeys(Configuration.LOGIN);

            //String primaryButtonSelector = ".button.form-button.is-primary";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(primaryButtonSelector)));
            //WebElement usernameButton = driver.findElement(By.cssSelector(primaryButtonSelector));
            loginPage.usernameButton.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            //WebElement inputPassword = driver.findElement(By.id("password"));

            loginPage.inputPassword.clear();
            loginPage.inputPassword.sendKeys(Configuration.PASSWORD);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(primaryButtonSelector)));
            WebElement buttonPassword = driver.findElement(By.cssSelector(primaryButtonSelector));
            loginPage.usernameButton.click();

            UserPage userPage = new UserPage(driver);

            //String userAvatarSelector = ".masterbar__item.masterbar__item-me";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(userAvatarSelector)));

            //WebElement userAvatar = driver.findElement(By.cssSelector(userAvatarSelector));
            userPage.userAvatar.click();

            //String userDisplayNameSelector = ".profile-gravatar__user-display-name";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(userDisplayNameSelector)));

            //WebElement userDisplayName = driver.findElement(By.cssSelector(userDisplayNameSelector));
            String userDisplayNameText = userPage.userDisplayName.getText();

            assertThat(userDisplayNameText).isEqualTo("testautomation112019");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(primaryButtonSelector)));
            //WebElement saveUserDetailsButton = driver.findElement(By.cssSelector(primaryButtonSelector));

            assertTrue(userPage.saveUserDetailsButton.isDisplayed());
            assertTrue(!userPage.saveUserDetailsButton.isEnabled());
        }

        @Test
        public void loginTestOnFirefox() {
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setPlatform(Platform.LINUX);
            cap.setVersion("");

            setupDriver();

            driver.get("https://www.wordpress.com/");

            WebElement loginIcon = driver.findElement(By.cssSelector(".x-nav-item.x-nav-item--wide.x-nav-item--logged-in"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(loginIcon));

            loginIcon.click();

            String usernameOrEmailSelector = "usernameOrEmail";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(usernameOrEmailSelector)));

            WebElement usernameInput = driver.findElement(By.id(usernameOrEmailSelector));

            usernameInput.clear();
            usernameInput.sendKeys("testautomation112019@wp.pl");

            String primaryButtonSelector = ".button.form-button.is-primary";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(primaryButtonSelector)));
            WebElement usernameButton = driver.findElement(By.cssSelector(primaryButtonSelector));
            usernameButton.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            WebElement inputPassword = driver.findElement(By.id("password"));

            inputPassword.clear();
            inputPassword.sendKeys("testautomation");
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(primaryButtonSelector)));
            WebElement buttonPassword = driver.findElement(By.cssSelector(primaryButtonSelector));
            usernameButton.click();

            String userAvatarSelector = ".masterbar__item.masterbar__item-me";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(userAvatarSelector)));

            WebElement userAvatar = driver.findElement(By.cssSelector(userAvatarSelector));
            userAvatar.click();

            String userDisplayNameSelector = ".profile-gravatar__user-display-name";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(userDisplayNameSelector)));

            WebElement userDisplayName = driver.findElement(By.cssSelector(userDisplayNameSelector));
            String userDisplayNameText = userDisplayName.getText();

            assertThat(userDisplayNameText).isEqualTo("testautomation112019");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(primaryButtonSelector)));
            WebElement saveUserDetailsButton = driver.findElement(By.cssSelector(primaryButtonSelector));
            assertThat(!saveUserDetailsButton.isDisplayed());
        }


    }

