package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;
import url.Urls;

import java.time.Duration;

public class JavaScriptAlert extends BaseTest implements Urls {

    private final static By jsAlertSel = By.cssSelector("[onclick='jsAlert()']");
    private final static By jsConfirmSel = By.cssSelector("[onclick='jsConfirm()']");
    private final static By jsPromptSel = By.cssSelector("[onclick='jsPrompt()']");
    private final static By resultSel = By.id("result");

    public static void main(String[] args) {
        try {
            driver.get(baseUrl.concat(jScriptSlug));

            //jScript alert
            handleAlert(driver, jsAlertSel, true);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            //jScript confirm
            handleAlert(driver, jsConfirmSel, false);
            System.out.println("Result: " + driver.findElement(resultSel).getText());

            //jScript prompt
            handleAlert(driver, jsPromptSel, "writing something para...");
            System.out.println("Result: " + driver.findElement(resultSel).getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static void handleAlert(WebDriver driver, By triggerJSAlertSel, boolean isAccepting){
        Alert alert = getAlert(driver, triggerJSAlertSel);
        System.out.println("Alert Content: " + alert.getText());
        if(isAccepting){
            alert.accept();
        }else{
            alert.dismiss();
        }
    }

    public static void handleAlert(WebDriver driver, By triggerJSAlertSel, String contentToEnter){
        Alert alert = getAlert(driver, triggerJSAlertSel);
        System.out.println("Alert Content: " + alert.getText());
        alert.sendKeys(contentToEnter);
        alert.accept();
    }
    public static Alert getAlert(WebDriver driver, By triggerJSAlertSel){
        WebElement triggerJSAlertBtnElem = driver.findElement(triggerJSAlertSel);
        triggerJSAlertBtnElem.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert;
    }
}
