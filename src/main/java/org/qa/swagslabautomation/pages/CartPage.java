package org.qa.swagslabautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.swagslabautomation.base.TestBase;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends TestBase {

    public CartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkoutButton;

    public CheckoutPage checkOutAction(){
        pageLoadWait(driver);
        checkoutButton.click();
        return new CheckoutPage();
    }

    public String checkCartProductNames(){
        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cart_item']" +
                "//div[@class='inventory_item_name']"));

        List<String> productNames = cartProducts.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return String.join(",", productNames);
    }


}


