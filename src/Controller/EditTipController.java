package Controller;

import Model.Instruction;
import Model.User;
import View.EditTipView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EditTipController {
    private Connection connection;
    private PreparedStatement statement;

    private Instruction tip;
    private User user;
    private EditTipView editTipView;

    public EditTipController(Instruction tip, User user, EditTipView editTipView) {
        this.tip = tip;
        this.user = user;
        this.editTipView = editTipView;

        init();
    }

    private void init() {
        editTipView.getTitleField().setText(tip.getTitle());
        editTipView.getContentArea().setText(tip.getContent());

        editTipView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTip();
            }
        });

        editTipView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTipView.getTipFrame().dispose();
            }
        });
    }

    private void updateTip() {
        connectDB();

        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (tip.getEmail().equals(user.getEmail())) {
            String sql = "UPDATE tips SET title = ?, content = ?, time = ? WHERE id = ?";

            try {
                statement = connection.prepareStatement(sql);

                statement.setString(1, editTipView.getTitleField().getText());
                statement.setString(2, editTipView.getContentArea().getText());
                statement.setTimestamp(3, ts);
                statement.setString(4, tip.getId());

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Tip Updated! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Tip Update Failed! ");
                }

                editTipView.getTipFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry, You cannot Edit other Admin's Tip! ");
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
