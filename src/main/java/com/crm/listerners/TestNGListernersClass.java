package com.crm.listerners;
import com.crm.genericUtility.BaseClass;
import com.crm.testscripts.CreateCampaignWithMandatoryDetailsTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestNGListernersClass extends BaseClass implements ITestListener {
//    public TestNGListernersClass()
//    {
//
//    }
    //WebDriver driver = null;
//    public TestNGListernersClass(WebDriver driver){
//        this.driver=driver;
//        Reporter.log("Diver value: "+driver);
//    }
    CreateCampaignWithMandatoryDetailsTest c= new CreateCampaignWithMandatoryDetailsTest();
    public void onTestStart(ITestResult result) {


        System.out.println("======Test is going to start========"+result.getTestContext().getName()+"=="+result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        System.out.println("======Test is Successfull!!===================");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getTestContext().getName()+"=="+result.getMethod().getMethodName());
         // Retrieve the driver instance from the test context
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        //BaseClass bs = new BaseClass();
        //driver = bs.getDriver();
        Date date = new Date();
        String d=date.toString().replace(" ","_").replace(":","_");
        //Take Screenshot of failed Test Case
        Reporter.log("Diver value: "+driver,true);
        try{
            TakesScreenshot ts = (TakesScreenshot)driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest=new File("./\\src\\main\\resources\\screenshots/scnShot_"+d+".jpg");
            FileUtils.copyFile(src,dest);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

//        try {
//
//        } catch (IOException e) {
//            //throw new RuntimeException(e);
//        }
        Reporter.log("Screenshot captured sucessfully!!",true);
        System.out.println("======Test is Failed!!========");
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        System.out.println("======Test is skipped!!==========");
    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    public void onStart(ITestContext context) {
        System.out.println("======All Tests in class going to start=========");
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    public void onFinish(ITestContext context) {
        System.out.println("======Test is finished========");
    }
}
