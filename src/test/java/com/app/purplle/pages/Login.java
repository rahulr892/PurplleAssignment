package com.app.purplle.pages;

import com.app.purplle.base.BasePage;
import com.app.purplle.utilities.ExtentUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Login extends BasePage {

    private By login_button = By.id("com.manash.purplle:id/login_button");
    private By shopnow_button = By.id("com.manash.purplle:id/shop_now_button");
    private By skip_Button = By.id("com.manash.purplle:id/skip_onboard_btn");
    private By ok_button = By.id("android:id/button1");
    private By gotIt_button = By.id("com.manash.purplle:id/got_it_button");

    public Login(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Login waitForLoginScreen() {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for login screen to load");
        waitForElementsVisibilityUsingOR(20, shopnow_button, skip_Button);
        if (isElementPresent(shopnow_button))
            waitForElementsVisibility(20, login_button, shopnow_button);
        return this;
    }

    public Login getToHomeScreen() {
        ExtentUtil.fetchTest().log(Status.INFO, "Navigating to home screen");
        if (isElementPresent(shopnow_button)) {
            clickElement(shopnow_button);
        } else if (isElementPresent(skip_Button)) {
            clickElement(skip_Button);
            clickElement(ok_button);
            clickElement(gotIt_button);
        }
        return this;
    }

}
