package Controller;

import Model.User;
import View.LoginView;
import View.NormalUserView;

import javax.swing.*;

public class NormalUserLoginStrategy implements LoginStrategy {
    @Override
    public void login(boolean verified, User user, LoginView loginView) {
        if (verified) {
            JOptionPane.showMessageDialog(null, "Login Successful! ");

            loginView.getLoginFrame().dispose();

            NormalUserView normalUserView = new NormalUserView();
            NormalUserController normalUserController = new NormalUserController(user, normalUserView);
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Email or Password! ");
        }
    }
}
