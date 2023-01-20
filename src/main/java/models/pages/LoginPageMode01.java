package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageMode01 {

    private final WebDriver driver;
    private final static By usernameSel = By.id("username");
    private final static By passwordSel =  By.id("password");
    private final static By loginBtnSel = By.cssSelector("[type='Submit']");

    public LoginPageMode01(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement userName(){
        return driver.findElement(usernameSel);
    }
    public WebElement password(){
        return driver.findElement(passwordSel);
    }
    public WebElement login(){
        return driver.findElement(loginBtnSel);
    }

}
