package pageObjectClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//p[text()='Email ']")
    private WebElement emailField;
    @FindBy(css = "input[name=email]")
    private WebElement emailFieldInput;
    @FindBy(xpath = "//p[text()='Password ']")
    private WebElement passwordField;
    @FindBy(css = "input[name=password]")
    private WebElement passwordFieldInput;
    @FindBy(css = "input[value=Login]")
    private WebElement loginButton;
    @FindBy(css = "a[href*=\"register\"]")
    private WebElement signUpLink;

    public MainPage typeEmail(String email) {
        emailFieldInput.sendKeys(email);
        return this;
    }

    public MainPage typePassword(String password) {
        passwordFieldInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(String email, String password) {
        this.typeEmail(email);
        this.typePassword(password);
        loginButton.click();
        return new LoginPage(driver);
    }

    public SignUpEnterZip clickSignUpLink() {
        signUpLink.click();
        return new SignUpEnterZip(driver);
    }
}
