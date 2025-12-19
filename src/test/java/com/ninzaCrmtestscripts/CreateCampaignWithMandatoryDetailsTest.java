package com.ninzaCrmtestscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateCampaignsPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class CreateCampaignWithMandatoryDetailsTest extends BaseClass {
    public CreateCampaignsPage createCampPage =null;
    public HomePage hp = null;
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
            }

        }
        return data;
    }

    public String flowId(WebDriver driver,String flowNameId)
    {
        List<WebElement> listFLows = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[1]"));
        String flowId = listFLows.get(0).getText();
        System.out.println(flowNameId+"Id is captured successfully!");
        return flowId;
    }

    public boolean verifyPopUpAndCreation(WebDriver driver,String flowName) throws InterruptedException, IOException {
        boolean flag = true;
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
                String flowNameId = CreateCampaignWithExpectedClosedDateTest.flowId(driver,flowName);

                System.out.println(flowName+"Id: "+flowNameId);
                //Store flow ID in Property File
                JavaUtility.writeToPropertyFile(flowName+"Id",flowNameId);
                return true;

            }
            else{
                System.out.println("Create "+flowName+" is not Successful!!");
                return false;
            }
        }
        else {
            System.out.println("Create "+flowName+" pop up is not displayed");
        }
        return false;

    }
    @Test(dataProvider = "campaignDetails",retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void createCampaignWithMandatoryDetails(String username,String password) throws Throwable {
        String flowName = "campaign";
        lp=new LoginPage(driver);
        lp.loginToNinza(username,password);
        hp=new HomePage(driver);
        Reporter.log("Driver is: "+driver);
        WebDriverUtility.toWait(1000);
        createCampPage=new CreateCampaignsPage(driver);
        WebElement campaignBtn = hp.getCreateCampaignButton();
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();",campaignBtn);
        createCampPage.createCampaignWithMandatoryDetails(JavaUtility.generateCampaignName(), ExcelUtility.toReadDataFromExcel("Campaigns",1,0));
        Assert.assertTrue(verifyPopUpAndCreation(driver,"campaign"));
        //Close popUp
        createCampPage.getClosePopUp().click();

    }

    }

