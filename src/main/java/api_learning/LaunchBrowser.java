package api_learning;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {

    public static void main(String[] args) {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";

        if(OS.isFamilyMac()){
            chromeDriverLocation = currentProjectLocation + "/src/main/resources/drivers/mac/chromedriver";
        }else if(OS.isFamilyWindows()){
            chromeDriverLocation = currentProjectLocation + "/src/main/resources/drivers/window/chromedriver.exe";
        }else{
            throw new IllegalArgumentException("[ERROR] Your OS is NOT supported");
        }

        if(chromeDriverLocation.isBlank()){
            throw new IllegalArgumentException("[ERROR] CAN NOT DETECT OS TYPE");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");


        WebDriver driver = new ChromeDriver(options);
        driver.get("https://vnexpress.net/");
        driver.manage().window().maximize();

        try{
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
