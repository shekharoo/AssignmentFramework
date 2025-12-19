package com.demoWebShop.testscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository_DemoWebShop.*;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class OrderDemoWebShopTest extends BaseClass {
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
    public void placeOrderDemoWebShop(String emailId, String password) throws Throwable {
        RegisterPage register=new RegisterPage(driver);
        DemoWebLoginPage log=new DemoWebLoginPage(driver);
        DemoWebHomePage hp=new DemoWebHomePage(driver);
        AddressPage addressPage = new AddressPage(driver);
        OrdersPage op=new OrdersPage(driver);
        RegisterDemoWebShopTest reg = new RegisterDemoWebShopTest();
        LoginDemoWebShopTest login = new LoginDemoWebShopTest();
        AddAddressDemoWebShopTest address = new AddAddressDemoWebShopTest();
        reg.registerToDemoWebShop(driver);
        log.getLogoutLink().click();
        WebDriverUtility.toWait(1000);
        login.loginToDemoWebShop(driver,emailId,password);
        address.addAddressDemoWebShop(driver);
        //Order Creation
        WebDriverUtility.toWait(1000);
        WebElement computerLink = op.getComputersLink();
        WebElement desktop = op.getDesktopDropDownLink();
        Actions action = new Actions(driver);
        action.moveToElement(computerLink).perform();
        desktop.click();
        WebDriverUtility.toWait(2000);
        op.getAddToCart1().click();
        op.getAddToCart2().click();
        String addToCartText=op.getAddToCartMsg().getText();
        Assert.assertTrue(addToCartText.contains("The product has been added"),"Successfully!! Added to Cart!");
        Reporter.log("Product has been added to cart Successfully!!",true);
        //Click on Shopping Cart
        op.getAddToCartMsgClose().click();
        WebDriverUtility.toWait(1000);
        op.getShoppingCart().click();
        op.getSelectCountry().sendKeys("India");
        op.getTermsofservice().click();
        op.getCheckout().click();
        op.getCheckoutContinue1().click();
        op.getCheckoutContinue2().click();
        op.getCheckoutContinue3().click();
        op.getCheckoutContinue4().click();
        op.getCheckoutContinue5().click();
        op.getConfirmButton().click();
        String actualOrderMessage=op.getOrderPlacedConfirmMessage().getText();
        Assert.assertEquals(actualOrderMessage, "Your order has been successfully processed!");
        Reporter.log("Congratulations!! Order has been placed Successfully!!",true);

    }
}
