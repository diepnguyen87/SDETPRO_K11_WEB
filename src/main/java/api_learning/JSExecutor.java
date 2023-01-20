package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import url.Urls;

public class JSExecutor extends BaseTest implements Urls {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl.concat(floatingMenuSlug));
            Thread.sleep(5000);
            JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
            jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(5000);
            jsExcutor.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
