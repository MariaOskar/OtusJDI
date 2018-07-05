package com.blazedemo;

import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory;
import com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.sun.javafx.util.Logging;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.otus.utils.PropertyHelper;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;

import static com.epam.jdi.uitests.web.settings.WebSettings.useDriver;

public class BaseTest extends TestNGBase {
    public static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();
    @BeforeMethod(description = "Создание драйвера и инициализация сайта")
    public static void setUp() throws IOException {
        String flag = PropertyHelper.getProperty("test.properties","test.traffic");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName( PropertyHelper.getProperty("test.properties", "selenoid.browser.name") );
        capabilities.setVersion( PropertyHelper.getProperty("test.properties", "selenoid.browser.version") );
        capabilities.setCapability("enableVNC",true);
        if(flag!=null && flag.equals("1")) {
            LoggingPreferences loggingPreferences = new LoggingPreferences();
            loggingPreferences.enable(LogType.PERFORMANCE, Level.INFO);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
        }
        driverContainer.set(new RemoteWebDriver(
                URI.create(PropertyHelper.getProperty("test.properties", "selenoid.uri")).toURL(),
                capabilities
        ));

        WebSite.init(useDriver(()->driverContainer.get()),BlazeDemoSite.class);
    }
}