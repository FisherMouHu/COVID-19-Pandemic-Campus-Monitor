package Controller;

import Model.Admin;
import Model.User;
import View.AdminView;
import View.NewRumorTipView;

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

public class NewRumorTipController {
    private Connection connection;
    private PreparedStatement statement;

    private User admin;
    private NewRumorTipView newRumorTipView;

    public NewRumorTipController(User admin, NewRumorTipView newRumorTipView) {
        this.admin = admin;
        this.newRumorTipView = newRumorTipView;

        init();
    }

    private void init() {
        newRumorTipView.getPublishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publishRumorTip();
            }
        });

        newRumorTipView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newRumorTipView.getRumorTipFrame().dispose();
            }
        });
    }

    private void publishRumorTip() {
        // Create a ID
        String id = UUID.randomUUID().toString();

        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (newRumorTipView.getTitleField().getText() == null || newRumorTipView.getTitleField().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Title! ");

            return;
        }

        if (newRumorTipView.getContentArea().getText() == null || newRumorTipView.getContentArea().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Detail Info! ");

            return;
        }

        connectDB();

        if (newRumorTipView.getRumorButton().isSelected()) {
            String sql = "INSERT INTO rumors VALUES (?, ?, ?, ?, ?, ?)";

            try {
                statement = connection.prepareStatement(sql);

                statement.setString(1, id);
                statement.setString(2, newRumorTipView.getTitleField().getText());
                statement.setString(3, newRumorTipView.getContentArea().getText());
                statement.setString(4, admin.getEmail());
                statement.setString(5, admin.getName());
                statement.setTimestamp(6, ts);

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Rumor Published! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Rumor Publish Failed! ");
                }

                newRumorTipView.getRumorTipFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else if (newRumorTipView.getTipButton().isSelected()) {
            String sql = "INSERT INTO tips VALUES (?, ?, ?, ?, ?, ?)";

            try {
                statement = connection.prepareStatement(sql);

                statement.setString(1, id);
                statement.setString(2, newRumorTipView.getTitleField().getText());
                statement.setString(3, newRumorTipView.getContentArea().getText());
                statement.setString(4, admin.getEmail());
                statement.setString(5, admin.getName());
                statement.setTimestamp(6, ts);

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Tip Published! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Tip Publish Failed! ");
                }

                newRumorTipView.getRumorTipFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Please Choose the Category of the Info! ");
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
