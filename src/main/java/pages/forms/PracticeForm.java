package pages.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PracticeForm {

    private WebDriver driver;
    private WebDriverWait wait;
    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By genderRadioButtonOptions = By.xpath("//input[@type='radio']");
    private By mobileField = By.id("mobile");
    private By dateOfBirthField = By.id("dob");
    private By subjectField = By.id("subjects");
    private By pictureField = By.xpath("//input[@id='picture']");
    private By currentAddressField = By.xpath("//textarea[@id='picture']");
    private By stateDropDown = By.id("state");
    private By cityDropDown = By.id("city");
    private By loginButton = By.xpath("//input[@type='submit']");

    // Constructor initializes the WebDriver and WebDriverWait
    public PracticeForm(WebDriver driver) {
        this.driver = driver;
        // Use WebDriverWait for element waiting instead of hard-coded waits
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Private method to enter text into any input field
    private void enterText(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear(); // Clear the field before entering text
        driver.findElement(locator).sendKeys(text);
    }

    // Set the name field value
    public void setName(String firstname) {
        enterText(nameField, firstname);
    }

    // Set the email field value
    public void setEmail(String email) {
        enterText(emailField, email);
    }

    // Generic method to select a radio button based on the label
    public void selectGenderByLabel(String genderOption) {
        List<WebElement> genderOptions = driver.findElements(genderRadioButtonOptions);
        try {
            // Loop through radio button options and select based on the option label
            for (WebElement option : genderOptions) {
                // Use RelativeLocator to find the label associated with the radio button input element
                String labelText = driver.findElement(RelativeLocator.with(By.tagName("label")).near(option)).getText();
                if (labelText.equalsIgnoreCase(genderOption)) {
                    wait.until(ExpectedConditions.elementToBeClickable(option));
                    option.click(); // Click the radio button once the label matches
                    break; // Break once the element is found
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("The provided radio button option is not supported.");
            e.printStackTrace();
        }
    }

    // Set the mobile number
    public void setMobileNumber(String mobileNumber) {
        enterText(mobileField, mobileNumber);
    }

    // Set the date of birth field value
    public void setDateOfBirth(String dateOfBirth) {
        enterText(dateOfBirthField, dateOfBirth);
    }

    // Set the subject field value
    public void setSubjectField(String subject) {
        enterText(subjectField, subject);
    }

    //upload a picture
    public void uploadPicture(String filePath) {
        enterText(pictureField,filePath);
    }

    // Set the current address value
    public void setCurrentAddress(String address) {
        enterText(currentAddressField, address);
    }

    /**
     * Method to select a hobby by label.
     * It finds the checkbox based on the label index associated with it.
     * @param hobby the label of the hobby to select
     */
    public void selectHobbyByLabel(String hobby) {
        if (hobby.equalsIgnoreCase("Sports"))
            driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        else if (hobby.equalsIgnoreCase("Reading"))
            driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
        else if (hobby.equalsIgnoreCase("Music"))
            driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
        else
            System.out.println("the provided hobby "+ hobby+" is not supported");
    }

    /**
     * Method to select an option from the state dropdown.
     * @param option the visible text of the option to select
     */
    public void selectFromStateDropDown(String option) {
        try {
            Select stateSelect = findDropDownElement(stateDropDown);
            wait.until(ExpectedConditions.elementToBeClickable(stateDropDown));
            stateSelect.selectByVisibleText(option);
        } catch (NoSuchElementException e) {
            System.out.println("The provided state '" + option + "' is not available.");
        }
    }

    /**
     * Method to select an option from the city dropdown.
     * @param option the visible text of the option to select
     */
    public void selectFromCityDropDown(String option) {
        try {
            Select citySelect = findDropDownElement(cityDropDown);
            wait.until(ExpectedConditions.elementToBeClickable(cityDropDown));
            citySelect.selectByVisibleText(option);
        } catch (NoSuchElementException e) {
            System.out.println("The provided city '" + option + "' is not available.");
        }
    }

    public HomePage submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

    /**
     * Private helper method to find and return a Select dropdown element.
     * @param dropDownLocator the locator of the dropdown
     * @return a Select object for interacting with the dropdown
     */
    private Select findDropDownElement(By dropDownLocator) {
        WebElement dropDown = wait.until(ExpectedConditions.presenceOfElementLocated(dropDownLocator));
        return new Select(dropDown);
    }

}
