package utils;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

public class DriverSetUp {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(DriverSetUp.class);
    private static final String MAIN_PAGE = "/cgi-bin/main.py";
    private String relativeFilePath = "src/test/resources/app.properties";
    private String absoluteFilePath = new String();
    private Properties appProps = new Properties();
    protected WebDriver driver;

    private void getAbsoluteFilePath() {
        try {
            absoluteFilePath = new File(relativeFilePath).getAbsolutePath();
        } catch (NullPointerException e) {
            LOGGER.error("Impossible to create absolute file path", e.getMessage());
        }
    }

    private void loadPropertiesFromFile() {
        getAbsoluteFilePath();
        try (InputStream input = new FileInputStream(absoluteFilePath)) {
            appProps.load(input);
        } catch (IOException e) {
            LOGGER.error("File not found", e.getMessage());
        }
    }

    @BeforeSuite
    public void setChromeDriver() {
        //loadPropertiesFromFile();
//            String chromeDriverAbsolutePath = new File(System.getProperty("user.home") + appProps.getProperty("chromeDriverPath")).toString();
//            System.setProperty(appProps.getProperty("chromeDriver"), chromeDriverAbsolutePath);
//            driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeClass
    @Step("Load Main page")
    public void loadMainPage() {
        loadPropertiesFromFile();
        driver.get(appProps.getProperty("endpoint") + MAIN_PAGE);
    }

    @AfterClass
    @Step("Close browser")
    public void quitBrowser() {
        driver.quit();
    }
}