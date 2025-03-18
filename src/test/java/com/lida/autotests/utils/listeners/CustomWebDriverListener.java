package com.lida.autotests.utils.listeners;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Log4j2
public class CustomWebDriverListener implements WebDriverListener {

    @Override
    public void afterAccept(Alert alert) {
        log.info("Alert accepted.");
    }

    @Override
    public void afterDismiss(Alert alert) {
        log.info("Alert dismissed.");
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        log.info("Navigated to URL: {}", url);
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        log.info("Navigated back.");
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        log.info("Navigated forward.");
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        log.info("Page refreshed.");
    }

    @Override
    public void beforeFindElement(WebDriver webDriver, By by) {
        log.debug("Finding element: {}", by);
    }

    @Override
    public void afterFindElement(WebDriver webDriver, By by, WebElement result) {
        log.info("Element found: {}", by);
    }

    @Override
    public void beforeClick(WebElement element) {
        log.debug("Attempting to click: {}", getLocator(element));
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("Clicked on element: {}", getLocator(element));
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence[] keysToSend) {
        log.debug("Attempting to send keys '{}' to: {}", String.join("", keysToSend), getLocator(element));
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence[] keysToSend) {
        log.info("Typed '{}' into element: {}", String.join("", keysToSend), getLocator(element));
    }

    @Override
    public void beforeGetText(WebElement element) {
        log.debug("Getting text from element: {}", getLocator(element));
    }

    @Override
    public void afterGetText(WebElement element, String text) {
        log.info("Extracted text '{}' from element: {}", text, getLocator(element));
    }

    private String getLocator(WebElement element) {
        try {
            return element.toString().split("->")[1].replaceFirst("(?s)(.*)", "$1");
        } catch (Exception e) {
            return "Unknown Locator";
        }
    }
}
