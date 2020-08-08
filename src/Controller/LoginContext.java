package Controller;

import Model.User;
import View.LoginView;

public class LoginContext {
    private LoginStrategy loginStrategy;

    public LoginContext(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public void executeStrategy(boolean verified, User user, LoginView loginView) {
        loginStrategy.login(verified, user, loginView);
    }
}
