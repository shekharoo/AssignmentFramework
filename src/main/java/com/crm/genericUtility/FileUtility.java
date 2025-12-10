package com.crm.genericUtility;

import com.crm.IConstant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
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
}
