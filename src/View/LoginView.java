package View;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    // Using Swing Framework to Display UI
    private JFrame loginFrame;
    private JPanel emailPanel;
    private JPanel passwordPanel;
    private JPanel identityPanel;
    private JPanel loginPanel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel identityLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton adminButton;
    private JRadioButton userButton;
    private JButton loginButton;

    public LoginView() {
        loginFrame = new JFrame("SCU Pandemic Assistant");

        loginFrame.getContentPane().setLayout(new GridLayout(4, 1, 5, 5));

        // Create UI Elements
        emailPanel = new JPanel();
        passwordPanel = new JPanel();
        identityPanel = new JPanel();
        loginPanel = new JPanel();

        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");

        emailField = new JTextField();
        emailField.setColumns(12);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);

        // For Test Purpose
        emailField.setText("hma@scu.edu");
        passwordField.setText("19951124MaHu");

        identityLabel = new JLabel("Choose Identity: ");

        adminButton = new JRadioButton("Administrator");
        userButton = new JRadioButton("Student / Faculty");

        loginButton = new JButton("Login");

        // Add the Elements to Frame
        loginFrame.add(emailPanel);

        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        loginFrame.add(passwordPanel);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        loginFrame.add(identityPanel);

        identityPanel.add(identityLabel);
        identityPanel.add(adminButton);
        identityPanel.add(userButton);

        ButtonGroup group = new ButtonGroup();

        group.add(adminButton);
        group.add(userButton);

        loginFrame.add(loginPanel);

        loginPanel.add(loginButton);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JRadioButton getAdminButton() {
        return adminButton;
    }

    public JRadioButton getUserButton() {
        return userButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
