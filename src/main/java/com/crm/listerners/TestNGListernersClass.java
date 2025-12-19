package com.crm.listerners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.crm.extentReport.ExtentTestManagerClass;
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

public class TestNGListernersClass implements ITestListener {

    public void onTestStart(ITestResult result) {
        ExtentTestManagerClass.createTest(result.getMethod().getMethodName());
        Reporter.log("======Test is going to start========"+result.getTestContext().getName()+"=="+result.getMethod().getMethodName(),true);
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        ExtentTestManagerClass.log(Status.PASS,"Test Passed: "+result.getName());
        Reporter.log("======Test is Successfull!!========",true);
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        ExtentTestManagerClass.log(Status.FAIL, "Test Failed: " + result.getName());
        Reporter.log(result.getTestContext().getName()+"=="+result.getMethod().getMethodName(),true);
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
            File dest=new File("./\\src\\main\\resources\\screenshots/scnShot_"+d+".jpeg");
            FileUtils.copyFile(src,dest);
            //String scrnShotPath= IConstant.screenShotPath+"/"+"scnShot_"+d+".jpeg";
            // Method to capture screenshot and return as Base64 String
            String base64Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
            ExtentTest testName = ExtentTestManagerClass.getTest();
            testName.fail("Screenshot below: ", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

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
        Reporter.log("======Test is Failed!!========",true);
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        ExtentTestManagerClass.log(Status.SKIP, "Test Skipped: " + result.getName());
        Reporter.log("Skipped Test: "+result.getTestContext().getName()+"=="+result.getMethod().getMethodName(),true);
        Reporter.log("======Test is skipped!!==========",true);
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
        ExtentTestManagerClass.flushReport();
        System.out.println("======Test is finished========");
    }
}
