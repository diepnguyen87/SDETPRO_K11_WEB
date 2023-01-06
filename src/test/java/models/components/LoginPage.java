package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class LoginPage {

    public void login(){
        System.out.println(username());
        System.out.println(password());
        System.out.println(loginBtn());
    }

    public void verifyLoginSuccess(){
        System.out.println("Verifying dashboard displaying");
    }
    public abstract String username();
    public abstract String password();
    public abstract String loginBtn();

}
