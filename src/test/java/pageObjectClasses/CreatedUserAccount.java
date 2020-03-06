package pageObjectClasses;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class CreatedUserAccount {
    WebDriver driver;

    public CreatedUserAccount(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "table[border='1'] > tbody > tr:first-child > td:last-child > b ")
    private WebElement userAccountEmail;
    @FindBy(css = "table[border='1'] > tbody > tr:last-child > td:last-child")
    private WebElement userAccountPassword;
    @FindBy(css = "span[class='confirmation_message']")
    private WebElement confirmationMessage;
    @FindBy(css = "a[href*='./main.py']")
    private WebElement homePage;

    @Step("User Email is created ")
    public String getUserAccountEmail() {
        return userAccountEmail.getText();
    }

    @Step("User Password is created ")
    public String getUserAccountPassword() {
        return userAccountPassword.getText();
    }

    @Step("Get \"Account is created!\" text")
    public String getSuccessfulRegisterText() {
        return confirmationMessage.getText();
    }

    @Step("Return to Home page")
    public void returnHomePage() {
        homePage.click();
    }
}