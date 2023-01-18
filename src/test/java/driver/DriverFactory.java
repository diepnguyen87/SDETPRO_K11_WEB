package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    public static WebDriver getChromeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";

        if (OS.isFamilyMac()) {
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/mac/chromedriver";
        } else if (OS.isFamilyWindows()) {
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/window/chromedriver.exe";
        } else {
            throw new IllegalArgumentException("[ERROR] Your OS is NOT supported");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--user-data-dir=/Users/diep.nguyen/Library/Application Support/Google/Chrome/Default");
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    public WebDriver getDriver(String browserName) {
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.ANY);
            BrowserType browserType = null;
            try {
                browserType = BrowserType.valueOf(browserName);
            } catch (Exception e) {
                throw new IllegalArgumentException("[ERROR]" + browserType.getName() + " does NOT exist");
            }
            switch (browserType) {
                case FIREFOX:
                    desiredCapabilities.setBrowserName(BrowserType.FIREFOX.getName());
                    break;
                case SAFARI:
                    desiredCapabilities.setBrowserName(BrowserType.SAFARI.getName());
                    break;
                default:
                    desiredCapabilities.setBrowserName(BrowserType.CHROME.getName());
                    break;
            }
            try {
                String hub = "http://localhost:4444/wd/hub";
                driver = new RemoteWebDriver(new URL(hub), desiredCapabilities);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
