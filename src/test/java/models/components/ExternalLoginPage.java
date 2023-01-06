package models.components;

public class ExternalLoginPage extends LoginPage{

    @Override
    public String username() {
        return "ExternalLoginPage | Username";
    }

    @Override
    public String password() {
        return "ExternalLoginPage | Password";
    }

    @Override
    public String loginBtn() {
        return "ExternalLoginPage | Login Button";
    }
}
