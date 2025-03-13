package com.lida.autotests.tests.smoke;

import com.lida.autotests.model.pages.SeleniumPage;
import com.lida.autotests.model.pages.WikipediaHomePage;
import com.lida.autotests.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaSearchTest extends BaseTest {

    private SeleniumPage seleniumPage;

    @Test(description = "Wikipedia search test")
    public void wikipediaSearchTest() {
        WikipediaHomePage wikipediaHomePage = new WikipediaHomePage();
        wikipediaHomePage.isScreenLoaded();
        wikipediaHomePage.clickSearchField();
        wikipediaHomePage.enterToSearchFieldText("selenium");
        seleniumPage = wikipediaHomePage.clickEnterOnSearchFiled();
        seleniumPage.isScreenLoaded();
        Assert.assertEquals(seleniumPage.getPageTitleText(), "Selenium");
    }
}
