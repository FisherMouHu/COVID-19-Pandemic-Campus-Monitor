package View;

import javax.swing.*;
import java.awt.*;

public class EditRumorView {
    private JFrame rumorFrame;
    private JPanel titlePanel;
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JLabel contentLabel;
    private JTextField titleField;
    private JTextArea contentArea;
    private JScrollPane contentScrollPane;
    private JButton updateButton;
    private JButton cancelButton;

    public EditRumorView() {
        rumorFrame = new JFrame("Edit Rumor");

        rumorFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        contentPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel("Title: ");
        contentLabel = new JLabel("Expert Comment: ");

        titleField = new JTextField();
        titleField.setColumns(28);

        contentArea = new JTextArea(15, 31);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(5, 5, 5, 5));

        contentScrollPane = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        rumorFrame.add(titlePanel);
        rumorFrame.add(contentPanel);
        rumorFrame.add(contentScrollPane);
        rumorFrame.add(updateButton);
        rumorFrame.add(cancelButton);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        contentPanel.add(contentLabel);

        rumorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rumorFrame.setSize(400, 400);
        rumorFrame.setLocationRelativeTo(null);
        rumorFrame.setResizable(false);
        rumorFrame.setVisible(true);
    }

    public JFrame getRumorFrame() {
        return rumorFrame;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
