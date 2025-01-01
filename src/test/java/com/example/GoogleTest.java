import org.openqa.selenium.By;
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

        // Navigate to Google
        driver.get("https://www.google.com");
        test.info("Navigated to Google homepage.");

        // Verify page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Google", "Page title does not match!");
        test.pass("Page title is verified successfully.");

        // Additional verifications (uncomment if needed)
        // WebElement searchButton = driver.findElement(By.name("btnK"));
        // Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed on the page.");
        // test.pass("Google search button is displayed.");

        // WebElement googleLogo = driver.findElement(By.id("hplogo"));
        // Assert.assertTrue(googleLogo.isDisplayed(), "Google logo is not displayed on the page.");
        // test.pass("Google logo is displayed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        test.info("Browser closed successfully.");

        if (extent != null) {
            extent.flush();
        }
        test.info("ExtentReports flushed and test execution completed.");
    }
}
