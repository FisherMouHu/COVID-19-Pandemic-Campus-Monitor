package View;

import javax.swing.*;
import java.awt.*;

public class NewRumorTipView {
    private JFrame rumorTipFrame;
    private JPanel titlePanel;
    private JPanel categoryPanel;
    private JPanel radioPanel;
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JLabel categoryLabel;
    private JLabel contentLabel;
    private JTextField titleField;
    private JTextArea contentArea;
    private JScrollPane contentScrollPane;
    private JRadioButton rumorButton;
    private JRadioButton tipButton;
    private JButton publishButton;
    private JButton cancelButton;

    public NewRumorTipView() {
        rumorTipFrame = new JFrame("Publish Rumor / Tip");

        rumorTipFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel = new JPanel(new GridLayout(1, 2));
        contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        categoryPanel.setPreferredSize(new Dimension(400, 30));
        radioPanel.setPreferredSize(new Dimension(300, 30));
        contentPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel("Title: ");
        categoryLabel = new JLabel("Category: ");
        contentLabel = new JLabel("Expert Comment: ");

        titleField = new JTextField();
        titleField.setColumns(28);

        contentArea = new JTextArea(13, 31);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(5, 5, 5, 5));

        contentScrollPane = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        rumorButton = new JRadioButton("Rumor");
        tipButton = new JRadioButton("Tip");

        publishButton = new JButton("Publish");
        publishButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        rumorTipFrame.add(titlePanel);
        rumorTipFrame.add(categoryPanel);
        rumorTipFrame.add(contentPanel);
        rumorTipFrame.add(contentScrollPane);
        rumorTipFrame.add(publishButton);
        rumorTipFrame.add(cancelButton);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        categoryPanel.add(categoryLabel);
        categoryPanel.add(radioPanel);

        radioPanel.add(rumorButton);
        radioPanel.add(tipButton);

        ButtonGroup group = new ButtonGroup();

        group.add(rumorButton);
        group.add(tipButton);

        contentPanel.add(contentLabel);

        rumorTipFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rumorTipFrame.setSize(400, 400);
        rumorTipFrame.setLocationRelativeTo(null);
        rumorTipFrame.setResizable(false);
        rumorTipFrame.setVisible(true);
    }

    public JFrame getRumorTipFrame() {
        return rumorTipFrame;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JRadioButton getRumorButton() {
        return rumorButton;
    }

    public JRadioButton getTipButton() {
        return tipButton;
    }

    public JButton getPublishButton() {
        return publishButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
