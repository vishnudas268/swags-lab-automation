package org.qa.swagslabautomation.utilites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestUtilities {

    public static final long PAGE_LOAD_WAIT = 30;
    public static final long IMPLICIT_WAIT = 30;

    public static void buttonClick(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void pageLoadWait(WebDriver driver){
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_WAIT, TimeUnit.SECONDS);
    }

    public static void selectFromDropdown(WebElement element,String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

}
