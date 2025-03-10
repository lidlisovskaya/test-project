package com.lida.autotests.core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Log4j2
public class WebDriverSingleton {

    private WebDriver wrappedDriver;
    private static ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();

    private WebDriverSingleton() {
        wrappedDriver = WebDriverFactory.getWebDriver();
    }

    public static synchronized WebDriverSingleton getInstance() {
        if (instance.get() == null) {
            instance.set(new WebDriverSingleton());
        }
        return instance.get();
    }

    public WebDriver getDriver() {
        return wrappedDriver;
    }

    public void closeDriver() {
        log.info("Stop browser");
        try {
            wrappedDriver.quit();
        } finally {
            instance.remove();
        }
    }
}
