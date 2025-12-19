package com.demoWebShop.testscripts;

import com.crm.IConstant;
import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository_DemoWebShop.RegisterPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class RegisterDemoWebShopTest extends BaseClass {
//    Scenarios:
//    1) Login- Verify Title
//    2) Add product to cart and place order
    //3)Add Address
    //4) Display the ordersbased on price low to hih


    @Test(retryAnalyzer = com.crm.listerners.IRetryAnalyzerClass.class)
    public void registerToDemoWebShop() throws Throwable {
        RegisterPage r = new RegisterPage(driver);
        r.getRegisterLink().click();
        r.getGenderRadioButton().click();
        r.getFirstName().sendKeys(JavaUtility.generateName());
        r.getLastName().sendKeys(JavaUtility.generateName());
        r.getEmail().sendKeys(JavaUtility.generateEmailId());
        r.getPassword().sendKeys(IConstant.password);
        r.getConfirmPassword().sendKeys(IConstant.password);
        r.getRegisterButton().click();
        WebDriverUtility.toWait(1000);
        String actualText=r.getRegistrationText().getText();
        Assert.assertEquals("Your registration completed",actualText);
        Reporter.log("Registration is Successfull!!",true);
    }
}
