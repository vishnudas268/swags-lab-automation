package org.qa.swagslabautomation.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

import org.qa.swagslabautomation.utilites.TestUtilities;

public class TestBase extends TestUtilities
{
    public static WebDriver driver;
    public static Properties prop;


    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/java/org/qa/swagslabautomation/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void initialization() {
        switch (prop.getProperty("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                ChromeOptions options = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                break;
            case "fireFox":
                driver = new FirefoxDriver();
                break;
            case "edgeBrowser":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser specified in configuration!");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_WAIT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.get(prop.getProperty("liveUrl"));

    }

    public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        ObjectMapper map = new ObjectMapper();
        return map.readValue(jsonContent,
                new TypeReference<>() {
                });
    }

    public void preventGooglePopup(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }
}
