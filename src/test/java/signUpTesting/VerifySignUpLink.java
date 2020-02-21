package signUpTesting;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import utils.DriverSetUp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class VerifySignUpLink extends DriverSetUp {

    @Test(description = "Verification of 'Sign up' link presence")
    @Description ("Test description: check that 'Sign up' link is present")
    @Step("Check that 'Sign up' link is present")
    public void verifySignUpLinkPresent() {
        driver.findElement(By.xpath("//a[text()='Sign up']")).isDisplayed();
    }

    @Test(description = "Verification of 'Sign up' link title correctness", dependsOnMethods = {"verifySignUpLinkPresent"})
    @Description("Test description: check that 'Sign up' title is correct")
    @Step("Check that 'Sign up' link title is correct")
    public void verifySignUpLinkCorrectTitle() {
        String signUpLink = driver.findElement(By.xpath("//a[text()='Sign up']")).getText();
        assertThat(signUpLink, equalTo("Sign up"));
    }
}
