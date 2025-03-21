package com.lida.autotests.core.driver;

import com.lida.autotests.utils.FileUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
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

@Log4j2
public class WebDriverFactory {

    private static String HOST = "http://localhost:4444";
    private static String DOWNLOADS_PATH = FileUtils.getDownloadedFilePath();

    public static WebDriver getWebDriver() {
        RemoteWebDriver driver = null;
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions options = getChromeOptions();
                driver = createDriver(options);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driver = createDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("No supported browser: " + browser);
        }
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().window().maximize();
        }

        log.info("Created WebDriver instance: " + driver);
        return driver;
    }

    private static RemoteWebDriver createDriver(MutableCapabilities options) {
        try {
            return new RemoteWebDriver(new URL(HOST), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
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
