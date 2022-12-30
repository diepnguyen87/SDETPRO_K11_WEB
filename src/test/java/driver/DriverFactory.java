package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getDriver() {
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
        options.addArguments("--user-data-dir=/Users/diep.nguyen/Library/Application Support/Google/Chrome/Default");
        //options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
