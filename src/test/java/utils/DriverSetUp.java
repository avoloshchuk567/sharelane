package utils;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

public class DriverSetUp {
    protected static WebDriver driver;
    private String filePath = "/Users/avoloshchuk/IdeaProjects/com.sharelane/src/test/resources/app.properties";
    private Properties appProps = new Properties();

    public void loadPropertiesFromFile() {
        try (InputStream input = new FileInputStream(filePath)) {
            appProps.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void setChromeDriver() {
        loadPropertiesFromFile();
        System.setProperty(appProps.getProperty("chromeDriver"), appProps.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
    }

    @BeforeClass
    @Step("Open browser")
    public void openBrowser() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterClass
    @Step("Close browser")
    public void quitBrowser() {
        driver.quit();
    }


}
