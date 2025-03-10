package com.lida.autotests.tests;


import com.lida.autotests.core.WebDriverSingleton;
import com.lida.autotests.model.pages.WikipediaHomePage;
import com.lida.autotests.model.pages.SeleniumPage;
import com.lida.autotests.utils.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.lida.autotests.utils.FileUtils.cleanOldReports;

@Log4j2
@Listeners({TestListener.class})
public class BaseTest {

    public WikipediaHomePage wikipediaHomePage = new WikipediaHomePage();
    public SeleniumPage seleniumPage = new SeleniumPage();

    @BeforeMethod
    public void startSession() {
        log.info("Starting session");
        WebDriver webDriver = WebDriverSingleton.getInstance().getDriver();
        log.info("Clear local storage");
        cleanOldReports();
        webDriver.get("https://www.wikipedia.org/");
    }

    @AfterSuite(alwaysRun = true)
    public void closeSession(ITestResult test) {
        WebDriverSingleton.getInstance().closeDriver();
    }
}
