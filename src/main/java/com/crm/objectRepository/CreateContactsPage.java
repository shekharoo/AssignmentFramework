package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage {
    WebDriver driver=null;
    public CreateContactsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@id='navbarNav']/ul/li[2]/a")
    WebElement contactsLink;

    @FindBy(name="organizationName")
    WebElement organizationName;

    @FindBy(name="title")
    WebElement title;
    @FindBy(name="contactName")
    WebElement contactName;
    @FindBy(name="mobile")
    WebElement mobile;
    @FindBy(xpath="//div[@class='form-container']/div[2]/div[4]//button")
    WebElement selectcampaignSubmitButton;
    @FindBy(xpath="//select[@id='search-criteria']/option[1]")
    WebElement selectCampaignIdDropDown;
    @FindBy(id="search-input")
    WebElement searchCampaignIdSearchBox;
    @FindBy(xpath="//button[@class='select-btn']")
    WebElement selectCampaignButtontable;
    @FindBy(xpath="//button[@type='submit']")
    WebElement createContactSubmitButton;
    @FindBy(xpath="//div[@class='Toastify']/div/div/div")
    WebElement popUp;
    public WebElement getContactLink() {
        return contactsLink;
    }

    public WebElement getOrganizationName() {
        return organizationName;
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getContactName() {
        return contactName;
    }

    public WebElement getMobile() {
        return mobile;
    }

    public WebElement getSelectcampaignSubmitButton() {
        return selectcampaignSubmitButton;
    }

    public WebElement getSelectCampaignIdDropDown() {
        return selectCampaignIdDropDown;
    }

    public WebElement getSearchCampaignIdSearchBox() {
        return searchCampaignIdSearchBox;
    }

    public WebElement getSelectCampaignButtontable() {
        return selectCampaignButtontable;
    }

    public WebElement getCreateContactSubmitButton() {
        return createContactSubmitButton;
    }

    public WebElement getPopUp() {
        return popUp;
    }

}
