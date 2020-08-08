package Controller;

import Model.Question;
import Model.User;
import View.AnswerQuestionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AnswerQuestionController {
    private Connection connection;
    private PreparedStatement statement;

    private User admin;
    private Question question;
    private AnswerQuestionView answerQuestionView;

    public AnswerQuestionController(User admin, Question question, AnswerQuestionView answerQuestionView) {
        this.admin = admin;
        this.question = question;
        this.answerQuestionView = answerQuestionView;

        init();
    }

    private void init() {
        answerQuestionView.getTitleLabel().setText("Question: " + question.getQuestionTitle());
        answerQuestionView.getPublisherLabel().setText("Asked By: " + question.getQuestionPeople() + " < " + question.getQuestionEmail() + " >");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            answerQuestionView.getTimeLabel().setText("Asked Time: " + sdf.format(sdf.parse(question.getQuestionTime().toString())));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        answerQuestionView.getQuestionArea().setText(question.getQuestion());

        answerQuestionView.getAnswerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerQuestion();
            }
        });

        answerQuestionView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerQuestionView.getAnswerFrame().dispose();
            }
        });
    }

    private void answerQuestion() {
        // Get Current Time
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp ts = new Timestamp(cal.getTimeInMillis());

        if (answerQuestionView.getAnswerArea().getText() == null || answerQuestionView.getAnswerArea().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Fill Out the Answer! ");

            return;
        }

        connectDB();

        String sql = "UPDATE questions SET answer = ?, answerEmail = ?, answerPeople = ?, answerTime = ? WHERE id = ?";

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, answerQuestionView.getAnswerArea().getText());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getName());
            statement.setTimestamp(4, ts);
            statement.setString(5, question.getId());

            int resultSet = statement.executeUpdate();

            if (resultSet > 0) {
                JOptionPane.showMessageDialog(null, "Question Answered! ");
            }
            else {
                JOptionPane.showMessageDialog(null, "Question Answer Failed! ");
            }

            answerQuestionView.getAnswerFrame().dispose();
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
