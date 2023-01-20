package models.pages;

import models.components.LoginFormComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageMode03 {

    private final WebDriver driver;

    public LoginPageMode03(WebDriver driver) {
        this.driver = driver;
    }

    public LoginFormComponent loginFormComp(){
        return new LoginFormComponent(driver);
    }
}
