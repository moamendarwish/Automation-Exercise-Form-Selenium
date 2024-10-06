package register;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.forms.HomePage;

import static org.testng.Assert.assertTrue;

public class RegisterTests extends BaseTest {
    /**
     * Test method for registering a user using data from the DataProvider.
     * The test method is linked to the DataProvider using the @Test annotation.
     *
     * @param name         the user's name
     * @param email        the user's email
     * @param gender       the user's gender
     * @param mobileNumber the user's mobile number
     * @param dateOfBirth  the user's date of birth
     * @param subject      the subject to be entered
     * @param hobby        the hobby to be selected
     * @param picturePath  the path to the picture to upload
     * @param address      the user's current address
     * @param state        the state to select from the dropdown
     * @param city         the city to select from the dropdown
     */
    @Test(dataProvider = "registerData")
    public void testRegister(String name, String email, String gender, String mobileNumber, String dateOfBirth,
                             String subject, String hobby, String picturePath, String address, String state, String city) {

        // Fill out the form with the provided data
        practiceForm.setName(name);
        practiceForm.setEmail(email);
        practiceForm.selectGenderByLabel(gender);
        practiceForm.setMobileNumber(mobileNumber);
        practiceForm.setDateOfBirth(dateOfBirth);
        practiceForm.setSubjectField(subject);
        practiceForm.selectHobbyByLabel(hobby);
        practiceForm.uploadPicture(System.getProperty("user.dir") + picturePath);
        practiceForm.setCurrentAddress(address);
        practiceForm.selectFromStateDropDown(state);
        practiceForm.selectFromCityDropDown(city);

        // Submit the form
        HomePage homePage = practiceForm.submitForm();

        // Assert that the welcome message is displayed on the HomePage
//        assertTrue(homePage.isWelcomeMessageDisplayed(),
//                "The welcome message should be displayed after successful form submission.");
    }

    /**
     * Data Provider that supplies test data to the testRegister method.
     */
    @DataProvider(name = "registerData")
    public Object[][] registerData() {
        return new Object[][]{
                // Each row contains test data for one test case
                {"Momen", "momen@gmail.com", "Male", "01095368311", "01/01/1999", "Automation", "Music",
                        "/src/main/resources/tree-736885_1280.jpg", "Alexandria", "NCR", "Agra"},
                {"Angie", "Angie@gmail.com", "Female", "0123456789", "05/05/1995", "QA Testing",
                        "Sports", "/src/main/resources/sample.jpg", "Downtown", "Uttar Pradesh", "Lucknow"}
        };
    }
}