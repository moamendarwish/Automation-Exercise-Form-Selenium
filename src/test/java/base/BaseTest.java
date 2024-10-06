package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.forms.PracticeForm;

import java.time.Duration;

public class BaseTest {

    // WebDriver instance that will be shared across tests
    protected WebDriver driver;

    // Instance of the page object that will be used in the tests
    protected PracticeForm practiceForm;

    /**
     * This method sets up the WebDriver and initializes the PracticeForm page object before any tests are run.
     * It is annotated with @BeforeClass, so it runs once before the first test method in each test class.
     */
    @BeforeClass
    public void setup() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Set the implicit wait time to 2 seconds for finding elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Maximize the browser window to ensure all elements are visible
        driver.manage().window().maximize();

        // Navigate to the specified URL
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

        // Initialize the PracticeForm page object to interact with form elements
        practiceForm = new PracticeForm(driver);
    }

    /**
     * This method tears down the WebDriver after all test methods have run.
     * It is annotated with @AfterClass, so it runs once after all the tests in the current class have completed.
     */
    @AfterClass
    public void tearDownBrowser() {
        // Close the browser and quit the WebDriver session to free up resources
        if (driver != null) {
            driver.quit();
        }
    }
}