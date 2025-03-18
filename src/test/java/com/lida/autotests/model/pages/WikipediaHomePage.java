package com.lida.autotests.model.pages;

import com.lida.autotests.core.element.Element;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class WikipediaHomePage extends BasePage{

    private Element searchField = Element.byXpath("//input[@id ='searchInput']");
    private Element wikiLogo = Element.byCss(".central-textlogo");

    @Step("step")
    public WikipediaHomePage openHomePage() {
        webDriver.get("https://www.wikipedia.org/");
        return new WikipediaHomePage();
    }

    @Step("step")
    public boolean IsWikiLogoDisplayed() {
         return wikiLogo.isDisplayed(5);
    }

    @Step("step")
    public void clickSearchField() {
        searchField.click();
    }

    @Step("step")
    public void enterToSearchFieldText(String searchText) {
        searchField.type(searchText);
    }

    @Step("step")
    public SeleniumPage clickEnterOnSearchFiled() {
        searchField.enter();
        return new SeleniumPage();
}

    @Override
    public boolean isScreenLoaded() {
        return IsWikiLogoDisplayed();
    }
}
