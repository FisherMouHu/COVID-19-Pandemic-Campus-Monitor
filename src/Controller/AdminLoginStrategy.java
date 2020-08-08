package Controller;

import Model.User;
import View.AdminView;
import View.LoginView;

import javax.swing.*;

public class AdminLoginStrategy implements LoginStrategy {
    @Override
    public void login(boolean verified, User user, LoginView loginView) {
        if (verified) {
            JOptionPane.showMessageDialog(null, "Login Successful! ");

            loginView.getLoginFrame().dispose();

            AdminView adminView = new AdminView();
            AdminController adminController = new AdminController(user, adminView);
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Email or Password! ");
        }
    }
}
