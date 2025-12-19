package com.crm.genericUtility;

import com.crm.extentReport.ExtentTestManagerClass;
import com.crm.objectRepository.CreateCampaignsPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository_DemoWebShop.DemoWebLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/***
 * Author: Shekhar Anand
 */
@Listeners(com.crm.listerners.TestNGListernersClass.class)
public class BaseClass {
    protected WebDriver driver;
    //protected RemoteWebDriver driver;

    public LoginPage lp = null;
    public CreateCampaignsPage createCampPage = null;
    public HomePage hp = null;
    public WebDriver getDriver() {
            return driver;
        }
    //public RemoteWebDriver getDriver() {
//        return driver;
//    }

    @BeforeSuite(groups = "Smoke")
    public void beforeSuite() {
        Reporter.log("Connect to DB is successful!!", true);
    }

 @Parameters({"browser"})
@BeforeClass(groups = "Smoke")
public WebDriver beforeClass(String browser,ITestContext context) throws IOException {
    //public WebDriver beforeClass(ITestContext context) throws IOException {
    //Reporter.log("launching Browser: "+browser, true);
    //FileUtility fu= new FileUtility();
    //String BROWSER = FileUtility.getFromPropertyFile("browser");
    //if(BROWSER.equalsIgnoreCase("chrome"))
        if(browser.equalsIgnoreCase("chrome"))
    {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        //URL url = new URL("http://49.249.28.218:8098/");
        //ChromeOptions option= new ChromeOptions();
        //option.addArguments("--start-maximized");
        //RemoteWebDriver driver = new RemoteWebDriver(url,options);
        driver = new ChromeDriver(options);
       // Set the driver instance as an attribute in ITestContext
        //context.setAttribute("RemoteWebDriver", driver);
        context.setAttribute("WebDriver", driver);
        //context.getTestContext().setAttribute("WebDriver", driver);
        WebDriverUtility.toMaximize(driver);
        lp = new LoginPage(driver);
        hp = new HomePage(driver);
        createCampPage=new CreateCampaignsPage(driver);
    } else if (browser.equalsIgnoreCase("edge")) {
        driver = new EdgeDriver();
            WebDriverUtility.toMaximize(driver);
            lp = new LoginPage(driver);
            hp = new HomePage(driver);
            createCampPage=new CreateCampaignsPage(driver);
    }
    else {
        driver = new FirefoxDriver();
    }
    return driver;
}

    @Parameters({"url"})
    @BeforeMethod(groups = "Smoke")
    public void navigateToUrl(String url) throws IOException, IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        Reporter.log("URL is: "+url,true);
    }
    @AfterMethod(groups = "Smoke")
    public void logoutApplication(ITestContext context) throws InterruptedException {
        String applnNAme=context.getCurrentXmlTest().getParameter("applicationName");

        if(applnNAme.equalsIgnoreCase("demowebshop"))
        {
            Reporter.log("Application Name is: "+applnNAme,true);
            DemoWebLoginPage loginPage = new DemoWebLoginPage(driver);
            WebDriverUtility.toWait(1000);
            loginPage.getLogoutLink();
            System.out.println("Log Out is Successful!!");
            ExtentTestManagerClass.flushReport();
        }
        else if(applnNAme.equalsIgnoreCase("ninzacrm")){
            WebDriverUtility.toWait(1000);
            WebDriverUtility.mouseHoverOnWebelemment(driver,hp.getLogOutIcon());
            WebDriverUtility.toWait(1000);
            hp.getLogOutLink().click();
            System.out.println("Log Out is Successful!!");
            ExtentTestManagerClass.flushReport();
        }
        else
        {
            Reporter.log("",true);
        }

    }

    @BeforeTest(groups = "Smoke")
    public void beforeTest() {
        Reporter.log("Pre conditions applied successfully!!", true);
    }

    @AfterTest(groups = "Smoke")
    public void afterTest() {
        Reporter.log("Post Conditions applied successfully!!", true);
    }
    @AfterClass(alwaysRun = true,groups = "Smoke")
    public void closeBrowser()
    {
        driver.quit();
        Reporter.log("Browser is closed successfully!!");
    }
    @AfterSuite(groups = "Smoke")
    public void afterSuite() {
        Reporter.log("Disconnect to DB is successful!!", true);
    }
}
