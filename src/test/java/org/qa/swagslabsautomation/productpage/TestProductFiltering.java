package org.qa.swagslabsautomation.productpage;

import org.qa.swagslabautomation.base.TestBase;
import org.qa.swagslabautomation.pages.LoginPage;
import org.qa.swagslabautomation.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class TestProductFiltering extends TestBase {

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
    filterOption:Name (A to Z)
    expectedList:Sauce Labs Backpack,Sauce Labs Bike Light,Sauce Labs Bolt T-Shirt,
    Sauce Labs Fleece Jacket,Sauce Labs Onesie,Test.allTheThings() T-Shirt (Red)
     */
    @Test
    public void verifyFilterAtoZ(){
        loginpage.userLogin("standard_user", "secret_sauce");
        ProductPage productpage = new ProductPage();
        productpage.filterProductList("Name (A to Z)");
        List<String> actualList=productpage.getProductList();
        List<String> expectedList = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals(actualList.get(i), expectedList.get(i),
                    "Mismatch at position " + (i + 1));
        }
        System.out.println("Product order A to Z verified successfully.");
    }

    /*
    username:standard_user
    password: secret_sauce
    filterOption:Name (Z to A)
    expectedList:Test.allTheThings() T-Shirt (Red),Sauce Labs Onesie,Sauce Labs Fleece Jacket,
    Sauce Labs Bolt T-Shirt,Sauce Labs Bike Light,Sauce Labs Backpack
     */
    @Test
    public void verifyFilterZtoA(){
        loginpage.userLogin("standard_user", "secret_sauce");
        ProductPage productpage = new ProductPage();
        productpage.filterProductList("Name (Z to A)");
        List<String> actualList=productpage.getProductList();
        List<String> expectedList = Arrays.asList(
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Onesie",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Bike Light",
                "Sauce Labs Backpack"
        );
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals(actualList.get(i), expectedList.get(i),
                    "Mismatch at position " + (i + 1));
        }
        System.out.println("Product order Z to A verified successfully.");
    }

    /*
    username:standard_user
    password: secret_sauce
    filterOption:Price (low to high)
    expectedList:Sauce Labs Onesie,Sauce Labs Bike Light,Sauce Labs Bolt T-Shirt,
    Test.allTheThings() T-Shirt (Red),Sauce Labs Backpack,Sauce Labs Fleece Jacket
     */
    @Test
    public void verifyFilterPriceLowToHigh(){
        loginpage.userLogin("standard_user", "secret_sauce");
        ProductPage productpage = new ProductPage();
        productpage.filterProductList("Price (low to high)");
        List<String> actualList=productpage.getProductList();
        List<String> expectedList = Arrays.asList(
                "Sauce Labs Onesie",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Backpack",
                "Sauce Labs Fleece Jacket"
        );
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals(actualList.get(i), expectedList.get(i),
                    "Mismatch at position " + (i + 1));
        }
        System.out.println("Product order Low to High Price verified successfully.");
    }

    /*
    username:standard_user
    password: secret_sauce
    filterOption:Price (high to low)
    expectedList:Sauce Labs Fleece Jacket,Sauce Labs Backpack,Sauce Labs Bolt T-Shirt,
    Test.allTheThings() T-Shirt (Red),Sauce Labs Bike Light,Sauce Labs Onesie
     */
    @Test
    public void verifyFilterPriceHighToLow(){
        loginpage.userLogin("standard_user", "secret_sauce");
        ProductPage productpage = new ProductPage();
        productpage.filterProductList("Price (high to low)");
        List<String> actualList=productpage.getProductList();
        List<String> expectedList = Arrays.asList(
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Backpack",
                "Sauce Labs Bolt T-Shirt",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Bike Light",
                "Sauce Labs Onesie"
        );
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals(actualList.get(i), expectedList.get(i),
                    "Mismatch at position " + (i + 1));
        }
        System.out.println("Product order High to Low Price verified successfully.");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
