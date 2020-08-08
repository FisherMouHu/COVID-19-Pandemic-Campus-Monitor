package Controller;

import Model.User;
import View.NewQuestionView;

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

public class NewQuestionController {
    private Connection connection;
    private PreparedStatement statement;

    private User user;
    private NewQuestionView newQuestionView;

    public NewQuestionController(User user, NewQuestionView newQuestionView) {
        this.user = user;
        this.newQuestionView = newQuestionView;

        init();
    }

    private void init() {
        newQuestionView.getAskButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askQuestion();
            }
        });

        newQuestionView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newQuestionView.getQuestionFrame().dispose();
            }
        });
    }

    private void askQuestion() {
        // Create a ID
        String id = UUID.randomUUID().toString();

        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (newQuestionView.getTitleField().getText() == null || newQuestionView.getTitleField().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Title! ");

            return;
        }

        if (newQuestionView.getQuestionArea().getText() == null || newQuestionView.getQuestionArea().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Question Detail! ");

            return;
        }

        connectDB();

        String sql = "INSERT INTO questions (id, questionTitle, question, questionEmail, questionPeople, questionTime) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, newQuestionView.getTitleField().getText());
            statement.setString(3, newQuestionView.getQuestionArea().getText());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getName());
            statement.setTimestamp(6, ts);

            int resultSet = statement.executeUpdate();

            if (resultSet > 0) {
                JOptionPane.showMessageDialog(null, "Question Asked! ");
            }
            else {
                JOptionPane.showMessageDialog(null, "Question Ask Failed! ");
            }

            newQuestionView.getQuestionFrame().dispose();
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
