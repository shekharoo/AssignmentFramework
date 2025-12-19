package com.ninzaCrmtestscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.*;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class GroupTest extends BaseClass {
    public CreateCampaignsPage createCampPage =null;
    public HomePage hp = null;
    public ContactsPage contPage = null;
    public CreateContactsPage createContPage = null;
    @DataProvider(parallel = true)
    public Object[][] campaignDetails() throws IOException {
        FileInputStream fis = new FileInputStream(IConstant.excelPath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet(IConstant.excelLoginSheetName);
        int row = sh.getLastRowNum();
        int cell = sh.getRow(row).getLastCellNum();
        Object[][] data = new Object[row][cell];
        DataFormatter value=new DataFormatter();
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<cell;j++)
            {
                Cell cl = sh.getRow(i + 1).getCell(j);
                data[i][j]= value.formatCellValue(cl);
                //data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
            }

        }
        return data;
    }

    public String flowId(WebDriver driver, String flowNameId)
    {
        List<WebElement> listFLows = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[1]"));
        String flowId = listFLows.get(0).getText();
        System.out.println(flowNameId+"Id is captured successfully!");
        return flowId;
    }

    public void verifyPopUpAndCreation(WebDriver driver,String flowName) throws InterruptedException, IOException {
        Thread.sleep(1000);
        WebElement popUp =createCampPage.getPopUp();
        //Get text of popup
        String popUpText = popUp.getText();
        if(popUp.isDisplayed())
        {
            if(popUpText.contains("Successfully Added")) {

                popUpText= JavaUtility.extractTextFromPopUp(popUp,flowName);
                //Store flow Name in Property File
                JavaUtility.writeToPropertyFile(flowName+"Name",popUpText);
                System.out.println(flowName+" name is: "+popUpText);
                System.out.println("Create "+flowName+" is Successful!!");
                //Capture flow ID
                String flowNameId = flowId(driver,flowName);

                System.out.println(flowName+"Id: "+flowNameId);
                //Store flow ID in Property File
                JavaUtility.writeToPropertyFile(flowName+"Id",flowNameId);

            }
            else{
                System.out.println("Create "+flowName+" is not Successful!!");
            }
        }
        else {
            System.out.println("Create "+flowName+" pop up is not displayed");
        }

    }
    @Test(dataProvider = "campaignDetails",groups = {"Smoke"},retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void createCampaignWithMandatoryDetails(String username,String password) throws Throwable {
//        BaseClass bs = new BaseClass();
//        driver=bs.getDriver();
        lp=new LoginPage(driver);
        lp.loginToNinza(username,password);
        hp=new HomePage(driver);
        Reporter.log("Driver is: "+driver);
        createCampPage=new CreateCampaignsPage(driver);
        hp.getCreateCampaignButton().click();
        createCampPage.createCampaignWithMandatoryDetails(JavaUtility.generateCampaignName(), ExcelUtility.toReadDataFromExcel("Campaigns",1,0));
        verifyPopUpAndCreation(driver,"campaign");
        //Close popUp
        createCampPage.getClosePopUp().click();

    }
    @Test(dataProvider = "campaignDetails",dependsOnMethods = "createCampaignWithMandatoryDetails",groups = {"Functional"},retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void createContact(String username,String password) throws Throwable {

        //BaseClass bs = new BaseClass();
        //driver=bs.getDriver();
        lp=new LoginPage(driver);
        lp.loginToNinza(username,password);
        hp=new HomePage(driver);
        createCampPage=new CreateCampaignsPage(driver);
        contPage = new ContactsPage(driver);
        createCampPage = new CreateCampaignsPage(driver);
        createContPage = new CreateContactsPage(driver);
        hp.getCreateCampaignButton().click();
        hp.getContactsLink().click();
        Thread.sleep(1000);
        //Click on create Contact button
        contPage.getcreateContactButton().click();
        //driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
        Thread.sleep(1000);
        //Store parent window handle
        String parentWh = driver.getWindowHandle();
        //Fill Organization
        createContPage.getOrganizationName().sendKeys(JavaUtility.generateOrganizationName());
        Thread.sleep(1000);
        //Fill Title
        createContPage.getTitle().sendKeys(JavaUtility.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Contact Name
        createContPage.getContactName().sendKeys(JavaUtility.generateRandomStrings());
        Thread.sleep(1000);
        //Fill Mobile
        createContPage.getMobile().sendKeys(JavaUtility.generateRandomMobileNo());
        Thread.sleep(1000);
        //Campaign button
        createContPage.getSelectcampaignSubmitButton().click();
        Thread.sleep(1000);
        //Passing window handle to dialogue box
        WebDriverUtility.switchToWindowByTitle(driver,"Select Campaign");
        Thread.sleep(1000);
        //select campaign id from drop down
        createContPage.getSelectCampaignIdDropDown().click();
        Reporter.log("Select campaign from drop down is successful!!");
        //Get Campaign Id from propert file
        String campaignId = JavaUtility.getFromPropertyFile("campaignId");
        //Serach for Camapign id inside search box
        createContPage.getSearchCampaignIdSearchBox().sendKeys(campaignId);
        Reporter.log("Enter Campaign ID is successful!!");
        Thread.sleep(1000);
        //Click on Select Campaign button
        createContPage.getSelectCampaignButtontable().click();
        Thread.sleep(1000);
        //Switch back to parent window handle
        System.out.println("Switching to parent window");
        WebDriverUtility.switchToParentWindow(driver,parentWh);
        Thread.sleep(2000);
        System.out.println("Switch to parent window is successful!!");
        //Click on Create Contact button
        createContPage.getCreateContactSubmitButton().click();
        Thread.sleep(1000);
        //Verify contact pop up and contact creation
        CreateContactTest.verifyPopUpAndCreation(driver,"contact");
        //Close popUp
        createCampPage.getClosePopUp().click();

    }


}
