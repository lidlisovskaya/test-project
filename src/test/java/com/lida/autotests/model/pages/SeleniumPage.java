package com.lida.autotests.model.pages;

import com.lida.autotests.core.element.Element;

public class SeleniumPage extends BasePage{

    Element pageLogo = Element.byCss(".mw-logo-wordmark");
    Element pageTitle = Element.byCss(".mw-page-title-main");
    Element mainPicture = Element.byXpath("//td[@class = 'nfobox-image']");

    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public boolean isMainPictureDisplayed() {
        return mainPicture.isDisplayed(5);
    }

    @Override
    public boolean isScreenLoaded() {
        return pageLogo.isDisplayed(5);
    }
}
