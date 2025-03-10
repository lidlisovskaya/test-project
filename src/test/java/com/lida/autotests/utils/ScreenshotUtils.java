package com.lida.autotests.utils;

import com.lida.autotests.core.WebDriverSingleton;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;

@Log4j2
public class ScreenshotUtils {

    public static final File TESTNG_DIRECTORY = new File("allure-results");

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] makeScreenshot() {
        log.info("Attach screenshot to Allure");
        return ((TakesScreenshot) WebDriverSingleton.getInstance()
                .getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    public static File saveScreenshot() {
        File screenshot = null;
        try {
            if (!TESTNG_DIRECTORY.exists()) {
                TESTNG_DIRECTORY.mkdirs();
            }
            screenshot = File.createTempFile("screenshot", ".png", TESTNG_DIRECTORY);
            try (FileOutputStream stream = new FileOutputStream(screenshot)) {
                stream.write(makeScreenshot());
            }
        } catch (Throwable e) {
            log.info("Unable to save screenshot", e);
        }
        return screenshot;
    }
}
