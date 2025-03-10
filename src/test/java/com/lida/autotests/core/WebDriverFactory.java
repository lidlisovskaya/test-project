package com.lida.autotests.core;

import com.lida.autotests.utils.FileUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static com.lida.autotests.utils.CommonConstants.DEFAULT_TIME_OUT_IN_SECONDS;
import static com.lida.autotests.utils.CommonConstants.SHORT_TIME_OUT_IN_SECONDS;

@Log4j2
public class WebDriverFactory {

    private static String HOST = "http://localhost:4444";
    private static String DOWNLOADS_PATH = FileUtils.getDownloadedFilePath();

    public static WebDriver getWebDriver() {
        RemoteWebDriver driver = null;
        switch (System.getProperty("browser")) {
            case "chrome":
                ChromeOptions options = getChromeOptions();
                try {
                    driver = new RemoteWebDriver(new URL(HOST), options);
                    driver.manage().timeouts()
                            .implicitlyWait(Duration.ofSeconds(SHORT_TIME_OUT_IN_SECONDS));
                    driver.manage().timeouts()
                            .pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS));
                    driver.manage().timeouts()
                            .scriptTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS));
                    driver.manage().window().maximize();
                    log.info("Current browser is Chrome. Screen resolution is: {}", driver.manage().window().getSize());
                } catch (MalformedURLException e) {
                    log.info("URL is not correct ");
                }
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL(HOST), firefoxOptions);
                    driver.manage().window().maximize();
                    log.info("Current browser is FireFox. Screen resolution is: {}", driver.manage().window().getSize());
                } catch (MalformedURLException e) {
                    log.info("URL is not correct ");
                }
                break;
            default:
                throw new RuntimeException("No support ");
        }
        return driver;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", DOWNLOADS_PATH);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/vnd.ms-excel.12");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
                "application/vnd.ms-excel.12");
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }

    private static ChromeOptions getChromeOptions() {
        DesiredCapabilities capabilities;
        capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-popup-blocking");
        HashMap<String, Object> chromePref = new HashMap<>();
        chromePref.put("profile.default_content_settings.popups", 0);
        chromePref.put("download.directory_upgrade", true);
        chromePref.put("download.default_directory", DOWNLOADS_PATH);
        options.setExperimentalOption("prefs", chromePref);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        return options;
    }
}
