package com.crm.genericUtility;

import com.crm.listerners.TestNGListernersClass;
import com.crm.objectRepository.CreateCampaignsPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IRetryAnalyzer;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class BaseClass {
    //protected WebDriver driver;
    public WebDriver driver;

    public LoginPage lp = null;
    public CreateCampaignsPage createCampPage = null;
    public HomePage hp = null;
    public WebDriver getDriver() {
            return driver;
        }

    @BeforeSuite
    public void beforeSuite() {
        Reporter.log("Connect DB is successful!!", true);
    }

@BeforeClass
public WebDriver beforeClass() throws IOException {
    Reporter.log("launching Browser", true);
    //FileUtility fu= new FileUtility();
    String BROWSER = FileUtility.getFromPropertyFile("browser");
    if(BROWSER.equalsIgnoreCase("chrome"))
    {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        //setDriver(driver);
        WebDriverUtility.toMaximize(driver);
        lp = new LoginPage(driver);
        hp = new HomePage(driver);
        createCampPage=new CreateCampaignsPage(driver);
    } else if (BROWSER.equalsIgnoreCase("edge")) {
        driver = new EdgeDriver();
    }
    else {
        driver = new FirefoxDriver();
    }
    return driver;
}
//
   @AfterClass(alwaysRun = true)
    public void closeBrowser()
   {
    driver.quit();
    Reporter.log("Browser is closed successfully!!");
   }
    @BeforeMethod()
    public void loginApplication() throws IOException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(FileUtility.getFromPropertyFile("URL"));
        lp.getUname().sendKeys(FileUtility.getFromPropertyFile("username"));
        lp.getPswd().sendKeys(FileUtility.getFromPropertyFile("password"));
        lp.getSubmit().click();
    }
    @AfterMethod
    public void logoutApplication() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverUtility.mouseHoverOnWebelemment(driver,hp.getLogOutIcon());
        Thread.sleep(1000);
        hp.getLogOutLink().click();
        System.out.println("Log Out is Successfull!!");
    }


    @BeforeTest
    public void beforeTest() {
        Reporter.log("Pre conditions", true);
    }

    @AfterTest
    public void afterTest() {
        Reporter.log("Post Conditions", true);
    }

    @AfterSuite
    public void afterSuite() {
        Reporter.log("Disconnect DB is successful!!", true);
    }
}
