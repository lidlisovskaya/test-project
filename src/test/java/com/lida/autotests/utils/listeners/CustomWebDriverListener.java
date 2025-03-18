package com.lida.autotests.utils.listeners;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import static com.lida.autotests.utils.LoggerUtils.getNestedCallerClassMethod;

@Log4j2
public class CustomWebDriverListener implements WebDriverListener {

    @Override
    public void afterAccept(Alert alert) {
        log.info("Alert was accepted");
    }

    @Override
    public void afterDismiss(Alert alert) {
        log.info("Alert was dismissed");
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        log.info("Navigation to {} completed", url);
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        log.info("Navigate back completed");
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        log.info("Navigate forward completed");
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        log.info("The page was refreshed");
    }

    @Override
    public void beforeFindElement(WebDriver webDriver, By by) {
        log.debug("Finding webElement: {}", by);
    }

    @Override
    public void afterFindElement(WebElement element, By by, WebElement result) {
        log.info("{} Found element by locator: {}", getNestedCallerClassMethod(), by);
    }

    @Override
    public void beforeClick(WebElement webElement) {
        log.debug("{} Trying to click webElement {}", getNestedCallerClassMethod(), getLocator(webElement));
    }

    @Override
    public void afterClick(WebElement webElement) {
        log.info("{} Clicked webElement {}", getNestedCallerClassMethod(), getLocator(webElement));
    }

    @Override
    public void beforeSendKeys(WebElement webElement, CharSequence[] charSequences) {
        log.debug("Trying to change value to {} of webElement: {}", charSequences, getLocator(webElement));
    }

    @Override
    public void afterSendKeys(WebElement webElement, CharSequence[] charSequences) {
        log.info("{} Changed value to {} of webElement: {}", getNestedCallerClassMethod(), charSequences,
                getLocator(webElement));
    }

    @Override
    public void beforeGetText(WebElement webElement) {
        log.debug("{} Get text from webElement: {}", getNestedCallerClassMethod(), getLocator(webElement));
    }

    @Override
    public void afterGetText(WebElement webElement, String s) {
        log.info("{} The text of {} elements is [{}]", getNestedCallerClassMethod(), getLocator(webElement), s);
    }

    private String getLocator(WebElement element) {
        if (element == null) {
            return "UnknownElement";
        }
        String[] parts = element.toString().split("->");
        return parts.length > 1 ? parts[1].trim() : "UnknownElement";
    }
}
