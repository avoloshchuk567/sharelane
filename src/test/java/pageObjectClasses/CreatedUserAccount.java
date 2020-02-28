package pageObjectClasses;

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

    public String getUserAccountEmail() {
        return userAccountEmail.getText();
    }

    public String getUserAccountPassword() {
        return userAccountPassword.getText();
    }

    public String getSuccessfulRegisterText() {
        return confirmationMessage.getText();
    }

    public void returnHomePage() {
        homePage.click();
    }
}
