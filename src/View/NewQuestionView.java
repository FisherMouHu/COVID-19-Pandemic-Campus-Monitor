package View;

import javax.swing.*;
import java.awt.*;

public class NewQuestionView {
    private JFrame questionFrame;
    private JPanel titlePanel;
    private JPanel questionPanel;
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JTextField titleField;
    private JTextArea questionArea;
    private JScrollPane questionScrollPane;
    private JButton askButton;
    private JButton cancelButton;

    public NewQuestionView() {
        questionFrame = new JFrame("Ask Question");

        questionFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        questionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        questionPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel("Question: ");
        questionLabel = new JLabel("Question Detail: ");

        titleField = new JTextField();
        titleField.setColumns(26);

        questionArea = new JTextArea(15, 31);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setMargin(new Insets(5, 5, 5, 5));

        questionScrollPane = new JScrollPane(questionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        askButton = new JButton("Ask");
        askButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        questionFrame.add(titlePanel);
        questionFrame.add(questionPanel);
        questionFrame.add(questionScrollPane);
        questionFrame.add(askButton);
        questionFrame.add(cancelButton);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        questionPanel.add(questionLabel);

        questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        questionFrame.setSize(400, 400);
        questionFrame.setLocationRelativeTo(null);
        questionFrame.setResizable(false);
        questionFrame.setVisible(true);
    }

    public JFrame getQuestionFrame() {
        return questionFrame;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getQuestionArea() {
        return questionArea;
    }

    public JButton getAskButton() {
        return askButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
