package org.qa.swagslabautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.swagslabautomation.base.TestBase;

public class CheckoutPage extends TestBase {

    public CheckoutPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@id='postal-code']" )
    WebElement postalCodeField;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueButton;

    @FindBy(xpath = "(//div[@class='summary_value_label'])[1]")
    WebElement paymentInformationLabel;

    @FindBy(xpath = "(//div[@class='summary_value_label'])[2]")
    WebElement shippingInformationLabel;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement itemTotalLabel;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxTotalLabel;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    WebElement totalLabel;

    @FindBy(xpath = "//button[text()='Finish']")
    WebElement finishButton;

    public void enterUserDetails(String firstName,String lastName,String pinCode){
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(pinCode);
        continueButton.click();
    }

    public String storePaymentInformation(){
        return paymentInformationLabel.getText();
    }

    public String storeShippingInformation(){
        return shippingInformationLabel.getText();
    }

    public String ItemPrice(){
        return itemTotalLabel.getText().replace("Item total: $", "");
    }

    public String taxPrice(){
        return taxTotalLabel.getText().replace("Tax: $", "");
    }

    public String totalPrice(){
        return totalLabel.getText().replace("Total: $", "");
    }

    public void finishProcess(){
        pageLoadWait(driver);
        finishButton.click();
        if(driver.findElement(By.xpath("//h2")).isDisplayed()){
            System.out.println("\n***Product Buying Process Completed Successfully***");
        }
    }




}
