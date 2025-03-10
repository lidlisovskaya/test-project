package com.lida.autotests.tests.smoke;

import com.lida.autotests.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "Selenium article page picture test", groups = "smoke")
public class SeleniumPictureTest extends BaseTest {

    @Test
    public void seleniumPagePictureTest() {
        wikipediaHomePage.clickSearchField();
        wikipediaHomePage.enterToSearchFieldText("selenium");
        wikipediaHomePage.clickEnterOnSearchFiled();
        seleniumPage.isSeleniumPageLoaded();
        Assert.assertTrue(seleniumPage.isMainPictureDisplayed());
    }
}
