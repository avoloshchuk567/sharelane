package signUpTesting;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import utils.DriverSetUp;
import org.testng.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class VerifySignUpLink extends DriverSetUp {

    @Test
    public void verifySignUpLinkPresent() {
        driver.findElement(By.xpath("//a[text()='Sign up']")).isDisplayed();
    }

    @Test
    public void verifySignUpLinkCorrectTitle(){
        String signUpLink = driver.findElement(By.xpath("//a[text()='Sign up']")).getText();
        assertThat(signUpLink, equalTo("Sign up"));
    }
}
