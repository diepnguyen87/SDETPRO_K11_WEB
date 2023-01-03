package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        try{
            driver.get(baseUrl.concat(loginSlug));
            By taolaoSel = By.cssSelector("#taolao");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(taolaoSel));
            wait.until(new WaitMoreThanOneTab());
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
