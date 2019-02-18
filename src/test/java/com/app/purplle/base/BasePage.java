package com.app.purplle.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class BasePage {

    private AppiumDriver appiumDriver;

    public BasePage(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }


    //Click an element
    protected WebElement findElement(By by) {
        (new WebDriverWait(appiumDriver, 10)).until(
                ExpectedConditions.visibilityOfElementLocated(by));
        return appiumDriver.findElement(by);
    }


    //Click an element
    protected List<WebElement> findElementsList(By by) {
        return appiumDriver.findElements(By.id("com.manash.purplle:id/offer_image"));
    }


    //Click an element
    protected void clickElement(By by) {
        (new WebDriverWait(appiumDriver, 10)).until(
                ExpectedConditions.visibilityOfElementLocated(by));
        appiumDriver.findElement(by).click();
    }


    protected void scrollToElement(String resourceId, String text) {
        ((AndroidDriver) appiumDriver)
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                        + ".resourceId(\"" + resourceId + "\")).scrollIntoView("
                        + "new UiSelector().text(\"" + text + "\"));");
    }


    protected void scrollToElementAndClick(String resourceId, String text) {
        ((AndroidDriver) appiumDriver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"" + resourceId + "\")).scrollIntoView("
                + "new UiSelector().text(\"" + text + "\"));").click();
    }


    protected void scrollToElement(String resourceId, String classname, String text) {
        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"" + resourceId + "\")).getChildByText("
                        + "new UiSelector().className(\"" + classname + "\"), \"" + text + "\")"));
    }


    protected void scrollAndClick(String resourceId, String classname, String text) {
        appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().id(\"" + resourceId + "\")).getChildByText("
                        + "new UiSelector().className(\"" + classname + "\"), \"" + text + "\")")).click();
    }


    protected void waitForElementsVisibility(int timeInSecs, By... bys) {
        for (By by : bys)
            (new WebDriverWait(appiumDriver, timeInSecs)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    protected void waitForElementsVisibilityUsingAND(int timeInSecs, By by1, By by2) {
        (new WebDriverWait(appiumDriver, timeInSecs)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(by1),
                ExpectedConditions.visibilityOfElementLocated(by2)));
    }


    protected void waitForElementsVisibilityUsingOR(int timeInSecs, By by1, By by2) {
        (new WebDriverWait(appiumDriver, timeInSecs)).until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(by1),
                ExpectedConditions.visibilityOfElementLocated(by2)));
    }


    protected void waitForElementsVisibility(int timeInSecs, By by) {
        List<WebElement> elements = ((WebDriver) appiumDriver).findElements(by);
        for (WebElement element : elements)
            (new WebDriverWait(appiumDriver, timeInSecs)).until(ExpectedConditions.visibilityOf(element));
    }


    protected Boolean isElementPresent(By by) {
        return appiumDriver.findElements(by).size() > 0;
    }


    //Tap to an element for 250 milliseconds
    protected void tapByElement(AndroidElement androidElement) {
        new TouchAction(appiumDriver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }


    //Tap by coordinates
    protected void tapByCoordinates(int x, int y) {
        new TouchAction(appiumDriver)
                .tap(point(x, y))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }


    //Press by element
    protected void pressByElement(AndroidElement element, long seconds) {
        new TouchAction(appiumDriver)
                .press(element(element))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }


    //Press by coordinates
    protected void pressByCoordinates(int x, int y, long seconds) {
        new TouchAction(appiumDriver)
                .press(point(x, y))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }


    //Horizontal Swipe by percentages
    protected void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = appiumDriver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);

        new TouchAction(appiumDriver)
                .press(point(startPoint, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endPoint, anchor))
                .release().perform();
    }


    //Vertical Swipe by percentages
    protected void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = appiumDriver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(appiumDriver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }


    //Swipe by elements
    protected void swipeByElements(By by1, By by2) {
        AndroidElement startElement = (AndroidElement) appiumDriver.findElement(by1);
        AndroidElement endElement = (AndroidElement) appiumDriver.findElement(by2);

        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction(appiumDriver)
                .press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }


    //Multitouch action by using an android element
    protected void multiTouchByElement(AndroidElement androidElement) {
        TouchAction press = new TouchAction(appiumDriver)
                .press(element(androidElement))
                .waitAction(waitOptions(ofSeconds(1)))
                .release();

        new MultiTouchAction(appiumDriver)
                .add(press)
                .perform();
    }


    protected WebElement scrollToContainsText(String containsText) {
        return ((AndroidDriver) appiumDriver).findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + containsText + "\").instance(0))");
    }


    protected WebElement scrollToExactText(String text) {
        return ((AndroidDriver) appiumDriver).findElementByAndroidUIAutomator
                ("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))");
    }


    protected WebElement findElementUsingText(String text) {
        return ((AndroidDriver) appiumDriver).findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")");
    }


    protected WebElement findElementUsingContainsText(String text) {
        return ((AndroidDriver) appiumDriver).findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
    }


    protected void waitForSync(int timeInSecs) {
        try {
            Thread.sleep(timeInSecs * 1000);
        } catch (Exception e) {
            //
        }
    }


}
