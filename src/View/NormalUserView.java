package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class NormalUserView {
    private JFrame userFrame;
    private JPanel navigationPanel;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel notificationPanel;
    private JPanel rumorTipPanel;
    private JPanel questionPanel;
    private JPanel totalInfoPanel;
    private JPanel confirmedInfoPanel;
    private JPanel infectedInfoPanel;
    private JPanel curedInfoPanel;
    private JPanel deathInfoPanel;
    private JPanel confirmedNumPanel;
    private JPanel infectedNumPanel;
    private JPanel curedNumPanel;
    private JPanel deathNumPanel;
    private JPanel detailedInfoPanel;
    private JPanel refreshInfoPanel;
    private JPanel detailedTitlePanel;
    private JPanel notificationListPanel;
    private JPanel rumorTipSeparatePanel;
    private JPanel rumorPanel;
    private JPanel tipPanel;
    private JPanel rumorTitlePanel;
    private JPanel tipTitlePanel;
    private JPanel rumorListPanel;
    private JPanel tipListPanel;
    private JPanel questionSeparatePanel;
    private JPanel answerPanel;
    private JPanel noAnswerPanel;
    private JPanel answerTitlePanel;
    private JPanel noAnswerTitlePanel;
    private JPanel answerListPanel;
    private JPanel noAnswerListPanel;
    private JScrollPane infoScrollPane;
    private JScrollPane notificationScrollPane;
    private JScrollPane rumorScrollPane;
    private JScrollPane tipScrollPane;
    private JScrollPane answerScrollPane;
    private JScrollPane noAnswerScrollPane;
    private JButton infoButton;
    private JButton notificationButton;
    private JButton rumorTipButton;
    private JButton questionButton;
    private JButton logoutButton;
    private JButton refreshInfoButton;
    private JButton refreshNotificationButton;
    private JButton refreshRumorTipButton;
    private JButton refreshQuestionButton;
    private JButton addQuestionButton;
    private JLabel confirmedInfoLabel;
    private JLabel infectedInfoLabel;
    private JLabel curedInfoLabel;
    private JLabel deathInfoLabel;
    private JLabel confirmedNumLabel;
    private JLabel infectedNumLabel;
    private JLabel curedNumLabel;
    private JLabel deathNumLabel;
    private JLabel detailedTitleLabel;
    private JLabel rumorLabel;
    private JLabel tipLabel;
    private JLabel answerLabel;
    private JLabel noAnswerLabel;
    private JTable detailedInfoTable;

    private CardLayout cardLayout;
    private GridBagConstraints constraints;

    public NormalUserView() {
        userFrame = new JFrame("SCU Pandemic Assistant");

        userFrame.getContentPane().setLayout(new BorderLayout());

        cardLayout = new CardLayout();

        navigationPanel = new JPanel(new GridLayout(5, 1));
        mainPanel = new JPanel(cardLayout);
        infoPanel = new JPanel();
        notificationPanel = new JPanel();
        rumorTipPanel = new JPanel();
        questionPanel = new JPanel();
        totalInfoPanel = new JPanel(new GridLayout(4, 2));
        confirmedInfoPanel = new JPanel();
        infectedInfoPanel = new JPanel();
        curedInfoPanel = new JPanel();
        deathInfoPanel = new JPanel();
        confirmedNumPanel = new JPanel();
        infectedNumPanel = new JPanel();
        curedNumPanel = new JPanel();
        deathNumPanel = new JPanel();
        detailedInfoPanel = new JPanel(new BorderLayout());
        refreshInfoPanel = new JPanel();
        detailedTitlePanel = new JPanel();
        notificationListPanel = new JPanel(new GridBagLayout());
        rumorTipSeparatePanel = new JPanel(new GridLayout(2, 1));
        rumorPanel = new JPanel(new BorderLayout());
        tipPanel = new JPanel(new BorderLayout());
        rumorTitlePanel = new JPanel();
        tipTitlePanel = new JPanel();
        rumorListPanel = new JPanel(new GridBagLayout());
        tipListPanel = new JPanel(new GridBagLayout());
        questionSeparatePanel = new JPanel(new GridLayout(2, 1));
        answerPanel = new JPanel(new BorderLayout());
        noAnswerPanel = new JPanel(new BorderLayout());
        answerTitlePanel = new JPanel();
        noAnswerTitlePanel = new JPanel();
        answerListPanel = new JPanel(new GridBagLayout());
        noAnswerListPanel = new JPanel(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;

        detailedInfoTable = new JTable();
        detailedInfoTable.setEnabled(false);

        infoScrollPane = new JScrollPane(detailedInfoTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        infoScrollPane.setPreferredSize(new Dimension(640, 390));

        notificationScrollPane = new JScrollPane(notificationListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        notificationScrollPane.setPreferredSize(new Dimension(600, 520));

        rumorTipSeparatePanel.setPreferredSize(new Dimension(600, 520));

        rumorScrollPane = new JScrollPane(rumorListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tipScrollPane = new JScrollPane(tipListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        questionSeparatePanel.setPreferredSize(new Dimension(600, 520));

        answerScrollPane = new JScrollPane(answerListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        noAnswerScrollPane = new JScrollPane(noAnswerListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        infoButton = new JButton("COVID-19 Info");
        notificationButton = new JButton("Notifications");
        rumorTipButton = new JButton("Rumors & Tips");
        questionButton = new JButton("Questions");
        logoutButton = new JButton("Logout");

        try {
            Image img = ImageIO.read(getClass().getResource("../Resource/info.png"));
            infoButton.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        try {
            Image img = ImageIO.read(getClass().getResource("../Resource/notification.png"));
            notificationButton.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        try {
            Image img = ImageIO.read(getClass().getResource("../Resource/instruction.png"));
            rumorTipButton.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        try {
            Image img = ImageIO.read(getClass().getResource("../Resource/question.png"));
            questionButton.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        try {
            Image img = ImageIO.read(getClass().getResource("../Resource/logout.png"));
            logoutButton.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        refreshInfoButton = new JButton("Refresh");
        refreshInfoButton.setPreferredSize(new Dimension(100, 35));

        refreshNotificationButton = new JButton("Refresh");
        refreshNotificationButton.setPreferredSize(new Dimension(100, 40));

        refreshRumorTipButton = new JButton("Refresh");
        refreshRumorTipButton.setPreferredSize(new Dimension(100, 40));

        addQuestionButton = new JButton("Ask Question");
        addQuestionButton.setPreferredSize(new Dimension(150, 40));

        refreshQuestionButton = new JButton("Refresh");
        refreshQuestionButton.setPreferredSize(new Dimension(100, 40));

        confirmedInfoLabel = new JLabel("Confirmed Infected");
        infectedInfoLabel = new JLabel("Active Infected");
        curedInfoLabel = new JLabel("Cured");
        deathInfoLabel = new JLabel("Death");

        confirmedInfoLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        infectedInfoLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        curedInfoLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        deathInfoLabel.setFont(new Font("Dialog", Font.BOLD, 15));

        confirmedNumLabel = new JLabel();
        infectedNumLabel = new JLabel();
        curedNumLabel = new JLabel();
        deathNumLabel = new JLabel();

        detailedTitleLabel = new JLabel("Detailed Info");
        detailedTitleLabel.setFont(new Font("Dialog", Font.BOLD, 15));

        rumorLabel = new JLabel("Rumors");
        tipLabel = new JLabel("Tips");
        answerLabel = new JLabel("Answered Questions");
        noAnswerLabel = new JLabel("Not Answered Questions");

        rumorLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        tipLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        answerLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        noAnswerLabel.setFont(new Font("Dialog", Font.BOLD, 15));

        userFrame.add(navigationPanel, BorderLayout.WEST);
        userFrame.add(mainPanel, BorderLayout.CENTER);

        navigationPanel.add(infoButton);
        navigationPanel.add(notificationButton);
        navigationPanel.add(rumorTipButton);
        navigationPanel.add(questionButton);
        navigationPanel.add(logoutButton);

        mainPanel.add(infoPanel, "Info");
        mainPanel.add(notificationPanel, "Notification");
        mainPanel.add(rumorTipPanel, "RumorTip");
        mainPanel.add(questionPanel, "Question");

        infoPanel.add(totalInfoPanel, BorderLayout.NORTH);
        infoPanel.add(detailedInfoPanel, BorderLayout.CENTER);
        infoPanel.add(refreshInfoPanel, BorderLayout.SOUTH);

        totalInfoPanel.setPreferredSize(new Dimension(600, 100));
        totalInfoPanel.add(confirmedInfoPanel);
        totalInfoPanel.add(infectedInfoPanel);
        totalInfoPanel.add(confirmedNumPanel);
        totalInfoPanel.add(infectedNumPanel);
        totalInfoPanel.add(curedInfoPanel);
        totalInfoPanel.add(deathInfoPanel);
        totalInfoPanel.add(curedNumPanel);
        totalInfoPanel.add(deathNumPanel);

        confirmedInfoPanel.add(confirmedInfoLabel);

        confirmedNumPanel.add(confirmedNumLabel);

        infectedInfoPanel.add(infectedInfoLabel);

        infectedNumPanel.add(infectedNumLabel);

        curedInfoPanel.add(curedInfoLabel);

        curedNumPanel.add(curedNumLabel);

        deathInfoPanel.add(deathInfoLabel);

        deathNumPanel.add(deathNumLabel);

        detailedInfoPanel.add(detailedTitlePanel, BorderLayout.NORTH);
        detailedInfoPanel.add(infoScrollPane, BorderLayout.CENTER);

        detailedTitlePanel.add(detailedTitleLabel);

        refreshInfoPanel.add(refreshInfoButton);

        notificationPanel.add(notificationScrollPane);
        notificationPanel.add(refreshNotificationButton);

        rumorTipPanel.add(rumorTipSeparatePanel);
        rumorTipPanel.add(refreshRumorTipButton);

        rumorTipSeparatePanel.add(rumorPanel);
        rumorTipSeparatePanel.add(tipPanel);

        rumorPanel.add(rumorTitlePanel, BorderLayout.NORTH);
        rumorPanel.add(rumorScrollPane, BorderLayout.CENTER);

        rumorTitlePanel.add(rumorLabel);

        tipPanel.add(tipTitlePanel, BorderLayout.NORTH);
        tipPanel.add(tipScrollPane, BorderLayout.CENTER);

        tipTitlePanel.add(tipLabel);

        questionPanel.add(questionSeparatePanel);
        questionPanel.add(addQuestionButton);
        questionPanel.add(refreshQuestionButton);

        questionSeparatePanel.add(answerPanel);
        questionSeparatePanel.add(noAnswerPanel);

        answerPanel.add(answerTitlePanel, BorderLayout.NORTH);
        answerPanel.add(answerScrollPane, BorderLayout.CENTER);

        answerTitlePanel.add(answerLabel);

        noAnswerPanel.add(noAnswerTitlePanel, BorderLayout.NORTH);
        noAnswerPanel.add(noAnswerScrollPane, BorderLayout.CENTER);

        noAnswerTitlePanel.add(noAnswerLabel);

        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setSize(800, 600);
        userFrame.setLocationRelativeTo(null);
        userFrame.setResizable(false);
        userFrame.setVisible(true);
    }

    public JFrame getUserFrame() {
        return userFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getNotificationListPanel() {
        return notificationListPanel;
    }

    public JPanel getRumorListPanel() {
        return rumorListPanel;
    }

    public JPanel getTipListPanel() {
        return tipListPanel;
    }

    public JPanel getAnswerListPanel() {
        return answerListPanel;
    }

    public JPanel getNoAnswerListPanel() {
        return noAnswerListPanel;
    }

    public JLabel getConfirmedNumLabel() {
        return confirmedNumLabel;
    }

    public JLabel getInfectedNumLabel() {
        return infectedNumLabel;
    }

    public JLabel getCuredNumLabel() {
        return curedNumLabel;
    }

    public JLabel getDeathNumLabel() {
        return deathNumLabel;
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public JButton getRefreshInfoButton() {
        return refreshInfoButton;
    }

    public JButton getNotificationButton() {
        return notificationButton;
    }

    public JButton getRefreshNotificationButton() {
        return refreshNotificationButton;
    }

    public JButton getRumorTipButton() {
        return rumorTipButton;
    }

    public JButton getRefreshRumorTipButton() {
        return refreshRumorTipButton;
    }

    public JButton getQuestionButton() {
        return questionButton;
    }

    public JButton getAddQuestionButton() {
        return addQuestionButton;
    }

    public JButton getRefreshQuestionButton() {
        return refreshQuestionButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JTable getDetailedInfoTable() {
        return detailedInfoTable;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }
}
