package com.crm.objectRepository_DemoWebShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoWebLoginPage {

    WebDriver driver=null;
    public DemoWebLoginPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText="Log in")
    WebElement loginLink;

    @FindBy(id="Email")
    WebElement email;
    @FindBy(id="Password")
    WebElement password;
    @FindBy(xpath="//input[@type='submit' and @value='Log in']")
    WebElement submit;
    @FindBy(linkText="Log out")
    WebElement logoutLink;

    public WebElement getLoginPage() {
        return loginLink;
    }

    public WebElement getEmail() {
        return email;
    }
    public WebElement getPassword() {
        return password;
    }
    public WebElement getSubmit() {
        return submit;
    }
    public WebElement getLogoutLink() {
        return logoutLink;
    }

    public void loginToDemoWebShop(String emailId,String pswd)
    {
        loginLink.click();
        email.sendKeys(emailId);
        password.sendKeys(pswd);
        submit.click();
    }





}
