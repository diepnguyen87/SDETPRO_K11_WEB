package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMode02 {

    private final WebDriver driver;
    private final static By usernameSel = By.id("username");
    private final static By passwordSel = By.id("password");
    private final static By loginBtnSel = By.cssSelector("[type='Submit']");

    public LoginPageMode02(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUserName(String username) {
        driver.findElement(usernameSel).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordSel).sendKeys(password);
    }

    public void login() {
        driver.findElement(loginBtnSel).click();
    }

}
