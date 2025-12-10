package com.crm.listerners;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class IRetryAnalyzerClass implements IRetryAnalyzer{
    int counter = 0;
    int retryLimit = 2;
    @Override
    public boolean retry(ITestResult result) {

        if(counter<retryLimit)
        {
            //if(!result.isSuccess())
            {

                System.out.println("Calling function..!!"+counter +" times..");
                counter++;
                //iRetryAnalyzerDemo();

                return true;
            }
        }

        return false;
    }


}
