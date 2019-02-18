package com.app.purplle.pages;

import com.app.purplle.base.BasePage;
import com.app.purplle.utilities.ExtentUtil;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Home extends BasePage {

    private By shopcart_button = By.xpath("//*[@id='com.manash.purplle:id/shop_cart' or @resource-id='com.manash.purplle:id/shop_cart']");
    private By opendrawer_button = By.xpath("//*[@content-desc='open_drawer']");
    private By makeup_button = By.xpath("//*[@resource-id='com.manash.purplle:id/title'][@text='Makeup']");
    private By banners = By.id("//*[@resource-id='com.manash.purplle:id/offer_image']");

    public Home(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public Home waitForHomeScreen() {
        ExtentUtil.fetchTest().log(Status.INFO, "Wait for home screen to load");
        waitForElementsVisibilityUsingAND(10, shopcart_button, opendrawer_button);
        return this;
    }

    public Home openLeftMenu() {
        ExtentUtil.fetchTest().log(Status.INFO, "Opening left menu drawer");
        clickElement(opendrawer_button);
        return this;
    }

    public Home clickMakeupButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking makeup button");
        clickElement(makeup_button);
        return this;
    }

    public Home clickingBanners() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking banners");
        List<WebElement> bannerList = findElementsList(banners);
        bannerList.get(1).click();
        return this;
    }

}
