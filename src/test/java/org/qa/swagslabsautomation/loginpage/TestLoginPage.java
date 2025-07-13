package org.qa.swagslabsautomation.loginpage;

import org.qa.swagslabautomation.base.TestBase;
import org.qa.swagslabautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLoginPage extends TestBase {

    LoginPage loginpage;

    @BeforeMethod
    public void openSwagsLab()
    {
        initialization();
        loginpage = new LoginPage();
    }

    /*
    username:standard_user
    password: secret_sauce
     */
    @Test
    public void testLoginWithValidCredentials() {
        loginpage.userLogin("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "User should stay on login page with invalid credentials");
    }

    /*
   username:standard_use
   password: secret_sauc
    */
    @Test
    public void testLoginWithInvalidCredentials() {
        loginpage.userLogin("standard_use", "secret_sauc");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/",
                "User should stay on login page with invalid credentials");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
