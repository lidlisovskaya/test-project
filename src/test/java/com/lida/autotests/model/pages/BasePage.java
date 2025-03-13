package com.lida.autotests.model.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver webDriver;

    public abstract boolean isScreenLoaded();

}
