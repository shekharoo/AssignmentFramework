package com.crm.objectRepository_DemoWebShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressPage {

    WebDriver driver=null;
    public AddressPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="Address_FirstName")
    WebElement Address_FirstName;
    @FindBy(id="Address_LastName")
    WebElement Address_LastName;
    @FindBy(id="Address_Email")
    WebElement Address_Email;
    @FindBy(id="Address_CountryId")
    WebElement Address_CountryId;
    @FindBy(id="Address_City")
    WebElement Address_City;
    @FindBy(id="Address_Address1")
    WebElement Address_Address1;
    @FindBy(id="Address_ZipPostalCode")
    WebElement Address_ZipPostalCode;
    @FindBy(id="Address_PhoneNumber")
    WebElement Address_PhoneNumber;
    @FindBy(xpath="//div[@class='buttons']//input")
    WebElement Address_Save;
    @FindBy(xpath="//div[@class='page-title']")
    WebElement Address_Created;



    public WebElement getAddress_FirstName() {
        return Address_FirstName;
    }

    public WebElement getAddress_LastName() {
        return Address_LastName;
    }

    public WebElement getAddress_Email() {
        return Address_Email;
    }

    public WebElement getAddress_CountryId() {
        return Address_CountryId;
    }

    public WebElement getAddress_City() {
        return Address_City;
    }

    public WebElement getAddress_Address1() {
        return Address_Address1;
    }

    public WebElement getAddress_ZipPostalCode() {
        return Address_ZipPostalCode;
    }

    public WebElement getAddress_PhoneNumber() {
        return Address_PhoneNumber;
    }
    public WebElement getAddress_AddressSave() {
        return Address_Save;
    }
    public WebElement getAddress_AddressCreated() {
        return Address_Created;
    }


}
