package Controller;

import Model.User;
import View.LoginView;

public interface LoginStrategy {
    public void login(boolean verified, User user, LoginView loginView);
}
