package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitElementEnabled;
import tests.BaseTest;
import url.Urls;

import java.time.Duration;

public class DynamicControl extends BaseTest implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl.concat(dynamicControlSlug));

            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");
            By loadingInputSel = By.id("loading");

            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement checkboxElem = checkboxFormElem.findElement(By.tagName("input"));
            WebElement inputElem = inputFormElem.findElement(By.tagName("input"));

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            if(!checkboxElem.isSelected()){
                jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: blue; outline: 4px solid red;');", checkboxElem);
                checkboxElem.click();
            }

            if(!inputElem.isEnabled()){
                WebElement enableBtnElem = inputFormElem.findElement(By.tagName("button"));
                jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: blue; outline: 4px solid red;');", enableBtnElem);
                enableBtnElem.click();
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            //wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingInputSel));
            //wait.until(new WaitElementEnabled(By.cssSelector("#input-example input")));
            wait.until(new WaitElementEnabled(inputFormSel, By.tagName("input")));
            jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: blue; outline: 4px solid red;');", inputElem);
            inputElem.sendKeys("viet tao lao vai dong...");
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
