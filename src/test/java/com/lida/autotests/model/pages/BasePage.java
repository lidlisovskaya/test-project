package com.lida.autotests.model.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver webDriver;

    @Step("Screen loading")
    public abstract boolean isScreenLoaded();

}
