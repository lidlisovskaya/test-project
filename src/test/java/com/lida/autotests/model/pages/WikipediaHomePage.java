package com.lida.autotests.model.pages;

import com.lida.autotests.core.element.Element;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class WikipediaHomePage {

    private Element searchField = Element.byXpath("//input[@id ='searchInput']");

    public void clickSearchField() {
        searchField.click();
    }

    public void enterToSearchFieldText(String searchText) {
        searchField.type(searchText);
    }

    public void clickEnterOnSearchFiled() {
        searchField.enter();
}
}
