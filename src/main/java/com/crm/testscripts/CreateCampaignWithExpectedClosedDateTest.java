package com.crm.testscripts;

import com.crm.IConstant;
import com.crm.genericUtility.*;
import com.crm.objectRepository.CreateCampaignsPage;
import com.crm.objectRepository.HomePage;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class CreateCampaignWithExpectedClosedDateTest extends BaseClass {
    //public WebDriver driver = null;
    public static CreateCampaignsPage createCampPage = null;
    public static HomePage hp = null;

    @DataProvider(parallel = true)
    public Object[][] loginDetails() throws IOException {
        FileInputStream fis = new FileInputStream(IConstant.excelPath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet(IConstant.excelCampaignsSheetName);
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


    public static String flowId(WebDriver driver,String flowNameId)
    {
        List<WebElement> listFLows = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[1]"));
        String flowId = listFLows.get(0).getText();
        System.out.println(flowNameId+"Id is captured successfully!");
        return flowId;
    }

    public static void verifyPopUpAndCreation(WebDriver driver,String flowName) throws InterruptedException, IOException {
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

            }
            else{
                System.out.println("Create "+flowName+" is not Successful!!");
            }
        }
        else {
            System.out.println("Create "+flowName+" pop up is not displayed");
        }

    }
    @Test(dataProvider = "loginDetails",alwaysRun = true,retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void createCampaignWithExpectedClosedDate(String targetSize) throws Throwable {
        //BaseClass bs = new BaseClass();
        //driver=bs.getDriver();
        hp=new HomePage(driver);
        createCampPage=new CreateCampaignsPage(driver);
        hp.getCreateCampaignButton().click();
        createCampPage.createCampaignWithClosingDate(JavaUtility.generateCampaignName(), ExcelUtility.toReadDataFromExcel("Campaigns",1,0),JavaUtility.getCurrentDate());
        CreateCampaignWithExpectedClosedDateTest.verifyPopUpAndCreation(driver,"campaign");
        //Close popUp
        createCampPage.getClosePopUp().click();

    }

    }

