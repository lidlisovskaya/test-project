package com.lida.autotests.tests.smoke;

import com.lida.autotests.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "Wikipedia search test", groups = "smoke")
public class WikipediaSearchTest extends BaseTest {

    @Test
    public void wikipediaSearchTest() {
    wikipediaHomePage.clickSearchField();
    wikipediaHomePage.enterToSearchFieldText("selenium");
    wikipediaHomePage.clickEnterOnSearchFiled();
    seleniumPage.isSeleniumPageLoaded();
        Assert.assertEquals(seleniumPage.getPageTitleText(), "Selenium");
    }
}
