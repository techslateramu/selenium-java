import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        // Your test code here
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
