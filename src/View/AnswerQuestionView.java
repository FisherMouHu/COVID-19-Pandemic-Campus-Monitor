package View;

import javax.swing.*;
import java.awt.*;

public class AnswerQuestionView {
    private JFrame answerFrame;
    private JPanel titlePanel;
    private JPanel publisherPanel;
    private JPanel timePanel;
    private JPanel questionPanel;
    private JPanel answerPanel;
    private JLabel titleLabel;
    private JLabel publisherLabel;
    private JLabel timeLabel;
    private JLabel questionLabel;
    private JTextArea questionArea;
    private JLabel answerLabel;
    private JTextArea answerArea;
    private JScrollPane questionScrollPane;
    private JScrollPane answerScrollPane;
    private JButton answerButton;
    private JButton cancelButton;

    public AnswerQuestionView() {
        answerFrame = new JFrame("Answer Question");

        answerFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        publisherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        publisherPanel.setPreferredSize(new Dimension(400, 30));
        timePanel.setPreferredSize(new Dimension(400, 30));
        questionPanel.setPreferredSize(new Dimension(400, 30));
        answerPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel();
        publisherLabel = new JLabel();
        timeLabel = new JLabel();

        questionLabel = new JLabel("Question Detail: ");
        answerLabel = new JLabel("Provide Your Answer: ");

        questionArea = new JTextArea(5, 31);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setMargin(new Insets(5, 5, 5, 5));

        answerArea = new JTextArea(5, 31);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setMargin(new Insets(5, 5, 5, 5));

        questionScrollPane = new JScrollPane(questionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        answerScrollPane = new JScrollPane(answerArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        answerButton = new JButton("Answer");
        answerButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        answerFrame.add(titlePanel);
        answerFrame.add(publisherPanel);
        answerFrame.add(timePanel);
        answerFrame.add(questionPanel);
        answerFrame.add(questionScrollPane);
        answerFrame.add(answerPanel);
        answerFrame.add(answerScrollPane);
        answerFrame.add(answerButton);
        answerFrame.add(cancelButton);

        titlePanel.add(titleLabel);

        publisherPanel.add(publisherLabel);

        timePanel.add(timeLabel);

        questionPanel.add(questionLabel);

        answerPanel.add(answerLabel);

        answerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        answerFrame.setSize(400, 440);
        answerFrame.setLocationRelativeTo(null);
        answerFrame.setResizable(false);
        answerFrame.setVisible(true);
    }

    public JFrame getAnswerFrame() {
        return answerFrame;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getPublisherLabel() {
        return publisherLabel;
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

    public JButton getAnswerButton() {
        return answerButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
