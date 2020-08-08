package View;

import javax.swing.*;
import java.awt.*;

public class NewNotificationView {
    private JFrame notificationFrame;
    private JPanel titlePanel;
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JLabel contentLabel;
    private JTextField titleField;
    private JTextArea contentArea;
    private JScrollPane contentScrollPane;
    private JButton publishButton;
    private JButton cancelButton;

    public NewNotificationView() {
        notificationFrame = new JFrame("Publish Notification");

        notificationFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        contentPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel("Title: ");
        contentLabel = new JLabel("Detail Info: ");

        titleField = new JTextField();
        titleField.setColumns(28);

        contentArea = new JTextArea(15, 31);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(5, 5, 5, 5));

        contentScrollPane = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        publishButton = new JButton("Publish");
        publishButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        notificationFrame.add(titlePanel);
        notificationFrame.add(contentPanel);
        notificationFrame.add(contentScrollPane);
        notificationFrame.add(publishButton);
        notificationFrame.add(cancelButton);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        contentPanel.add(contentLabel);

        notificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notificationFrame.setSize(400, 400);
        notificationFrame.setLocationRelativeTo(null);
        notificationFrame.setResizable(false);
        notificationFrame.setVisible(true);
    }

    public JFrame getNotificationFrame() {
        return notificationFrame;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JButton getPublishButton() {
        return publishButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
