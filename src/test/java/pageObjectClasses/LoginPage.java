package pageObjectClasses;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".error_message")
    private WebElement errorMessageText;

    @Step("Get error message text: \"Oops, error. Email and/or password don't match our records\" ")
    public String getErrorMessageText() {
        return errorMessageText.getText();
    }
}
