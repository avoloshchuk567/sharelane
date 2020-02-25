package signUpTesting;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import utils.DriverSetUp;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class VerifySignUpLink extends DriverSetUp {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(VerifySignUpLink.class);

    @Test(description = "Verification of 'Sign up' link presence")
    @Description ("Test description: check that 'Sign up' link is present")
    @Step("Check that 'Sign up' link is present")
    public void verifySignUpLinkPresent() {
        LOGGER.info("Test: verifySignUpLinkPresent");
        driver.findElement(By.xpath("//a[text()='Sign up']")).isDisplayed();
    }

    @Test(description = "Verification of 'Sign up' link title correctness", dependsOnMethods = {"verifySignUpLinkPresent"})
    @Description("Test description: check that 'Sign up' title is correct")
    @Step("Check that 'Sign up' link title is correct")
    public void verifySignUpLinkCorrectTitle() {
        LOGGER.info("Test: verifySignUpLinkCorrectTitle");
        String signUpLink = driver.findElement(By.xpath("//a[text()='Sign up']")).getText();
        assertThat(signUpLink, equalTo("Sign up"));
    }
}