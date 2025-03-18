package com.lida.autotests.core.element;

import com.lida.autotests.core.driver.WebDriverSingleton;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class Element {

  private final By by;
  private final WebDriver webDriver;
  private final WebDriverWait wait;

  public Element(By by) {
    this.by = by;
    this.webDriver = WebDriverSingleton.getDriver();
    this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); // Explicit wait
  }

  public static Element byXpath(String xpath, Object... params) {
    String locator = String.format(xpath, params);
    log.info("Search element by xpath: " + locator);
    return new Element(By.xpath(locator));
  }

  public static Element byCss(String css, Object... params) {
    String locator = String.format(css, params);
    log.info("Search element by css: " + locator);
    return new Element(By.cssSelector(locator));
  }

  public WebElement getElement() {
    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public void click() {
    log.info("Clicking element: " + by.toString());
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
    element.click();
  }

  public void type(String text) {
    log.info("Typing text '" + text + "' into element: " + by.toString());
    WebElement element = getElement();
    element.clear();
    element.sendKeys(text);
  }

  public String getText() {
    return getElement().getText();
  }


  public void enter() {
    log.info(String.format("ENTER", webDriver));
    new Actions(webDriver).sendKeys(Keys.ENTER).build().perform();
  }

  public boolean isDisplayed(int timeOutInSeconds) {
    boolean isDisplayed;
    try {
      isDisplayed = waitForVisibility(timeOutInSeconds) != null;
    } catch (TimeoutException e) {
      isDisplayed = false;
    }
    return isDisplayed;
  }

  public WebElement waitForVisibility(int timeout) {
    log.info("Wait element will be visible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
            .until(ExpectedConditions.visibilityOfElementLocated(by));
  }
}