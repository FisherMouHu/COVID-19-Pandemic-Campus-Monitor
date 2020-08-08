package Controller;

import Model.User;
import View.NewNotificationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class NewNotificationController {
    private Connection connection;
    private PreparedStatement statement;

    private User admin;
    private NewNotificationView newNotificationView;

    public NewNotificationController(User admin, NewNotificationView newNotificationView) {
        this.admin = admin;
        this.newNotificationView = newNotificationView;

        init();
    }

    private void init() {
        newNotificationView.getPublishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publishNotification();
            }
        });

        newNotificationView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newNotificationView.getNotificationFrame().dispose();
            }
        });
    }

    private void publishNotification() {
        // Create a ID
        String id = UUID.randomUUID().toString();

        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (newNotificationView.getTitleField().getText() == null || newNotificationView.getTitleField().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Title! ");

            return;
        }

        if (newNotificationView.getContentArea().getText() == null || newNotificationView.getContentArea().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Detail Info! ");

            return;
        }

        connectDB();

        String sql = "INSERT INTO notifications VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, newNotificationView.getTitleField().getText());
            statement.setString(3, newNotificationView.getContentArea().getText());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getName());
            statement.setTimestamp(6, ts);

            int resultSet = statement.executeUpdate();

            if (resultSet > 0) {
                JOptionPane.showMessageDialog(null, "Notification Published! ");
            }
            else {
                JOptionPane.showMessageDialog(null, "Notification Publish Failed! ");
            }

            newNotificationView.getNotificationFrame().dispose();
        } catch (Exception ep) {
            ep.printStackTrace();
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
        } catch (ClassNotFoundException e) {
            System.out.println("Driver cannot be Found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
