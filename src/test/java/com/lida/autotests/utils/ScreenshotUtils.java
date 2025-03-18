package com.lida.autotests.utils;

import com.lida.autotests.core.driver.WebDriverSingleton;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;

@Log4j2
public class ScreenshotUtils {

    public static final File TESTNG_DIRECTORY = new File("target/surefire-reports");

    public static InputStream makeScreenshot() {
        log.info("Make screenshot");
        byte[] imgBytes = ((TakesScreenshot) WebDriverSingleton.getDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(imgBytes);
    }

    public static void attachScreenshotToAllure(String name) {
            Allure.addAttachment("FAILED_" + name, "image/png", makeScreenshot(), ".png");
            log.info("Attached screenshot to Allure");
        }

    public static File saveScreenshot() {
        File screenshot = null;
        try {
            if (!TESTNG_DIRECTORY.exists()) {
                TESTNG_DIRECTORY.mkdirs();
            }
            screenshot = File.createTempFile("screenshot", ".png", TESTNG_DIRECTORY);
            try (FileOutputStream stream = new FileOutputStream(screenshot)) {
                stream.write(makeScreenshot().readAllBytes());
            }
        } catch (Throwable e) {
            log.info("Unable to save screenshot", e);
        }
        return screenshot;
    }
}
