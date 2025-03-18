package com.lida.autotests.model.pages;

import com.lida.autotests.core.element.Element;
import io.qameta.allure.Step;

public class SeleniumPage extends BasePage{

    Element pageLogo = Element.byCss(".mw-logo-wordmark");
    Element pageTitle = Element.byCss(".mw-page-title-main");
    Element mainPicture = Element.byXpath("//td[@class = 'nfobox-image']");

    @Step("step")
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    @Step("step")
    public boolean isMainPictureDisplayed() {
        return mainPicture.isDisplayed(5);
    }

    @Step("step")
    @Override
    public boolean isScreenLoaded() {
        return pageLogo.isDisplayed(5);
    }
}
