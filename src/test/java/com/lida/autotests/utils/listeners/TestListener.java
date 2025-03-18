package com.lida.autotests.utils.listeners;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.lida.autotests.utils.ScreenshotUtils.attachScreenshotToAllure;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult test) {
        log.info(test.getMethod().getMethodName() + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult test) {
        log.info(test.getMethod().getMethodName() + " test is succeed.");
    }

    @Override
    public void onTestFailure(ITestResult test) {
        attachScreenshotToAllure("Failed" + test.getTestName());
        attachLogToAllure("Failure logs for " + test.getMethod().getMethodName());
        log.info(test.getMethod().getMethodName() + " test is failed.");
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String attachLogToAllure(String message) {
        return message + "\n" + "Check the console logs for more details.";
    }
}

