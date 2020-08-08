package Controller;

import Model.Instruction;
import Model.User;
import View.EditRumorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class EditRumorController {
    private Connection connection;
    private PreparedStatement statement;

    private Instruction rumor;
    private User user;
    private EditRumorView editRumorView;

    public EditRumorController(Instruction rumor, User user, EditRumorView editRumorView) {
        this.rumor = rumor;
        this.user = user;
        this.editRumorView = editRumorView;

        init();
    }

    private void init() {
        editRumorView.getTitleField().setText(rumor.getTitle());
        editRumorView.getContentArea().setText(rumor.getContent());

        editRumorView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRumor();
            }
        });

        editRumorView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRumorView.getRumorFrame().dispose();
            }
        });
    }

    private void updateRumor() {
        connectDB();

        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (rumor.getEmail().equals(user.getEmail())) {
            String sql = "UPDATE rumors SET title = ?, content = ?, time = ? WHERE id = ?";

            try {
                statement = connection.prepareStatement(sql);

                statement.setString(1, editRumorView.getTitleField().getText());
                statement.setString(2, editRumorView.getContentArea().getText());
                statement.setTimestamp(3, ts);
                statement.setString(4, rumor.getId());

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Rumor Updated! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Rumor Update Failed! ");
                }

                editRumorView.getRumorFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Sorry, You cannot Edit other Admin's Rumor! ");
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
