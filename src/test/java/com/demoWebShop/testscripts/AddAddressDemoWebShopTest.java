package com.demoWebShop.testscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.JavaUtility;
import com.crm.objectRepository_DemoWebShop.AddressPage;
import com.crm.objectRepository_DemoWebShop.DemoWebHomePage;
import com.crm.objectRepository_DemoWebShop.DemoWebLoginPage;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class AddAddressDemoWebShopTest extends BaseClass {
//    Scenarios:
//    1) Login- Verify Title
//    2) Add product to cart and place order
    //3)Add Address
    //4) Display the ordersbased on price low to hih

    @DataProvider(parallel = true)
    public Object[][] demoWebLoginDetails() throws IOException {
        FileInputStream fis = new FileInputStream(IConstant.excelPath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet(IConstant.excelDemoWebLoginSheetName);
        int row = sh.getLastRowNum();
        int cell = sh.getRow(row).getLastCellNum();
        Object[][] data = new Object[row][cell];
        DataFormatter value = new DataFormatter();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cell; j++) {
                Cell cl = sh.getRow(i + 1).getCell(j);
                data[i][j] = value.formatCellValue(cl);
            }

        }
        return data;
    }
    @Test(dataProvider = "demoWebLoginDetails",retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void addAddressDemoWebShop(WebDriver driver) throws Throwable {
        DemoWebHomePage hp=new DemoWebHomePage(driver);
        DemoWebLoginPage login=new DemoWebLoginPage(driver);
        AddressPage ap = new AddressPage(driver);
        //login.loginToDemoWebShop(emailId,password);
        hp.getMyAccount().click();
        hp.getAddressMenu().click();
        hp.getAddNewAddress().click();
        ap.getAddress_FirstName().sendKeys(JavaUtility.generateName());
        ap.getAddress_LastName().sendKeys(JavaUtility.generateName());
        ap.getAddress_Email().sendKeys(JavaUtility.generateEmailId());
        ap.getAddress_CountryId().sendKeys("India");
        ap.getAddress_City().sendKeys(JavaUtility.generateName());
        ap.getAddress_Address1().sendKeys(JavaUtility.generateName());
        ap.getAddress_ZipPostalCode().sendKeys(JavaUtility.generateRandomMobileNo());
        ap.getAddress_PhoneNumber().sendKeys(JavaUtility.generateRandomMobileNo());
        ap.getAddress_AddressSave().click();
        String actualText = ap.getAddress_AddressCreated().getText();
        //Assert.assertEquals("My account - Addresses",actualText);
        Reporter.log("Address is created Successfully!!",true);
    }
}
