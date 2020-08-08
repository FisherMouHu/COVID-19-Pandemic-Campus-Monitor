package View;

import javax.swing.*;
import java.awt.*;

public class NotificationDetailView {
    private JFrame notificationFrame;
    private JPanel titlePanel;
    private JPanel typePanel;
    private JPanel publisherPanel;
    private JPanel timePanel;
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JLabel typeLabel;
    private JLabel publisherLabel;
    private JLabel timeLabel;
    private JLabel contentLabel;
    private JTextArea contentArea;
    private JScrollPane contentScrollPane;
    private JButton confirmButton;

    public NotificationDetailView() {
        notificationFrame = new JFrame("Notification Detail");

        notificationFrame.getContentPane().setLayout(new FlowLayout());

        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        publisherPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        titlePanel.setPreferredSize(new Dimension(400, 30));
        typePanel.setPreferredSize(new Dimension(400, 30));
        publisherPanel.setPreferredSize(new Dimension(400, 30));
        timePanel.setPreferredSize(new Dimension(400, 30));
        contentPanel.setPreferredSize(new Dimension(400, 30));

        titleLabel = new JLabel();
        typeLabel = new JLabel();
        publisherLabel = new JLabel();
        timeLabel = new JLabel();

        contentLabel = new JLabel("Detail Info: ");

        contentArea = new JTextArea(8, 31);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(5, 5, 5, 5));

        contentScrollPane = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(90, 35));

        notificationFrame.add(titlePanel);
        notificationFrame.add(typePanel);
        notificationFrame.add(publisherPanel);
        notificationFrame.add(timePanel);
        notificationFrame.add(contentPanel);
        notificationFrame.add(contentScrollPane);
        notificationFrame.add(confirmButton);

        titlePanel.add(titleLabel);

        typePanel.add(typeLabel);

        publisherPanel.add(publisherLabel);

        timePanel.add(timeLabel);

        contentPanel.add(contentLabel);

        notificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notificationFrame.setSize(400, 390);
        notificationFrame.setLocationRelativeTo(null);
        notificationFrame.setResizable(false);
        notificationFrame.setVisible(true);
    }

    public JFrame getNotificationFrame() {
        return notificationFrame;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public JLabel getPublisherLabel() {
        return publisherLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
