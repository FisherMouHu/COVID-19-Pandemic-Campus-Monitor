package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AdminViewInitiator extends UserViewInitiator {
    private ResultSet infoResultSet;
    private ResultSet notificationResultSet;
    private ResultSet rumorResultSet;
    private ResultSet tipResultSet;
    private ResultSet questionResultSet;

    private User admin;
    private AdminView adminView;

    private int confirmedNum = 0;
    private int infectedNum = 0;
    private int curedNum = 0;
    private int deathNum = 0;
    private Object[][] data = {};
    private Object[] columnNames = { "School Name", "Confirmed Infected", "Active Infected", "Cured", "Death" };

    // Create a Builder and a Director
    // Use Builder Pattern to Create Same Object with Different Representations ( Instruction -> (Notification, Rumor, Tip) )
    private InstructionBuilder instructionBuilder;
    private InstructionDirector instructionDirector;

    // Create a Facade
    // Use Facade Pattern to Hide the Implementation of other DetailController ( InstructionInfoKeeper -> (NotificationController, RumorController, TipController) )
    private InstructionInfoKeeper instructionInfoKeeper;

    public AdminViewInitiator(User admin, AdminView adminView) {
        this.admin = admin;
        this.adminView = adminView;

        instructionInfoKeeper = new InstructionInfoKeeper();
    }

    @Override
    public void initInfo() {
        // Initialize at the Beginning
        try {
            String sql = "SELECT * FROM infos;";

            infoResultSet = statement.executeQuery(sql);

            DefaultTableModel tm = new DefaultTableModel(data, columnNames);

            confirmedNum = 0;
            infectedNum = 0;
            curedNum = 0;
            deathNum = 0;

            while (infoResultSet.next()) {
                String[] item = new String[] {infoResultSet.getString(2), String.valueOf(infoResultSet.getInt(3)), String.valueOf(infoResultSet.getInt(4)), String.valueOf(infoResultSet.getInt(5)), String.valueOf(infoResultSet.getInt(6))};

                // Compute Number
                confirmedNum += infoResultSet.getInt(3);
                infectedNum += infoResultSet.getInt(4);
                curedNum += infoResultSet.getInt(5);
                deathNum += infoResultSet.getInt(6);

                // Add a New Row
                tm.addRow(item);
            }

            adminView.getConfirmedNumLabel().setText(String.valueOf(confirmedNum));
            adminView.getInfectedNumLabel().setText(String.valueOf(infectedNum));
            adminView.getCuredNumLabel().setText(String.valueOf(curedNum));
            adminView.getDeathNumLabel().setText(String.valueOf(deathNum));

            adminView.getDetailedInfoTable().setModel(tm);
            adminView.getDetailedInfoTable().getTableHeader().setPreferredSize(new Dimension(adminView.getDetailedInfoTable().getTableHeader().getWidth(), 45));
            adminView.getDetailedInfoTable().getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
            adminView.getDetailedInfoTable().setRowHeight(25);
            adminView.getDetailedInfoTable().getColumnModel().getColumn(0).setMinWidth(280);
            adminView.getDetailedInfoTable().getColumnModel().getColumn(1).setMinWidth(130);
            adminView.getDetailedInfoTable().getColumnModel().getColumn(2).setMinWidth(110);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void initOthers() {
        adminView.getInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initInfo();

                adminView.getCardLayout().show(adminView.getMainPanel(), "Info");
            }
        });

        adminView.getUpdateInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateInfoView updateInfoView = new UpdateInfoView();
                UpdateInfoController updateInfoController = new UpdateInfoController(updateInfoView);
            }
        });

        adminView.getRefreshInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.getAdminFrame().invalidate();

                initInfo();

                adminView.getAdminFrame().validate();
                adminView.getAdminFrame().repaint();
            }
        });

        adminView.getNotificationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initNotification(sdf);

                adminView.getCardLayout().show(adminView.getMainPanel(), "Notification");
            }
        });

        adminView.getAddNotificationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewNotificationView newNotificationView = new NewNotificationView();
                NewNotificationController newNotificationController = new NewNotificationController(admin, newNotificationView);
            }
        });

        adminView.getRefreshNotificationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.getAdminFrame().invalidate();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initNotification(sdf);

                adminView.getAdminFrame().validate();
                adminView.getAdminFrame().repaint();
            }
        });

        adminView.getRumorTipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initRumor(sdf);

                initTip(sdf);

                adminView.getCardLayout().show(adminView.getMainPanel(), "RumorTip");
            }
        });

        adminView.getAddRumorTipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewRumorTipView newRumorTipView = new NewRumorTipView();
                NewRumorTipController newRumorTipController = new NewRumorTipController(admin, newRumorTipView);
            }
        });

        adminView.getRefreshRumorTipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.getAdminFrame().invalidate();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initRumor(sdf);

                initTip(sdf);

                adminView.getAdminFrame().validate();
                adminView.getAdminFrame().repaint();
            }
        });

        adminView.getQuestionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initQuestion();

                adminView.getCardLayout().show(adminView.getMainPanel(), "Question");
            }
        });

        adminView.getRefreshQuestionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.getAdminFrame().invalidate();

                initQuestion();

                adminView.getAdminFrame().validate();
                adminView.getAdminFrame().repaint();
            }
        });

        adminView.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Logout? ");

                if (dialogResult == JOptionPane.YES_OPTION) {
                    adminView.getAdminFrame().dispose();

                    LoginView loginView = new LoginView();
                    LoginController loginController = new LoginController(loginView);
                }
            }
        });
    }

    private void initNotification(SimpleDateFormat sdf) {
        try {
            String sql = "SELECT * FROM notifications ORDER BY time ASC;";

            notificationResultSet = statement.executeQuery(sql);

            adminView.getNotificationListPanel().removeAll();

            adminView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (notificationResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new NotificationBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(notificationResultSet.getString(1), notificationResultSet.getString(2), notificationResultSet.getString(3), notificationResultSet.getString(4), notificationResultSet.getString(5), notificationResultSet.getTimestamp(6));

                Instruction notification = instructionDirector.getInstruction();

                adminView.getNotificationListPanel().add(instructionInfoKeeper.getNotificationInfo(sdf, panelPainted, notification), adminView.getConstraints(), 0);

                adminView.getConstraints().weighty = 0;

                panelPainted = !panelPainted;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    private void initRumor(SimpleDateFormat sdf) {
        try {
            // Get Rumors
            String rumorSql = "SELECT * FROM rumors ORDER BY time ASC;";

            rumorResultSet = statement.executeQuery(rumorSql);

            adminView.getRumorListPanel().removeAll();

            adminView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (rumorResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new RumorBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(rumorResultSet.getString(1), rumorResultSet.getString(2), rumorResultSet.getString(3), rumorResultSet.getString(4), rumorResultSet.getString(5), rumorResultSet.getTimestamp(6));

                Instruction rumor = instructionDirector.getInstruction();

                adminView.getRumorListPanel().add(instructionInfoKeeper.getRumorInfo(sdf, panelPainted, rumor, admin), adminView.getConstraints(), 0);

                adminView.getConstraints().weighty = 0;

                panelPainted = !panelPainted;
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    private void initTip(SimpleDateFormat sdf) {
        try {
            // Get Tips
            String tipSql = "SELECT * FROM tips ORDER BY time ASC;";

            tipResultSet = statement.executeQuery(tipSql);

            adminView.getTipListPanel().removeAll();

            adminView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (tipResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new TipBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(tipResultSet.getString(1), tipResultSet.getString(2), tipResultSet.getString(3), tipResultSet.getString(4), tipResultSet.getString(5), tipResultSet.getTimestamp(6));

                Instruction tip = instructionDirector.getInstruction();

                adminView.getTipListPanel().add(instructionInfoKeeper.getTipInfo(sdf, panelPainted, tip, admin), adminView.getConstraints(), 0);

                adminView.getConstraints().weighty = 0;

                panelPainted = !panelPainted;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void initQuestion() {
        try {
            String sql = "SELECT * FROM questions ORDER BY questionTime ASC;";

            questionResultSet = statement.executeQuery(sql);

            adminView.getQuestionListPanel().removeAll();

            adminView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (questionResultSet.next()) {
                if (questionResultSet.getString(7) == null) {
                    JPanel questionItemPanel = new JPanel(new GridLayout(3, 1));

                    questionItemPanel.setPreferredSize(new Dimension(600, 100));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    if (!panelPainted) {
                        questionItemPanel.setBackground(Color.white);
                    } else {
                        questionItemPanel.setBackground(Color.lightGray);
                    }

                    Question question = new Question(questionResultSet.getString(1), questionResultSet.getString(2), questionResultSet.getString(3), questionResultSet.getString(4), questionResultSet.getString(5), questionResultSet.getTimestamp(6));

                    JLabel questionTitleLabel = new JLabel("Question: " + question.getQuestionTitle());
                    JLabel questionPublisherLabel = new JLabel("From: " + question.getQuestionPeople() + " < " + question.getQuestionEmail() + " >");
                    JLabel questionTimeLabel = new JLabel("Asked Time: " + sdf.format(sdf.parse(question.getQuestionTime().toString())));

                    questionItemPanel.add(questionTitleLabel);
                    questionItemPanel.add(questionPublisherLabel);
                    questionItemPanel.add(questionTimeLabel);

                    questionItemPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            AnswerQuestionView answerQuestionView = new AnswerQuestionView();
                            AnswerQuestionController answerQuestionController = new AnswerQuestionController(admin, question, answerQuestionView);
                        }
                    });

                    adminView.getQuestionListPanel().add(questionItemPanel, adminView.getConstraints(), 0);

                    adminView.getConstraints().weighty = 0;

                    panelPainted = !panelPainted;
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
