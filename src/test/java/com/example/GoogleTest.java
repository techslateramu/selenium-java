import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
   // Set up ExtentReports with detailed configurations
   ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/index.html");
   sparkReporter.config().setReportName("Automation Test Report");
   sparkReporter.config().setDocumentTitle("Automation Report - Selenium TestNG");
   sparkReporter.config().setTheme(Theme.STANDARD); // Use Theme.DARK for a dark theme
   sparkReporter.config().setEncoding("UTF-8");

   extent = new ExtentReports();
   extent.attachReporter(sparkReporter);

   // Add system information to the report
   extent.setSystemInfo("Tester", "Your Name");
   extent.setSystemInfo("Environment", "QA");
   extent.setSystemInfo("Browser", "Chrome");
   extent.setSystemInfo("OS", System.getProperty("os.name"));
   extent.setSystemInfo("Java Version", System.getProperty("java.version"));        // Set Chrome options for headless mode
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
            test.info("Navigated to Google homepage.");

            // Verify page title
            String pageTitle = driver.getTitle();
            Assert.assertEquals(pageTitle, "Google", "Page title does not match!");
            test.pass("Page title is verified successfully.");

        } catch (AssertionError | Exception e) {
            // Log the failure to ExtentReports
            test.fail("Test failed with exception: " + e.getMessage());
            throw e; // Rethrow the exception to fail the test in TestNG
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
}
