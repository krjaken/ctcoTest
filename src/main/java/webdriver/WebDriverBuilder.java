package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverBuilder {
    private static final ThreadLocal<WebDriverBuilder> webFactoryInstance = new ThreadLocal<WebDriverBuilder>();
    private static final ThreadLocal<WebDriver> driverInstance = new ThreadLocal<WebDriver>();

    public static WebDriverBuilder getInstance() {
        if (webFactoryInstance.get() == null) {
            synchronized (WebDriverBuilder.class) {
                webFactoryInstance.set(new WebDriverBuilder());
            }
        }
        return webFactoryInstance.get();
    }

    private WebDriverBuilder() {
        log.info("Create Instance WebDriverFactory");
        createDriverInstance();
    }

    public WebDriver getDriver() {
        if (driverInstance.get() == null) {
            createDriverInstance();
        }
        return driverInstance.get();
    }

    public void quitDriver() {
        driverInstance.get().quit();
        driverInstance.remove();
    }

    private void createDriverInstance() {
        log.info("Create Instance WebDriver");

        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver(prepareChrome(WebDriverManager.chromedriver().getBinaryPath()));

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

        driverInstance.set(webDriver);
    }

    private ChromeOptions prepareChrome(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("showChromedriverLog ", true);
        options.setCapability("setWebContentsDebuggingEnabled", true);
        options.setCapability("recreateChromeDriverSessions", true);
        options.setCapability("pageLoadStrategy", "normal");

        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        return options;
    }
}
