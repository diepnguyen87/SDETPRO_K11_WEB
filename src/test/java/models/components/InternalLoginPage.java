package models.components;

public class InternalLoginPage extends LoginPage {

    @Override
    public String username() {
        return "InternalLoginPage | UserName";
    }

    @Override
    public String password() {
        return "InternalLoginPage | Password";
    }

    @Override
    public String loginBtn() {
        return "InternalLoginPage | Login Button";
    }
}
