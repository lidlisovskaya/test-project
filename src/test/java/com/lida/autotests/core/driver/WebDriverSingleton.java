package com.lida.autotests.core.driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;


@Log4j2
public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            log.info("Creating a new WebDriver instance for thread: " + Thread.currentThread().getId());
            driverThreadLocal.set(WebDriverFactory.getWebDriver());
        }
        return driverThreadLocal.get();
    }

    public static synchronized void closeDriver() {
        log.info("Closing WebDriver for thread: " + Thread.currentThread().getId());
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.error("Error while quitting WebDriver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
