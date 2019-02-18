package com.app.purplle.pages;

import com.app.purplle.base.BasePage;
import com.app.purplle.utilities.ExtentUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Makeup extends BasePage {

    private final String lipsticks = "Lipsticks    Starting at Rs. 99";

    private By makeUpList = By.xpath("//*[@resource-id='com.manash.purplle:id/title']");

    public Makeup(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Makeup click_Lipstick_Option() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking lipsticks at 99 option");
        waitForElementsVisibility(20, makeUpList);
        scrollToContainsText(lipsticks).click();
        return this;
    }

}
