package pageObjectTests;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjectClasses.CreatedUserAccount;
import pageObjectClasses.LoginPage;
import pageObjectClasses.MainPage;
import pageObjectClasses.SignUpEnterZip;
import utils.DriverSetUp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainPageTest extends DriverSetUp {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(DriverSetUp.class);
    private CreatedUserAccount createdUser;
    private String createdUserEmail;
    private String createdUserPassword;

    @Test
    @Step()
    public void createTestUser() {
        MainPage mainPage = new MainPage(driver);
        createdUser = mainPage.clickSignUpLink().continueRegistration("12345")
                .register("John", "Smith", "john.smith@test.com", "12345", "12345");
        createdUserEmail = createdUser.getUserAccountEmail();
        createdUserPassword = createdUser.getUserAccountPassword();
        createdUser.returnHomePage();
        LOGGER.info("User is created with email = {}, password = {}", createdUserEmail, createdUserPassword);
    }

    @Test
    public void checkEmailTextIsCorrectTest(){
        MainPage mainPage = new MainPage(driver);
        String emailText = mainPage.getEmailFieldText();
        assertThat(emailText, equalTo("Email"));
    }

    @Test
    public void checkPasswordTextIsCorrectTest(){
        MainPage mainPage = new MainPage(driver);
        String passwordText = mainPage.getPasswordFieldText();
        assertThat(passwordText, equalTo("Password"));
    }

    @DataProvider
    public Object[][] loginFailData() {
        return new Object[][]{
                {"", "", "checkLoginWithEmptyFields"},
                {createdUserEmail, "", "checkLoginWithEmptyPassword"},
                {"", createdUserPassword, "checkLoginWithEmptyEmail"},
                {"john.smith@test.com", createdUserPassword, "checkLoginWithIncorrectEmail"},
                {createdUserEmail, "12345", "checkLoginWithIncorrectPassword"},
        };
    }

    @Test(dataProvider = "loginFailData", dependsOnMethods = "createTestUser")
    public void loginFailTest(String email, String password, String testDescription) {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.loginIntoAccount(email, password);
        String errorMessage = loginPage.getErrorMessageText();
        assertThat(errorMessage, equalTo("Oops, error. Email and/or password don't match our records"));
    }

    @Test
    public void itsPossibleToSignUpTest(){
        MainPage mainPage = new MainPage(driver);
        SignUpEnterZip signUpEnterZip = mainPage.clickSignUpLink();
        assertThat(signUpEnterZip.getSignUpText(), equalTo("Sign Up"));

    }

    @Test(dependsOnMethods = "createTestUser")
    public void successfulLoginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.loginIntoAccount(createdUserEmail, createdUserPassword);
        String helloMessage = mainPage.getHelloText();
        assertThat(helloMessage, containsString("Hello"));
    }
}
