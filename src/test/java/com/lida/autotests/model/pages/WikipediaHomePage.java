package com.lida.autotests.model.pages;

import com.lida.autotests.core.element.Element;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class WikipediaHomePage extends BasePage{

    private Element searchField = Element.byXpath("//input[@id ='searchInput']");
    private Element wikiLogo = Element.byCss(".central-textlogo");

    public WikipediaHomePage openHomePage() {
        webDriver.get("https://www.wikipedia.org/");
        return new WikipediaHomePage();
    }

    public boolean IsWikiLogoDisplayed() {
         return wikiLogo.isDisplayed(5);
    }

    public void clickSearchField() {
        searchField.click();
    }

    public void enterToSearchFieldText(String searchText) {
        searchField.type(searchText);
    }

    public SeleniumPage clickEnterOnSearchFiled() {
        searchField.enter();
        return new SeleniumPage();
}

    @Override
    public boolean isScreenLoaded() {
        return IsWikiLogoDisplayed();
    }
}
