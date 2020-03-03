package pageObjectClasses;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(MainPage.class);
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] takeElementScreenshotPNG(WebElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] takeWholePageScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
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
    @FindBy(css = "span[class ='user']")
    private WebElement helloText;

    @Step("Entered email: {}")
    public MainPage typeEmail(String email) {
        emailFieldInput.sendKeys(email);
        return this;
    }

    @Step("Entered password: {}")
    public MainPage typePassword(String password) {
        passwordFieldInput.sendKeys(password);
        return this;
    }

    @Step("Click Login button")
    public void clickLogInButton() {
        loginButton.click();
    }

    public LoginPage loginIntoAccount(String email, String password) {
        this.typeEmail(email);
        this.typePassword(password);
        takeWholePageScreenshotPNG(driver);
        LOGGER.debug("Email: {}, Password: {}", email, password);
        this.clickLogInButton();
        return new LoginPage(driver);
    }

    public SignUpEnterZip clickSignUpLink() {
        signUpLink.click();
        LOGGER.debug("Sign Up link is clicked");
        return new SignUpEnterZip(driver);
    }

    public String getEmailFieldText() {
        takeElementScreenshotPNG(emailField);
        return emailField.getText();
    }

    public String getPasswordFieldText() {
        takeElementScreenshotPNG(passwordField);
        return passwordField.getText();
    }

    public String getHelloText() {
        return helloText.getText();
    }
}
