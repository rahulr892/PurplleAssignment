package com.app.purplle.pages;

import com.app.purplle.base.BasePage;
import com.app.purplle.utilities.ExtentUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Lipsticks extends BasePage {

    private By title = By.id("com.manash.purplle:id/toolbar_title");
    private By bestSellersTitle = By.xpath("//*[@text='BEST SELLERS']");
    private By alertTitle = By.id("android:id/alertTitle");
    private By openAppAgain = By.id("android:id/aerr_restart");

    public Lipsticks(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Lipsticks clickAnOptionInAllLipsticks() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking a lipstick in all lipsticks");
        waitForElementsVisibility(20, title, bestSellersTitle);
        waitForSync(2);
        for (int i = 0; i < 4; i++)
            verticalSwipeByPercentages(0.8, 0.2, 0.5);
        findElementUsingContainsText("Purplle Lip Crayon").click();
//        if (findElement(alertTitle).getAttribute("text").equals("Purplle has stopped")) {
//            ExtentUtil.fetchTest().log(Status.FATAL, "App has crashed");
//            Assert.fail("App Crashed");
//        }
        return this;
    }

}
