package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(){

    }
    public WebDriver driver;
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//button[@class='btn btn-info']")
    WebElement createCampaignButton;

    @FindBy(linkText = "Campaigns")
    WebElement campaignLink;
    @FindBy(linkText = "Contacts")
    WebElement contactsLink;
    @FindBy(linkText = "Leads")
    WebElement leadsLink;
    @FindBy(linkText = "Opportunities")
    WebElement opportunitiesLink;
    @FindBy(linkText = "Products")
    WebElement productsLink;
    @FindBy(linkText = "Quotes")
    WebElement quotesLink;
    @FindBy(linkText = "Purchase Order")
    WebElement purchaseOrderLink;
    @FindBy(linkText = "Sales Order")
    WebElement salesOrderLink;
    @FindBy(linkText = "Invoice")
    WebElement invoiceLink;
    @FindBy(linkText = "Admin Console")
    WebElement adminConsoleLink;
    @FindBy(xpath = "//div[@class='user-icon']")
    WebElement logOutIcon;
    @FindBy(xpath = "//div[@class='dropdown-item logout']")
    WebElement logOutLink;

    public WebElement getCreateCampaignButton() {
        return createCampaignButton;
    }

    public WebElement getCampaignLink() {
        return campaignLink;
    }

    public WebElement getContactsLink() {
        return contactsLink;
    }

    public WebElement getLeadsLink() {
        return leadsLink;
    }

    public WebElement getOpportunitiesLink() {
        return opportunitiesLink;
    }

    public WebElement getProductsLink() {
        return productsLink;
    }

    public WebElement getQuotesLink() {
        return quotesLink;
    }

    public WebElement getPurchaseOrderLink() {
        return purchaseOrderLink;
    }

    public WebElement getSalesOrderLink() {
        return salesOrderLink;
    }

    public WebElement getInvoiceLink() {
        return invoiceLink;
    }

    public WebElement getAdminConsoleLink() {
        return adminConsoleLink;
    }

    public WebElement getLogOutIcon() {
        return logOutIcon;
    }

    public WebElement getLogOutLink() {
        return logOutLink;
    }



}
