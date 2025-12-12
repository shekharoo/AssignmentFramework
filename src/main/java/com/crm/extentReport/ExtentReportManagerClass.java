package com.crm.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.IConstant;

import java.util.Date;

public class ExtentReportManagerClass {
    private static ExtentReports extentReport;
    public static ExtentReports getExtentReport()
    {
        if(extentReport==null)
        {
            Date date = new Date();
            String d=date.toString().replace(" ","_").replace(":","_");
            String reportPath = IConstant.extentReportPath+"/"+"report_"+d+".html";
            ExtentSparkReporter sparkReporter= new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Results");

            extentReport = new ExtentReports();
            extentReport.attachReporter(sparkReporter);
            extentReport.setSystemInfo("Tester", "Shekhar Anand");
            extentReport.setSystemInfo("Environment", "QA");
        }
        return extentReport;
    }
}
