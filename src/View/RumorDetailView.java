package View;

import javax.swing.*;
import java.awt.*;

public class RumorDetailView {
    private JFrame rumorFrame;
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
    private JButton editButton;
    private JButton confirmButton;
    private JButton deleteButton;

    public RumorDetailView() {
        rumorFrame = new JFrame("Rumor Detail");

        rumorFrame.getContentPane().setLayout(new FlowLayout());

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

        contentLabel = new JLabel("Expert Comment: ");

        contentArea = new JTextArea(8, 31);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setMargin(new Insets(5, 5, 5, 5));

        contentScrollPane = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(90, 35));

        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(90, 35));

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(90, 35));

        rumorFrame.add(titlePanel);
        rumorFrame.add(typePanel);
        rumorFrame.add(publisherPanel);
        rumorFrame.add(timePanel);
        rumorFrame.add(contentPanel);
        rumorFrame.add(contentScrollPane);

        rumorFrame.add(editButton);
        rumorFrame.add(confirmButton);
        rumorFrame.add(deleteButton);

        titlePanel.add(titleLabel);

        typePanel.add(typeLabel);

        publisherPanel.add(publisherLabel);

        timePanel.add(timeLabel);

        contentPanel.add(contentLabel);

        rumorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rumorFrame.setSize(400, 390);
        rumorFrame.setLocationRelativeTo(null);
        rumorFrame.setResizable(false);
        rumorFrame.setVisible(true);
    }

    public JFrame getRumorFrame() {
        return rumorFrame;
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

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
