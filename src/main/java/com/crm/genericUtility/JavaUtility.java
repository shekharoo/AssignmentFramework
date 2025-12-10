package com.crm.genericUtility;

import com.crm.IConstant;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class JavaUtility {
    public static StringBuilder generateRandomStrings() {
        new Random();
        StringBuilder sb = new StringBuilder();
        String str = "ABC012ZXC";
        int strLen = str.length();

        for(int i = 0; i < strLen; ++i) {
            int index = (int)((double)strLen * Math.random());
            sb.append(str.charAt(index));
        }

        return sb;
    }
    public static String generateCampaignName()
    {
        //Generate random strings
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        String s = "Campaign";
        String s1 = "RXYZWABCRT";
        char[] c=s1.toCharArray();
        int strLen = c.length;
        for(int i=0;i<strLen;i++)
        {
            int j = r.nextInt(4);
            char temp=c[i];
            c[i]=c[j];
            c[j]=temp;
            //s+s1.charAt(i);
        }
        for(char c1:c)
        {
            sb.append(c1);
        }
        String s2=sb.toString();
        return s+s2;
        //System.out.println("Combine String: "+s+s2);
        //System.out.println(Arrays.toString(c));

//        Random r = new Random();
//        String s = "Campaign";
        //String s1 = "5284";
        //int strLen = s1.length();
        //Random r = new Random();
        //int = r.nextInt();
        //Math.random();
        //int index = (int) strLen*Math.random();
//        String a = String.valueOf(r.nextInt(999));
//        r.next
//        String campaignName = s + a;
        //String final = s+a;

        //return campaignName;
        //return s2;
        }


    public static String generateOrganizationName()
    {
        StringBuilder sb = new StringBuilder();
        //Generate random strings
        Random r = new Random();
        String s = "Qspiders";
        //String s1 = String.valueOf(r.nextInt(999));
        String s1 = "XYZW";
        char[] c=s1.toCharArray();
        int strLen = c.length;
        for(int i=0;i<strLen;i++)
        {
            int j = r.nextInt(3);
            char temp=c[i];
            c[i]=c[j];
            c[j]=temp;
            //s+s1.charAt(i);
        }
        for(char c1:c)
        {
            sb.append(c1);
        }
        String s2=sb.toString();
        return s+s2;
       // return s+s1;
    }
    public static StringBuilder generateRandomMobileNo()
    {
        //Generate random strings
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        String str = "0123456789";
        int strLen = str.length();
        for(int i=0;i<strLen;i++)
        {
            int index = (int)((int)strLen*Math.random());
            sb.append(str.charAt(index));
        }
        return sb;
    }
    public static String generateProductName()
    {
        StringBuilder sb = new StringBuilder();
        //Generate random strings
        Random r = new Random();
        String s = "PhilipsLED";
        //String s1 = String.valueOf(r.nextInt(999));
        String s1 = "XYZW";
        char[] c=s1.toCharArray();
        int strLen = c.length;
        for(int i=0;i<strLen;i++)
        {
            int j = r.nextInt(3);
            char temp=c[i];
            c[i]=c[j];
            c[j]=temp;
            //s+s1.charAt(i);
        }
        for(char c1:c)
        {
            sb.append(c1);
        }
        String s2=sb.toString();
        return s+s2;
        // return s+s1;
    }
    public static String extractTextFromPopUp(WebElement ele, String flowName)
    {
        String[] splitString = ele.getText().split(" ");
        String[] String;
        System.out.println(flowName+":"+splitString[1]+" is sucessfully created");
        return splitString[1];
    }
    public static String getCurrentDate() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String systemDate = sdf.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(systemDate));
        c.add(Calendar.DATE,1);
        String currDate = sdf.format(c.getTime());
        //String currDate = c.getTime().toString();

        return currDate;
    }

    public static void writeToPropertyFile(String key,String value) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(IConstant.propertyFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties prop = new Properties();
        prop.load(fis);
        prop.setProperty(key,value);

        try{
            fos=new FileOutputStream(IConstant.propertyFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        prop.store(fos,"File written successfully to Property File!!");

    }
    public static String getFromPropertyFile(String key) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(IConstant.propertyFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties prop = new Properties();
        prop.load(fis);
        String value = prop.getProperty(key);
        System.out.println("Value: "+value);
        System.out.println("Value is retrieved from property file successfully!");
        return value;

    }

    public static void main(String[] args) throws ParseException, IOException {
       //String orgName= JavaUtility.generateOrganizationName();
        //String campName=JavaUtility.generateCampaignName();
        //System.out.println(orgName);
        //System.out.println(campName);
        String camID=JavaUtility.getFromPropertyFile("CampaignId");
        System.out.println("CampdID: "+camID);
    }

}
