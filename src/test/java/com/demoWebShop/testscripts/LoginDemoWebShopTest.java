package com.demoWebShop.testscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository_DemoWebShop.DemoWebLoginPage;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class LoginDemoWebShopTest extends BaseClass {
//    Scenarios:
    //1)Register 2)Login 3)Add Address 4)Add to Cart and placeorder
//    1) Login- Verify Title
//    2) Add product to cart and place order
    //3)Add Address
    //4) Display the ordersbased on price low to hih

    public DemoWebLoginPage loginPage = null;
    public HomePage hp = null;

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

    @Test(dataProvider = "demoWebLoginDetails", retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void loginToDemoWebShop(WebDriver driver,String emailId, String password) throws Throwable {
        loginPage = new DemoWebLoginPage(driver);
        WebDriverUtility.toWait(1000);
        loginPage.loginToDemoWebShop(emailId, password);
        String expTitle = driver.getTitle();
        Assert.assertEquals(driver.getTitle(), "Demo Web Shop");
        Reporter.log("Login is Successful!!",true);

    }
}
