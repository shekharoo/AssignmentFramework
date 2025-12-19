package com.crm.objectRepository_DemoWebShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
    public OrdersPage(){

    }
    public WebDriver driver;
    public OrdersPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//ul[@class='top-menu']/li[2]")
    WebElement computersLink;
    @FindBy(xpath = "//ul[@class='sublist firstLevel active']/li[1]")
    WebElement desktopDropDownLink;
    @FindBy(xpath = "//div[@class='product-grid']/div[1]//input")
    WebElement addToCart1;
    @FindBy(xpath = "//div[@class='add-to-cart']//input[2]")
    WebElement addToCart2;
    @FindBy(xpath = "//p[@class='content']")
    WebElement addToCartMsg;
    @FindBy(xpath = "//span[@class='close']")
    WebElement addToCartMsgClose;
    @FindBy(xpath = "//li[@id='topcartlink']//a")
    WebElement shoppingCart;
    @FindBy(id = "CountryId")
    WebElement selectCountry;
    @FindBy(id = "termsofservice")
    WebElement termsofservice;
    @FindBy(id = "checkout")
    WebElement checkout;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[1]/div/div//input")
    WebElement checkoutContinue1;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[2]/div/div//input")
    WebElement checkoutContinue2;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[3]//descendant::input[4]")
    WebElement checkoutContinue3;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[4]/div/div//input")
    WebElement checkoutContinue4;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[5]/div/div//input")
    WebElement checkoutContinue5;
    @FindBy(xpath = "//ol[@id='checkout-steps']/li[6]/div/div//input")
    WebElement confirmButton;
    @FindBy(xpath = "//div[@class='title']")
    WebElement OrderPlacedConfirmMessage;
    public WebElement getComputersLink() {
        return computersLink;
    }

    public WebElement getDesktopDropDownLink() {
        return desktopDropDownLink;
    }

    public WebElement getAddToCart1() {
        return addToCart1;
    }
    public WebElement getAddToCart2() {
        return addToCart2;
    }

    public WebElement getAddToCartMsg() {
        return addToCartMsg;
    }
    public WebElement getAddToCartMsgClose() {
        return addToCartMsgClose;
    }


    public WebElement getShoppingCart() {
        return shoppingCart;
    }

    public WebElement getSelectCountry() {
        return selectCountry;
    }

    public WebElement getTermsofservice() {
        return termsofservice;
    }

    public WebElement getCheckout() {
        return checkout;
    }

    public WebElement getCheckoutContinue1() {
        return checkoutContinue1;
    }

    public WebElement getCheckoutContinue2() {
        return checkoutContinue2;
    }

    public WebElement getCheckoutContinue3() {
        return checkoutContinue3;
    }

    public WebElement getCheckoutContinue4() {
        return checkoutContinue4;
    }

    public WebElement getCheckoutContinue5() {
        return checkoutContinue5;
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public WebElement getOrderPlacedConfirmMessage() {
        return OrderPlacedConfirmMessage;
    }




}
