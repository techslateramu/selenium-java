import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogleTest {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Set up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/index.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        test = extent.createTest("GoogleTest Setup", "Setup ChromeDriver and ExtentReports");
        test.pass("Setup completed successfully.");
    }

    @Test
    public void testGoogle() {
        test = extent.createTest("Google Search Page Test", "Verifies Google search page title and elements");

        try {
            // Navigate to Google
            driver.get("https://www.google.com");
            test.info("Navigated to Google homepage.")
                .addScreenCaptureFromPath(captureScreenshot("Google_Homepage"));

            // Verify page title
            String pageTitle = driver.getTitle();
            Assert.assertEquals(pageTitle, "Google", "Page title does not match!");
            test.pass("Page title is verified successfully.")
                .addScreenCaptureFromPath(captureScreenshot("Page_Title_Verification"));

        } catch (AssertionError | Exception e) {
            // Log the failure and capture a screenshot
            String screenshotPath = captureScreenshot("Failure_Screenshot");
            test.fail("Test failed with exception: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
            throw e; // Rethrow the exception to mark the test as failed
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }

    private String captureScreenshot(String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "target/screenshots/" + screenshotName + "_" + timestamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.out.println("Error while capturing screenshot: " + e.getMessage());
        }

        return filePath;
    }
}
