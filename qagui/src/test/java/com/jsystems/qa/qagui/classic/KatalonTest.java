package com.jsystems.qa.qagui.classic;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

public class KatalonTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader().getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("https://wordpress.com/");
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.id("usernameOrEmail")).click();
        driver.findElement(By.id("usernameOrEmail")).clear();
        driver.findElement(By.id("usernameOrEmail")).sendKeys("testautomation112019@wp.pl");
        // ERROR: Caught exception [unknown command []]
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Terms of Service'])[1]/following::button[1]")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("testautomation");
        // ERROR: Caught exception [unknown command [ass]]
        // ERROR: Caught exception [unknown command []]
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
