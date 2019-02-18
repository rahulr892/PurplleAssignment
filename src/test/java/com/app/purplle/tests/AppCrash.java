package com.app.purplle.tests;

import com.app.purplle.base.TestBase;
import org.testng.annotations.Test;

public class AppCrash extends TestBase {

    @Test(priority = 1)
    public void app_Crash_Scenario() {
        startReport(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "App crash while selecting lipstick scenario", this.getClass().getSimpleName());
        login.waitForLoginScreen()
                .getToHomeScreen();
        home.waitForHomeScreen()
                .openLeftMenu()
                .clickMakeupButton();
        makeup.click_Lipstick_Option();
        lipsticks.clickAnOptionInAllLipsticks();
        printLogs();
    }

    @Test(priority = 2)
    public void clicking_Banner_And_Checking_Logs() {
        startReport(Thread.currentThread().getStackTrace()[1].getMethodName(),
                "Clicking banner and filtering logs with 'testing_aws'", this.getClass().getSimpleName());
        login.waitForLoginScreen()
                .getToHomeScreen();
        home.waitForHomeScreen()
                .clickingBanners();
        printFilteredLogs("testing_aws");
    }

}

