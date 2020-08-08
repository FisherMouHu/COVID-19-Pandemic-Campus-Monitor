package Controller;

import Model.*;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    private User user;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private LoginView loginView;

    // Create a Strategy and a Context
    // Use Strategy Pattern to Switch between Two Different Login Strategies ( Admin Login & Normal User Login )
    private LoginStrategy loginStrategy;
    private LoginContext loginContext;

    // Create a User Factory
    // Use Factory Method Pattern to Decide Which User to Create ( Admin / Normal User )
    private UserFactory userFactory;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        init();
    }

    private void init() {
        // Add Listener to Elements
        loginView.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        boolean verified;

        // Connect to Database
        connectDB();

        if (loginView.getAdminButton().isSelected()) {
            verified = loginVerify("admins", loginView.getEmailField().getText(), loginView.getPasswordField().getText());

            // Strategy ! ! !
            loginStrategy = new AdminLoginStrategy();

            loginContext = new LoginContext(loginStrategy);

            loginContext.executeStrategy(verified, user, loginView);
        }
        else if (loginView.getUserButton().isSelected()) {
            verified = loginVerify("users", loginView.getEmailField().getText(), loginView.getPasswordField().getText());

            // Strategy ! ! !
            loginStrategy = new NormalUserLoginStrategy();

            loginContext = new LoginContext(loginStrategy);

            loginContext.executeStrategy(verified, user, loginView);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please Choose Your Identity！");
        }
    }

    private void connectDB() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_pandemic?useSSL=false&serverTimezone=UTC";
        String name = "root";
        String pwd = "19951124";

        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url, name, pwd);

            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver cannot be Found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean loginVerify(String identity, String email, String password) {
        boolean matched = false;

        try {
            // Use "BINARY" to Distinguish Upper & Lower Case
            String sql = "SELECT * FROM " + identity + " WHERE BINARY email = '" + email + "' AND BINARY password = '" + password + "';";

            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                if (identity.equals("admins")) {
                    // Factory Method ! ! !
                    userFactory = new AdminFactory();

                    user = userFactory.createUser(email, password, resultSet.getString(3));
                }
                else {
                    // Factory Method ! ! !
                    userFactory = new NormalUserFactory();

                    user = userFactory.createUser(email, password, resultSet.getString(3));
                }

                matched = true;
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return matched;
    }
}
