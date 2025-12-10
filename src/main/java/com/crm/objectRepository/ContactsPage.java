package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
    WebDriver driver=null;
    public ContactsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@class='btn btn-info']")
    WebElement createContactButton;


    @FindBy(xpath = "//select[@class='form-control']/option[1]")
    WebElement searchContactByID;
    @FindBy(xpath = "//select[@class='form-control']/option[2]")
    WebElement searchContactByName;

    @FindBy(xpath="//input[@class='form-control']")
    WebElement contactSearchBox;
    @FindBy(xpath="//table[@class='table table-striped table-hover']//tbody/tr/td[1]")
    WebElement contactIdTableDisplay;
    @FindBy(xpath="//table[@class='table table-striped table-hover']//tbody/tr/td[2]")
    WebElement contactNameTableDisplay;

    public WebElement getcreateContactButton() {
        return createContactButton;
    }
    public WebElement getSearchContactByID() {
        return searchContactByID;
    }

    public WebElement getSearchContactByName() {
        return searchContactByName;
    }
    public WebElement getContactSearchBox() {
        return contactSearchBox;
    }

    public WebElement getContactIdTableDisplay() {
        return contactIdTableDisplay;
    }

    public WebElement getContactNameTableDisplay() {
        return contactNameTableDisplay;
    }

}
