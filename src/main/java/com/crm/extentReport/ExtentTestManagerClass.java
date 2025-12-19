package com.crm.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManagerClass {

    private static ExtentReports extent= ExtentReportManagerClass.getExtentReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

    public static void createTest(String testName) {
        ExtentTest extentTest;
        if (parentTest.get() != null) {
            extentTest = parentTest.get().createNode(testName); // Child test
        } else {
            extentTest = extent.createTest(testName); // No parent, simple test
        }
        test.set(extentTest);
    }

    public static void log(Status status, String details) {
        test.get().log(status, details);
    }

    // Flush the report
    public static void flushReport() {
        extent.flush();
    }

    // Retrieve the current test
    public static ExtentTest getTest() {
        return test.get();
    }
}
