package signUpTesting;

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

    @Test()
    public void verifySignUpLinkPresent() {
        LOGGER.info("Test: verifySignUpLinkPresent");
        driver.findElement(By.xpath("//a[text()='Sign up']")).isDisplayed();
    }

    @Test
    public void verifySignUpLinkCorrectTitle() {
        LOGGER.info("Test: verifySignUpLinkCorrectTitle");
        String signUpLink = driver.findElement(By.xpath("//a[text()='Sign up']")).getText();
        assertThat(signUpLink, equalTo("Sign up"));
    }
}