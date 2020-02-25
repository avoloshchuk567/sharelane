package pageObjectClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SignUpEnterZip {
    WebDriver driver;

    public SignUpEnterZip(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//p[text()='ZIP code* ']")
    private WebElement zipField;
    @FindBy(css = "input[name=zip_code]")
    private WebElement zipFieldInput;
    @FindBy(css = "input[value='Continue']")
    private WebElement continueButton;

    public SignUpEnterZip typeZip(String zip) {
        zipField.sendKeys(zip);
        return this;
    }

    public SignUpRegister continueRegistration(String zip) {
        this.typeZip(zip);
        return new SignUpRegister(driver);
    }
}
