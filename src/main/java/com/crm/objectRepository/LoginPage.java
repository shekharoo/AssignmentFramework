package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver=null;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

//    public WebDriver getDriver() {
//        return driver;
//    }

    @FindBy(id="username")
    WebElement uname;

    @FindBy(id="inputPassword")
    WebElement pswd;

    @FindBy(xpath="//button[@type='submit']")
    WebElement submit;

    public WebElement getUname() {
        return uname;
    }

    public WebElement getPswd() {
        return pswd;
    }
    public WebElement getSubmit() {
        return submit;
    }

    public void setSubmit(WebElement submit) {
        this.submit = submit;
    }

    public void setUname(WebElement uname) {
        this.uname = uname;
    }

    public void setPswd(WebElement pswd) {
        this.pswd = pswd;
    }

    public void loginToNinza(String username,String password)
    {
        uname.sendKeys(username);
        pswd.sendKeys(password);
        submit.click();
    }





}
