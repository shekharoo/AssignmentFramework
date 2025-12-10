package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignsPage {
    public CreateCampaignsPage(){

    }
    public WebDriver driver=null;
    public CreateCampaignsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@id='navbarNav']/ul/li[1]/a")
    WebElement campaignLink;

    @FindBy(name="campaignName")
    WebElement campaignName;

    @FindBy(name="targetSize")
    WebElement targetSize;
    @FindBy(name="expectedCloseDate")
    WebElement expectedCloseDate;
    @FindBy(name="targetAudience")
    WebElement targetAudience;
    @FindBy(xpath="//textarea[@name='description']")
    WebElement description;


    @FindBy(xpath="//div[@class='Toastify']/div/div/div[1]")
    WebElement popUp;
    @FindBy(xpath="//button[@type='submit']")
    WebElement createCampaignSubmitButton;

    @FindBy(xpath="//button[@aria-label='close']")
    WebElement closePopUp;


    public WebElement getCampaignLink() {
        return campaignLink;
    }

    public WebElement getCampaignName() {
        return campaignName;
    }

    public WebElement getTargetSize() {
        return targetSize;
    }

    public WebElement getExpectedCloseDate() {
        return expectedCloseDate;
    }

    public WebElement getPopUp() {
        return popUp;
    }
    public WebElement getClosePopUp() {
        return closePopUp;
    }
    public WebElement getCreateCampaignSubmitButton() {
        return createCampaignSubmitButton;
    }

    public void createCampaignWithMandatoryDetails(String campName,String trgtSize)
    {
        //Enter Campaign Name
        campaignName.sendKeys(campName);
        targetSize.sendKeys(trgtSize);
        createCampaignSubmitButton.click();
    }
    public void createCampaignWithClosingDate(String campName,String trgtSize,String expCloseDate)
    {

        //Enter Campaign Name
        campaignName.sendKeys(campName);
        expectedCloseDate.sendKeys(expCloseDate);
        createCampaignSubmitButton.click();
    }
    public void createCampaignWithTargetAudience(String campName,String trgtSize,String trgtAudience,String desc)
    {
        //Enter Campaign Name
        campaignName.sendKeys(campName);
        targetSize.sendKeys(trgtSize);
        targetAudience.sendKeys(trgtAudience);
        description.sendKeys(desc);
        createCampaignSubmitButton.click();
    }


}
