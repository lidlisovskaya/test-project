package com.lida.autotests.tests;


import com.lida.autotests.core.driver.WebDriverSingleton;
import com.lida.autotests.utils.listeners.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static com.lida.autotests.utils.FileUtils.cleanOldReports;
import static com.lida.autotests.utils.ScreenshotUtils.attachScreenshotToAllure;

@Log4j2
@Listeners({TestListener.class})
public class BaseTest {

    protected WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void startSession() {
        log.info("Starting WebDriver session for thread: " + Thread.currentThread().getId());
        cleanOldReports();
        webDriver = WebDriverSingleton.getDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.wikipedia.org/");
    }

    @AfterMethod(alwaysRun = true)
    public void closeSession(ITestResult test) {
        if (test.getStatus() == 2) {
            attachScreenshotToAllure(test.getMethod().getMethodName());
        }
        log.info("Closing WebDriver session for thread: " + Thread.currentThread().getId());
        WebDriverSingleton.closeDriver();
    }
}

