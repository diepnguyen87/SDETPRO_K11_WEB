package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();

        try {
            driver.get(baseUrl.concat(iframeSlug));
            WebElement iframeElem = driver.findElement(By.cssSelector("[id$='ifr']"));
            driver.switchTo().frame(iframeElem);
            WebElement bodyIframeElem = driver.findElement(By.id("tinymce"));
            bodyIframeElem.click();
            bodyIframeElem.clear();
            bodyIframeElem.sendKeys("Test vai dong...");
            Thread.sleep(5000);

            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium"));
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
