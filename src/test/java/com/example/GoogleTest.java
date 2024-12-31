import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class GoogleTest {
    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set Chrome options for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");

        // Assertion to check the page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Google", "Page title does not match!");

        // // Assertion to check if the Google search button is present
        // WebElement searchButton = driver.findElement(By.name("btnK"));
        // Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed on the page");

        // // Additional assertion for Google's logo
        // WebElement googleLogo = driver.findElement(By.id("hplogo"));
        // Assert.assertTrue(googleLogo.isDisplayed(), "Google logo is not displayed on the page");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
