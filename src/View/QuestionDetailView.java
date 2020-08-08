package View;

import javax.swing.*;
import java.awt.*;

public class QuestionDetailView {
    private JFrame questionFrame;
    private JPanel titlePanel;
    private JPanel answerPeoplePanel;
    private JPanel timePanel;
    private JPanel questionPanel;
    private JPanel answerPanel;
    private JLabel titleLabel;
    private JLabel answerPeopleLabel;
    private JLabel timeLabel;
    private JLabel questionLabel;
    private JTextArea questionArea;
    private JLabel answerLabel;
    private JTextArea answerArea;
    private JScrollPane questionScrollPane;
    private JScrollPane answerScrollPane;
    private JButton confirmButton;
    private JButton deleteButton;

    public QuestionDetailView() {
        questionFrame = new JFrame("Question Detail");

        questionFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerPeoplePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        answerPeoplePanel.setPreferredSize(new Dimension(400, 30));
        timePanel.setPreferredSize(new Dimension(400, 30));
        questionPanel.setPreferredSize(new Dimension(400, 30));
        answerPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel();
        answerPeopleLabel = new JLabel();
        timeLabel = new JLabel();

        questionLabel = new JLabel("Question Detail: ");
        answerLabel = new JLabel("Answer Detail: ");

        questionArea = new JTextArea(5, 31);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setMargin(new Insets(5, 5, 5, 5));

        answerArea = new JTextArea(5, 31);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setMargin(new Insets(5, 5, 5, 5));

        questionScrollPane = new JScrollPane(questionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        answerScrollPane = new JScrollPane(answerArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(90, 35));

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(90, 35));

        questionFrame.add(titlePanel);
        questionFrame.add(answerPeoplePanel);
        questionFrame.add(timePanel);
        questionFrame.add(questionPanel);
        questionFrame.add(questionScrollPane);
        questionFrame.add(answerPanel);
        questionFrame.add(answerScrollPane);
        questionFrame.add(confirmButton);
        questionFrame.add(deleteButton);

        titlePanel.add(titleLabel);

        answerPeoplePanel.add(answerPeopleLabel);

        timePanel.add(timeLabel);

        questionPanel.add(questionLabel);

        answerPanel.add(answerLabel);

        questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        questionFrame.setSize(400, 440);
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setResizable(false);
        questionFrame.setVisible(true);
    }

    public JFrame getQuestionFrame() {
        return questionFrame;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getAnswerPeopleLabel() {
        return answerPeopleLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JTextArea getQuestionArea() {
        return questionArea;
    }

    public JTextArea getAnswerArea() {
        return answerArea;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
