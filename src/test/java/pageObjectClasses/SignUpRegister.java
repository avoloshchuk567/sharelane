package pageObjectClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpRegister {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpRegister.class);
    WebDriver driver;

    public SignUpRegister(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()='First Name* ']")
    private WebElement firstNameField;
    @FindBy(css = "input[name=first_name]")
    private WebElement firstNameFieldInput;
    @FindBy(xpath = "//p[text()='Last Name ']")
    private WebElement lastNameField;
    @FindBy(css = "input[name=last_name]")
    private WebElement lastNameFieldInput;
    @FindBy(xpath = "//p[text()='Email* ']")
    private WebElement emailField;
    @FindBy(css = "input[name=email]")
    private WebElement emailFieldInput;
    @FindBy(xpath = "//p[text()='Password* ']")
    private WebElement passwordField;
    @FindBy(css = "input[name=password1]")
    private WebElement passwordFieldInput;
    @FindBy(xpath = "//p[text()='Confirm Password* ']")
    private WebElement confirmPasswordField;
    @FindBy(css = "input[name=password2]")
    private WebElement confirmPasswordFieldInput;
    @FindBy(css = "input[value=Register]")
    private WebElement registerButton;

    public SignUpRegister typeFirstName(String firstName) {
        firstNameFieldInput.sendKeys(firstName);
        return this;
    }

    public SignUpRegister typeLastName(String lastName) {
        lastNameFieldInput.sendKeys(lastName);
        return this;
    }

    public SignUpRegister typeEmail(String email) {
        emailFieldInput.sendKeys(email);
        return this;
    }

    public SignUpRegister typePassword(String password1) {
        passwordFieldInput.sendKeys(password1);
        return this;
    }

    public SignUpRegister typeConfirmPassword(String password2) {
        confirmPasswordFieldInput.sendKeys(password2);
        return this;
    }

    public void clickRegisterButton(){
        registerButton.click();
    }

    public CreatedUserAccount register(String firstName, String lastName, String email, String password1, String password2) {
        this.typeFirstName(firstName);
        this.typeLastName(lastName);
        this.typeEmail(email);
        this.typePassword(password1);
        this.typeConfirmPassword(password2);
        this.clickRegisterButton();
        LOGGER.debug("FirstName {}, LastName {}, Email {}, Password {}, ConfirmPassword {}", firstName, lastName, email, password1, password2);
        return new CreatedUserAccount(driver);
    }
}