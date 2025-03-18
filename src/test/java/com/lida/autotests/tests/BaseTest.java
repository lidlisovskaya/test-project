package com.lida.autotests.tests;


import com.lida.autotests.core.driver.WebDriverSingleton;
import com.lida.autotests.utils.listeners.TestListener;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.lida.autotests.utils.FileUtils.cleanOldReports;

@Log4j2
@Listeners({TestListener.class})
public class BaseTest {

    protected WebDriver webDriver;

    @Step
    @BeforeMethod(alwaysRun = true)
    public void startSession() {
        log.info("Starting WebDriver session for thread: " + Thread.currentThread().getId());
        cleanOldReports();
        webDriver = WebDriverSingleton.getDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.wikipedia.org/");
    }

    @Step
    @AfterMethod(alwaysRun = true)
    public void closeSession() {
        log.info("Closing WebDriver session for thread: " + Thread.currentThread().getId());
        WebDriverSingleton.closeDriver();
    }
}

