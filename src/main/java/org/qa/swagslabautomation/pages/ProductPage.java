package org.qa.swagslabautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.swagslabautomation.base.TestBase;

import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends TestBase {

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".shopping_cart_link")
    WebElement cartLink;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement sortContainer;

    public void openProductDetails(String productName){
        driver.findElement(By.xpath("//div[@class='inventory_item_name '][text()='"+productName+"']")).click();
    }

    public void addToCart(String productName){
        driver.findElement(By.xpath("//div[@class='inventory_item'][.//div[text()='"+productName+"']]" +
                "//button[contains(text(),'Add to cart')]")).click();
    }

    public void removeFromCart(String productName){
        driver.findElement(By.xpath("//div[@class='inventory_item'][.//div[text()='"+productName+"']]" +
                "//button[contains(text(),'Remove')]")).click();
    }

    public CartPage clickCartButton(){
        cartLink.click();
        return new CartPage();
    }

    public void filterProductList(String filterOption){
        selectFromDropdown(sortContainer,filterOption);
    }

    public List<String> getProductList() {
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        return productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
