package Controller;

import Model.Question;
import View.QuestionDetailView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QuestionDetailController {
    private Connection connection;
    private PreparedStatement statement;

    private Question question;
    private QuestionDetailView questionDetailView;

    public QuestionDetailController(Question question, QuestionDetailView questionDetailView) {
        this.question = question;
        this.questionDetailView = questionDetailView;

        init();
    }

    private void init() {
        questionDetailView.getTitleLabel().setText("Question: " + question.getQuestionTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (question.getAnswerPeople() != null) {
            questionDetailView.getAnswerPeopleLabel().setText("Answered By: " + question.getAnswerPeople() + " < " + question.getAnswerEmail() + " >");

            try {
                questionDetailView.getTimeLabel().setText("Answered Time: " + sdf.format(sdf.parse(question.getAnswerTime().toString())));
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
        else {
           questionDetailView.getAnswerPeopleLabel().setText("Asked By: " + question.getQuestionPeople() + " < " + question.getQuestionEmail() + " >");

           try {
               questionDetailView.getTimeLabel().setText("Asked Time: " + sdf.format(sdf.parse(question.getQuestionTime().toString())));
           } catch (ParseException pe) {
               pe.printStackTrace();
           }
        }

        questionDetailView.getQuestionArea().setText(question.getQuestion());
        questionDetailView.getAnswerArea().setText(question.getAnswer() != null ? question.getAnswer() : "Sorry, there has no any answer yet! Please be patient! ");

        questionDetailView.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionDetailView.getQuestionFrame().dispose();
            }
        });

        questionDetailView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuestion();
            }
        });
    }

    private void deleteQuestion() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete? ");

        if (dialogResult == JOptionPane.YES_OPTION) {
            connectDB();

            String sql = "DELETE From questions WHERE id = ?";

            try {
                statement = connection.prepareStatement(sql);

                statement.setString(1, question.getId());

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Question Deleted! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Question Delete Failed! ");
                }

                questionDetailView.getQuestionFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
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
