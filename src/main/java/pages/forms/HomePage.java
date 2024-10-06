package pages.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By welcomeMessage = By.id("welcome-message");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessfulMessage(){
        return driver.findElement(welcomeMessage).getText();
    }

    public boolean isWelcomeMessageDisplayed(){
        return driver.findElement(welcomeMessage).isDisplayed();
    }
}
