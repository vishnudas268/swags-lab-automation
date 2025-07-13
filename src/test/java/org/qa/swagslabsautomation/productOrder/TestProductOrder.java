package org.qa.swagslabsautomation.productOrder;

import org.qa.swagslabautomation.base.TestBase;
import org.qa.swagslabautomation.pages.CartPage;
import org.qa.swagslabautomation.pages.CheckoutPage;
import org.qa.swagslabautomation.pages.LoginPage;
import org.qa.swagslabautomation.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestProductOrder extends TestBase {

    LoginPage loginpage;

    @BeforeMethod
    public void openSwagsLab()
    {
        initialization();
        loginpage = new LoginPage();
    }

    /*
    userName:standard_user
    password: secret_sauce
    productNames: Sauce Labs Backpack,Sauce Labs Bike Light
    firstName:Test
    lastName:QA
    pinCode:657573
     */
    @Test(dataProvider = "productBuyData")
    public void verifyTheProductOrderingProcess(HashMap<String,String>input) throws InterruptedException {
        loginpage.userLogin(input.get("userName"), input.get("password"));
        ProductPage productpage = new ProductPage();
        for(String name : input.get("productNames").split(",")) {
            productpage.addToCart(name.trim());
        }

        CartPage cartpage =productpage.clickCartButton();
        Thread.sleep(3000);
        String actual = cartpage.checkCartProductNames();
        Assert.assertEquals(actual.trim(), input.get("productNames").trim(),
                "Cart product names do not match expected list!");
        CheckoutPage checkoutpage =cartpage.checkOutAction();
        System.out.println("Test is here!");
        Thread.sleep(3000);
        checkoutpage.enterUserDetails(input.get("firstName"),input.get("lastName"),input.get("pinCode"));
        System.out.println("\nPayment Information: "+checkoutpage.storePaymentInformation());
        System.out.println("\nShipping Information: "+checkoutpage.storeShippingInformation());
        Thread.sleep(3000);
        double itemPrice = Double.parseDouble(checkoutpage.ItemPrice());
        double taxPrice = Double.parseDouble(checkoutpage.taxPrice());
        double totalPrice = Double.parseDouble(checkoutpage.totalPrice());
        double expectedTotal = itemPrice + taxPrice;
        Assert.assertEquals(totalPrice, expectedTotal, 0.01, "Total price calculation mismatch!");
        checkoutpage.finishProcess();
    }

    @DataProvider
    public Object[][] productBuyData() throws IOException {
        List<HashMap<String, String>> value = getJsonData(System.getProperty("user.dir")
                + "/src/test/java/org/qa/swagslabsautomation/productOrder/productBuyData.json");
        return new Object[][] { {value.getFirst() } };
    }
}
