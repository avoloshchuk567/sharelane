package pageObjectClasses;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpEnterZip {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpRegister.class);
    WebDriver driver;

    public SignUpEnterZip(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()='ZIP code* ']")
    private WebElement zipField;
    @FindBy(css = "input[name=zip_code]")
    private WebElement zipFieldInput;
    @FindBy(css = "input[value='Continue']")
    private WebElement continueButton;
    @FindBy(css = "tr[class='grey_bg'] > td > p > b")
    private WebElement signUpText;

    @Step("Sign up with zip: {0}")
    public SignUpEnterZip typeZip(String zip) {
        zipFieldInput.sendKeys(zip);
        return this;
    }

    @Step("Click Continue button")
    public void clickContinueButton() {
        continueButton.click();
    }

    public SignUpRegister continueRegistration(String zip) {
        this.typeZip(zip);
        this.clickContinueButton();
        LOGGER.debug("ZIP code {}", zip);
        return new SignUpRegister(driver);
    }

    public String getSignUpText() {
        return signUpText.getText();
    }
}
