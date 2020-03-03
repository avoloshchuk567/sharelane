package pageObjectTests;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pageObjectClasses.*;
import utils.DriverSetUp;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MainPageTest extends DriverSetUp {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(MainPageTest.class);
    private CreatedUserAccount createdUser;
    private String createdUserEmail;
    private String createdUserPassword;
    private MainPage mainPage;

    @Attachment(value = "Description {txt}", type = "text/plain", fileExtension = ".txt")
    public String attachTxt(String txt) {
        return txt;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public byte[] takeWholePageScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Test user is created")
    public void createTestUser() {
        mainPage = loadMainPage();
        createdUser = mainPage.clickSignUpLink().continueRegistration("12345")
                .register("John", "Smith", "john.smith@test.com", "12345", "12345");
        createdUserEmail = createdUser.getUserAccountEmail();
        createdUserPassword = createdUser.getUserAccountPassword();
        attachTxt("Created user email: " + createdUserEmail + " \n" + "Created user password: " + createdUserPassword);
        takeWholePageScreenshotPNG(driver);
        LOGGER.info("User is created with email = {}, password = {}", createdUserEmail, createdUserPassword);
        driver.quit();
    }

    @Test(description = "Test of Email text on Main page")
    @Description("Test description: check that email text is correct")
    public void checkEmailTextIsCorrectTest() {
        mainPage = loadMainPage();
        String emailText = mainPage.getEmailFieldText();
        assertThat(emailText, equalTo("Email"));
    }

    @Test(description = "Test of Password text on Main page")
    @Description("Test description: check that password text is correct")
    public void checkPasswordTextIsCorrectTest() {
        mainPage = loadMainPage();
        String passwordText = mainPage.getPasswordFieldText();
        assertThat(passwordText, equalTo("Password"));
    }

    @DataProvider
    public Object[][] loginFailData() {
        this.createTestUser();
        return new Object[][]{
                {"", "", "checkLoginWithEmptyFields"},
                {createdUserEmail, "", "checkLoginWithEmptyPassword"},
                {"", createdUserPassword, "checkLoginWithEmptyEmail"},
                {"john.smith@test.com", createdUserPassword, "checkLoginWithIncorrectEmail"},
                {createdUserEmail, "12345", "checkLoginWithIncorrectPassword"},
        };
    }

    @Test(dataProvider = "loginFailData", description = "Invalid login test")
    @Description("Test description: it's impossible to login with invalid data")
    public void loginFailTest(String email, String password, String testDescription) {
        mainPage = loadMainPage();
        LoginPage loginPage = mainPage.loginIntoAccount(email, password);
        takeWholePageScreenshotPNG(driver);
        LOGGER.debug("Invalid login with email: {}, password: {}", email, password);
        String errorMessage = loginPage.getErrorMessageText();
        assertThat(errorMessage, equalTo("Oops, error. Email and/or password don't match our records"));
    }

    @Test(description = "Sign up link works correctly")
    @Description("Test description: check that it's possible to sign up")
    public void itsPossibleToSignUpTest() {
        mainPage = loadMainPage();
        SignUpEnterZip signUpEnterZip = mainPage.clickSignUpLink();
        assertThat(signUpEnterZip.getSignUpText(), equalTo("Sign Up"));
    }

    @Test(description = "Valid login test")
    @Description("Test description: it's possible to login with valid data")
    public void successfulLoginTest() {
        this.createTestUser();
        //attach   > add text attachment
        mainPage = loadMainPage();
        mainPage.loginIntoAccount(createdUserEmail, createdUserPassword);
        LOGGER.debug("Logged into account with email: {}, password: {}", createdUserEmail, createdUserPassword);
        String helloMessage = mainPage.getHelloText();
        takeWholePageScreenshotPNG(driver);
        assertThat(helloMessage, containsString("Hello"));
    }
}