package com.lida.autotests.tests.smoke;

import com.lida.autotests.model.pages.SeleniumPage;
import com.lida.autotests.model.pages.WikipediaHomePage;
import com.lida.autotests.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumPictureTest extends BaseTest {

    private SeleniumPage seleniumPage;

    @Test(description = "Selenium article page picture test")
    public void seleniumPagePictureTest() {
        WikipediaHomePage wikipediaHomePage = new WikipediaHomePage();
        wikipediaHomePage.isScreenLoaded();
        wikipediaHomePage.clickSearchField();
        wikipediaHomePage.enterToSearchFieldText("selenium");
        seleniumPage = wikipediaHomePage.clickEnterOnSearchFiled();
        seleniumPage.isScreenLoaded();
        Assert.assertTrue(seleniumPage.isMainPictureDisplayed());
    }
}
