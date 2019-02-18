package com.app.purplle.base;

import com.app.purplle.pages.Home;
import com.app.purplle.pages.Lipsticks;
import com.app.purplle.pages.Login;
import com.app.purplle.pages.Makeup;
import com.app.purplle.utilities.ExtentUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

public class TestBase {

    protected Login login;
    protected Home home;
    protected Makeup makeup;
    protected Lipsticks lipsticks;
    private AppiumDriver appiumDriver;

    @BeforeSuite
    public void startReporting() {
        System.out.println("Suite execution start\nStart extent reporting");
        String dir = "./Reports/Purplle/report";
        //String time = new SimpleDateFormat("@dd_MM_yyyy@HH_mm_ss").format(new Date());
        String time = "time";
        String html = ".html";
        String path = dir + time + html;
        ExtentUtil.createReporter(path);
    }

    @AfterSuite
    public void endReporting() {
        System.out.println("Suite execution finish\nEnd extent reporting");
    }


    @Parameters({"platform"})
    @BeforeMethod
    public void setUp(@Optional("android") String platform) {
        if (platform.equalsIgnoreCase("android")) {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "/apps/Android/");
            File app = new File(appDir, "purplleAndroid.apk");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("deviceName", "Galaxy J6+");
            caps.setCapability("udid", "95a89620");
            caps.setCapability("platformName", "Android");
            caps.setCapability("app", app.getAbsolutePath());
            caps.setCapability("appPackage", "com.manash.purplle");
            caps.setCapability("appActivity", "com.manash.purplle.activity.SplashActivity");
            caps.setCapability("skipDeviceInitialization", true);
            caps.setCapability("skipServerInstallation", true);

            try {
                appiumDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (platform.equalsIgnoreCase("ios")) {
            //
        }

        login = new Login(appiumDriver);
        home = new Home(appiumDriver);
        makeup = new Makeup(appiumDriver);
        lipsticks = new Lipsticks(appiumDriver);
    }


    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (appiumDriver != null) {
            hasAppCrashed();

            if (testResult.getStatus() == ITestResult.SUCCESS) {
                ExtentUtil.fetchTest().log(Status.PASS, MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));

            } else if (testResult.getStatus() == ITestResult.FAILURE) {
                ExtentUtil.fetchTest().log(Status.INFO, testResult.getThrowable());
                ExtentUtil.fetchTest().log(Status.FAIL, MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));

            } else if (testResult.getStatus() == ITestResult.SKIP) {
                ExtentUtil.fetchTest().log(Status.INFO, testResult.getThrowable());
                ExtentUtil.fetchTest().log(Status.SKIP, MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));

            }
            appiumDriver.quit();
            appiumDriver = null;
            ExtentUtil.saveReporter(); // Saves test log in report under ExtentReports folder
        }
    }


    protected void printLogs() {
        List<LogEntry> logEntries = appiumDriver.manage().logs().get("logcat").filter(Level.ALL);
        for (LogEntry logEntry : logEntries)
            System.out.println(logEntry.getMessage());
    }


    protected void printFilteredLogs(String filterText) {
        List<LogEntry> logEntries = appiumDriver.manage().logs().get("logcat").filter(Level.ALL);
        for (LogEntry logEntry : logEntries) {
            if (logEntry.getMessage().contains(filterText))
                System.out.println(logEntry.getMessage());
        }
    }


    private void hasAppCrashed() {
        By alertTitle = By.id("android:id/alertTitle");
        Boolean crashTextAppeared = appiumDriver.findElements(alertTitle).size() > 0;
        if (crashTextAppeared) {
            if (appiumDriver.findElement(alertTitle).getAttribute("text").equals("Purplle has stopped")) {
                ExtentUtil.fetchTest().log(Status.FATAL, "App has crashed");
                //Assert.fail("App Crash Occurred");
            }
        }
    }


    protected void startReport(String testName, String description, String category) {
        ExtentUtil.createTest(testName, description);
        ExtentUtil.fetchTest().assignCategory(category);
    }


}
