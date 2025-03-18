package com.lida.autotests.core.element;

import com.lida.autotests.core.driver.WebDriverSingleton;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.lida.autotests.utils.CommonConstants.DEFAULT_TIME_OUT_IN_SECONDS;

@Log4j2
public class Element {

  private final By by;
  private final LocatorManager locatorManager;
  private final WebDriver webDriver = WebDriverSingleton.getDriver();

  private Element(By by, LocatorManager locatorManager) {
    this.by = by;
    this.locatorManager = locatorManager;
  }

  public static Element byXpath(String xpath, Object... params) {
    String locator = String.format(xpath, params);
    log.info("Search element by xpath. Locator is " + locator);
    return new Element(By.xpath(locator),
            LocatorManager.byXpath(xpath, params));
  }

  public static Element byCss(String css, Object... params) {
    String locator = String.format(css, params);
    log.info("Search element by css. Locator is " + locator);
    return new Element(By.cssSelector(String.format(css, params)),
            LocatorManager.byCss(css, params));
  }

  public void click() {
    log.info(parseAction("%s '%s' %s"));
    waitForClickable();
    nativeClick();
  }

  public void type(String value) {
    log.info(parseAction("%s in '%s'") + " with a value:" + value);
    WebElement element = getWrappedWebElement();
    element.sendKeys(value);
  }

  private void nativeClick() {
    log.info(parseAction("%s '%s' %s"));
    new Actions(webDriver).click(getWrappedWebElement()).build().perform();
  }

  public void enter() {
    log.info(String.format("ENTER", webDriver));
    new Actions(webDriver).sendKeys(Keys.ENTER).build().perform();
  }

  public boolean isDisplayed(int timeOutInSeconds) {
    log.info("Check " + parseActionFromStateElement("'%s' %s %s"));
    boolean isDisplayed;
    try {
      isDisplayed = waitForVisibility(timeOutInSeconds) != null;
    } catch (TimeoutException e) {
      isDisplayed = false;
    }
    return isDisplayed;
  }

  public WebElement waitForPresence() {
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
            .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public WebElement waitForVisibility(int timeout) {
    log.info("Wait element will be visible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
            .until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public WebElement waitForVisibility() {
    return waitForVisibility(DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public WebElement waitForClickable() {
    log.info("Wait element will be clickable");
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
            .until(ExpectedConditions.elementToBeClickable(by));
  }

  public WebElement getWrappedWebElement() {
    return waitForPresence();
  }

  public String getText() {
    String text = waitForVisibility().getText();
    log.info("Text from Element is " + text);
    return text;
  }

  public By getBy() {
    return by;
  }

  private LocatorManager getLocatorManager() {
    return locatorManager;
  }

  private static String parseAction(String pattern) {
    String[] contextKeyWords = parseActionContext();
    String actionType = upperCaseFirstLetter(contextKeyWords[0]);
    String actionObject = parseActionObject(contextKeyWords);
    String actionObjectType = contextKeyWords[contextKeyWords.length - 1].toLowerCase();
    return String.format(pattern, actionType, actionObject, actionObjectType);
  }

  private static String[] parseActionContext() {
    for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
      String className = stackTraceElement.getClassName();
      if (className.endsWith("Screen") || className.endsWith("Page")) {
        return stackTraceElement.getMethodName()
                .split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
      }
    }
    return null;
  }

  private static String upperCaseFirstLetter(String contextKeyWord) {
    return contextKeyWord.substring(0, 1).toUpperCase() + contextKeyWord.substring(1);
  }

  private static String parseActionObject(String[] actions) {
    StringBuilder actionObject = new StringBuilder();
    for (int i = 1; i < actions.length - 1; i++) {
      actionObject.append(actions[i]).append(" ");
    }
    return actionObject.toString().trim();
  }

  private static String parseActionFromStateElement(String pattern) {
    String[] contextKeyWords = parseActionContext();
    String actionType = contextKeyWords[0].toLowerCase();
    String actionObject = parseActionObject(contextKeyWords);
    String actionObjectType = contextKeyWords[contextKeyWords.length - 1].toLowerCase();
    return String.format(pattern, actionObject, actionType, actionObjectType);
  }
}