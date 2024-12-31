package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set ChromeDriver location only if it's not in the PATH
        // System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
    }
    @Test
    public void verifyGoogleTitle() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Verify the title of the page
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is not as expected");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
