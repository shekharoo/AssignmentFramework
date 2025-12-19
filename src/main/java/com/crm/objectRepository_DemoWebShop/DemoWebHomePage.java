package com.crm.objectRepository_DemoWebShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoWebHomePage {
    public DemoWebHomePage(){

    }
    public WebDriver driver;
    public DemoWebHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(linkText="My account")
    WebElement myAccount;
    @FindBy(linkText = "Addresses")
    WebElement addressMenu;
    @FindBy(xpath="//input[@type='button']")
    WebElement addNewAddress;
    public WebElement getMyAccount() {
        return myAccount;
    }

    public WebElement getAddressMenu() {
        return addressMenu;
    }
    public WebElement getAddNewAddress() {
        return addNewAddress;
    }



}
