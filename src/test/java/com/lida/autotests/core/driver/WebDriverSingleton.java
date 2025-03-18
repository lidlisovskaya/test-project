package com.lida.autotests.core.driver;

import com.lida.autotests.utils.listeners.CustomWebDriverListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


@Log4j2
public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            log.info("Creating a new WebDriver instance for thread: " + Thread.currentThread().getId());
            WebDriver baseDriver = WebDriverFactory.getWebDriver();
            WebDriver decoratedDriver = new EventFiringDecorator<>(new CustomWebDriverListener()).decorate(baseDriver);
            driverThreadLocal.set(decoratedDriver);
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
