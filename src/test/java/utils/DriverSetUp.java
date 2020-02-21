package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

public class DriverSetUp {
    protected WebDriver driver;
    private String filePath = new File("src/test/resources/app.properties").getAbsolutePath();
    private Properties appProps = new Properties();
    private static final String MAIN_PAGE = "/cgi-bin/main.py";

    private void loadPropertiesFromFile() {
        try (InputStream input = new FileInputStream(filePath)) {
            appProps.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @BeforeSuite
    public void setChromeDriver() {
        loadPropertiesFromFile();
        System.setProperty(appProps.getProperty("chromeDriver"), new File(appProps.getProperty("chromeDriverPath")).getAbsolutePath());
        driver = new ChromeDriver();
    }

    @BeforeClass
    public void loadMainPage() {
        loadPropertiesFromFile();
        driver.get(appProps.getProperty("endpoint") + MAIN_PAGE);
    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }

}
