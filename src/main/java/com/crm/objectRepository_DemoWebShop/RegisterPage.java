package com.crm.objectRepository_DemoWebShop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    public RegisterPage(){

    }
    public WebDriver driver;
    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(linkText = "Register")
    WebElement registerLink;
    @FindBy(id = "gender-male")
    WebElement genderRadioButton;
    @FindBy(id = "FirstName")
    WebElement firstName;
    @FindBy(id = "LastName")
    WebElement lastName;
    @FindBy(id = "Email")
    WebElement email;
    @FindBy(id = "Password")
    WebElement password;
    @FindBy(id = "ConfirmPassword")
    WebElement confirmPassword;
    @FindBy(id = "register-button")
    WebElement registerButton;
    @FindBy(xpath = "//div[@class='result']")
    WebElement registrationText;


    public WebElement getRegisterLink() {
        return registerLink;
    }

    public WebElement getGenderRadioButton() {
        return genderRadioButton;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
    public WebElement getRegistrationText() {
        return registrationText;
    }

}
