package com.crm.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WebDriverUtility {

    // manage & navigate methods
    public static void toMaximize(WebDriver driver) {
        driver.manage().window().maximize();
    }

    public void toMinimize(WebDriver driver) {
        driver.manage().window().minimize();
    }

    public void toGoBack(WebDriver driver) {
        driver.navigate().back();
    }

    public void toGoForward(WebDriver driver) {
        driver.navigate().forward();
        ;
    }

    public void toRefresh(WebDriver driver) {
        driver.navigate().refresh();
        ;
    }
    public static void toWait(long time) throws InterruptedException {
        try {
            Thread.sleep(time);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Waits &Synchronization
    public static void waitForPageToLoad(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Switch to Frame
    public void switchToFrame(WebDriver driver, int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrame(WebDriver driver, String nameorID) {
        driver.switchTo().frame(nameorID);
    }

    public void switchToFrame(WebDriver driver, WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    // Switch to alert
    public void switchToAlertAndAccept(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void switchToAlertAndDismiss(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String switchToAlertAndGetText(WebDriver driver) {
        String text = driver.switchTo().alert().getText();
        return text;
    }

    public void switchToAlertAndSendKeys(WebDriver driver, String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // Select class
    public static void select(WebElement element, int index) {
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }

    public void select(WebElement element, String value) {
        Select sel = new Select(element);
        sel.selectByValue(value);
    }

    public void select(String text, WebElement element) {
        Select sel = new Select(element);
        sel.selectByVisibleText(text);
    }

    // Actions Class

    public static void mouseHoverOnWebelemment(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.moveToElement(element).perform();
    }

    public void clickOnWebelement(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.click(element).perform();
    }

    public void doubleclickOnWebelement(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.doubleClick(element).perform();
    }

    public void rightclickOnWebelement(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.contextClick(element).perform();
    }

    public void scrollToWebelement(WebDriver driver, WebElement element) {
        Actions act = new Actions(driver);
        act.scrollToElement(element).perform();
    }

    public void drageAndDropWebelement(WebDriver driver, WebElement src, WebElement target) {
        Actions act = new Actions(driver);
        act.dragAndDrop(src, target).perform();
    }

    // Switch To parentWindow
    public static void switchToParentWindow(WebDriver driver, String parentId) {
        driver.switchTo().window(parentId);
    }

    // Switch To Window
    public static void switchToWindow(WebDriver driver) {
        Set<String> allids = driver.getWindowHandles();
        for (String id : allids) {
            driver.switchTo().window(id);
        }
    }

    // Switch To Window using Title
    public static void switchToWindowByTitle(WebDriver driver, String partialTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            driver.switchTo().window(win);
            if (driver.getTitle().contains(partialTitle)) {
                System.out.println("Switch to Dialogue box window successful");
                break;
            }
        }
    }

    // Switching to Child Window
    public void switchToChildWindow(WebDriver driver) {
        String parentId = driver.getWindowHandle();
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }
}
