package com.lida.autotests.utils;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.ITestResult;

import java.util.Date;

import static com.lida.autotests.utils.ScreenshotUtils.saveScreenshot;

public class ReportPortalListener extends ReportPortalTestNGListener {

  @Override
  public void onTestFailure(ITestResult testResult) {
    ReportPortal.emitLog("Save screenshot", LogLevel.INFO.name(), new Date(), saveScreenshot());
    super.onTestFailure(testResult);
  }

  @Override
  public void onConfigurationFailure(ITestResult testResult) {
    saveScreenshot();
    super.onConfigurationFailure(testResult);
  }
}
