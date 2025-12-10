package com.crm.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class B{

    @Test
    public void getB() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://anand2.my.salesforce.com");
        driver.findElement(By.id("username")).sendKeys("anandmail2013-fd64@force.com");
        driver.findElement(By.id("password")).sendKeys("$Demo123$");
        driver.findElement(By.id("Login")).click();
        Thread.sleep(20000);
        driver.findElement(By.xpath("//BUTTON[@title='Show Navigation Menu']/LIGHTNING-PRIMITIVE-ICON[normalize-space(@variant)='bare']")).click();
        driver.findElement(By.xpath("//DIV[@id='navMenuList']/descendant::A[@data-itemid='Home']/SPAN/SPAN[normalize-space(@class)='menuLabel slds-listbox__option-text slds-listbox__option-text_entity']")).click();
        //A a= new B();
        //System.out.println("value of A: "+a.getA());
    }
}
