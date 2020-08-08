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

public class NormalUserViewInitiator extends UserViewInitiator {
    private ResultSet infoResultSet;
    private ResultSet notificationResultSet;
    private ResultSet rumorResultSet;
    private ResultSet tipResultSet;
    private ResultSet questionResultSet;

    private User user;
    private NormalUserView normalUserView;

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

    public NormalUserViewInitiator(User user, NormalUserView normalUserView) {
        this.user = user;
        this.normalUserView = normalUserView;

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

            normalUserView.getConfirmedNumLabel().setText(String.valueOf(confirmedNum));
            normalUserView.getInfectedNumLabel().setText(String.valueOf(infectedNum));
            normalUserView.getCuredNumLabel().setText(String.valueOf(curedNum));
            normalUserView.getDeathNumLabel().setText(String.valueOf(deathNum));

            normalUserView.getDetailedInfoTable().setModel(tm);
            normalUserView.getDetailedInfoTable().getTableHeader().setPreferredSize(new Dimension(normalUserView.getDetailedInfoTable().getTableHeader().getWidth(), 45));
            normalUserView.getDetailedInfoTable().getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
            normalUserView.getDetailedInfoTable().setRowHeight(25);
            normalUserView.getDetailedInfoTable().getColumnModel().getColumn(0).setMinWidth(280);
            normalUserView.getDetailedInfoTable().getColumnModel().getColumn(1).setMinWidth(130);
            normalUserView.getDetailedInfoTable().getColumnModel().getColumn(2).setMinWidth(110);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void initOthers() {
        normalUserView.getInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initInfo();

                normalUserView.getCardLayout().show(normalUserView.getMainPanel(), "Info");
            }
        });

        normalUserView.getRefreshInfoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalUserView.getUserFrame().invalidate();

                initInfo();

                normalUserView.getUserFrame().validate();
                normalUserView.getUserFrame().repaint();
            }
        });

        normalUserView.getNotificationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initNotification(sdf);

                normalUserView.getCardLayout().show(normalUserView.getMainPanel(), "Notification");
            }
        });

        normalUserView.getRefreshNotificationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalUserView.getUserFrame().invalidate();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initNotification(sdf);

                normalUserView.getUserFrame().validate();
                normalUserView.getUserFrame().repaint();
            }
        });

        normalUserView.getRumorTipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initRumor(sdf);

                initTip(sdf);

                normalUserView.getCardLayout().show(normalUserView.getMainPanel(), "RumorTip");
            }
        });

        normalUserView.getRefreshRumorTipButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalUserView.getUserFrame().invalidate();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                initRumor(sdf);

                initTip(sdf);

                normalUserView.getUserFrame().validate();
                normalUserView.getUserFrame().repaint();
            }
        });

        normalUserView.getQuestionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initAnswerQuestion();

                initNoAnswerQuestion();

                normalUserView.getCardLayout().show(normalUserView.getMainPanel(), "Question");
            }
        });

        normalUserView.getAddQuestionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewQuestionView newQuestionView = new NewQuestionView();
                NewQuestionController newQuestionController = new NewQuestionController(user, newQuestionView);
            }
        });

        normalUserView.getRefreshQuestionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalUserView.getUserFrame().invalidate();

                initAnswerQuestion();

                initNoAnswerQuestion();

                normalUserView.getUserFrame().validate();
                normalUserView.getUserFrame().repaint();
            }
        });

        normalUserView.getLogoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Logout? ");

                if (dialogResult == JOptionPane.YES_OPTION) {
                    normalUserView.getUserFrame().setVisible(false);

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

            normalUserView.getNotificationListPanel().removeAll();

            normalUserView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (notificationResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new NotificationBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(notificationResultSet.getString(1), notificationResultSet.getString(2), notificationResultSet.getString(3), notificationResultSet.getString(4), notificationResultSet.getString(5), notificationResultSet.getTimestamp(6));

                Instruction notification = instructionDirector.getInstruction();

                normalUserView.getNotificationListPanel().add(instructionInfoKeeper.getNotificationInfo(sdf, panelPainted, notification), normalUserView.getConstraints(), 0);

                normalUserView.getConstraints().weighty = 0;

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

            normalUserView.getRumorListPanel().removeAll();

            normalUserView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (rumorResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new RumorBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(rumorResultSet.getString(1), rumorResultSet.getString(2), rumorResultSet.getString(3), rumorResultSet.getString(4), rumorResultSet.getString(5), rumorResultSet.getTimestamp(6));

                Instruction rumor = instructionDirector.getInstruction();

                normalUserView.getRumorListPanel().add(instructionInfoKeeper.getRumorInfo(sdf, panelPainted, rumor, user),normalUserView.getConstraints(), 0);

                normalUserView.getConstraints().weighty = 0;

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

            normalUserView.getTipListPanel().removeAll();

            normalUserView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean panelPainted = false;

            while (tipResultSet.next()) {
                // Builder ! ! !
                instructionBuilder = new TipBuilder();

                instructionDirector = new InstructionDirector(instructionBuilder);

                instructionDirector.constructInstruction(tipResultSet.getString(1), tipResultSet.getString(2), tipResultSet.getString(3), tipResultSet.getString(4), tipResultSet.getString(5), tipResultSet.getTimestamp(6));

                Instruction tip = instructionDirector.getInstruction();

                normalUserView.getTipListPanel().add(instructionInfoKeeper.getTipInfo(sdf, panelPainted, tip, user), normalUserView.getConstraints(), 0);

                normalUserView.getConstraints().weighty = 0;

                panelPainted = !panelPainted;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void initAnswerQuestion() {
        try {
            String sql = "SELECT * FROM questions WHERE questionEmail = '" + user.getEmail() + "' ORDER BY questionTime ASC;";

            questionResultSet = statement.executeQuery(sql);

            normalUserView.getAnswerListPanel().removeAll();

            normalUserView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean answeredPanelPainted = false;

            while (questionResultSet.next()) {
                if (questionResultSet.getString(7) != null) {
                    JPanel questionItemPanel = new JPanel(new GridLayout(3, 1));

                    questionItemPanel.setPreferredSize(new Dimension(600, 100));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    if (!answeredPanelPainted) {
                        questionItemPanel.setBackground(Color.white);
                    } else {
                        questionItemPanel.setBackground(Color.lightGray);
                    }

                    Question question = new Question(questionResultSet.getString(1), questionResultSet.getString(2), questionResultSet.getString(3), questionResultSet.getString(4), questionResultSet.getString(5), questionResultSet.getTimestamp(6), questionResultSet.getString(7), questionResultSet.getString(8), questionResultSet.getString(9), questionResultSet.getTimestamp(10));

                    JLabel questionTitleLabel = new JLabel("Question: " + question.getQuestionTitle());
                    JLabel questionPublisherLabel = new JLabel("From: " + question.getQuestionPeople() + " < " + question.getQuestionEmail() + " >");
                    JLabel questionTimeLabel = new JLabel("Asked Time: " + sdf.format(sdf.parse(question.getQuestionTime().toString())));

                    questionItemPanel.add(questionTitleLabel);
                    questionItemPanel.add(questionPublisherLabel);
                    questionItemPanel.add(questionTimeLabel);

                    questionItemPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            QuestionDetailView questionDetailView = new QuestionDetailView();
                            QuestionDetailController questionDetailController = new QuestionDetailController(question, questionDetailView);
                        }
                    });

                    normalUserView.getAnswerListPanel().add(questionItemPanel, normalUserView.getConstraints(), 0);

                    normalUserView.getConstraints().weighty = 0;

                    answeredPanelPainted = !answeredPanelPainted;
                }
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    private void initNoAnswerQuestion() {
        try {
            String sql = "SELECT * FROM questions WHERE questionEmail = '" + user.getEmail() + "' ORDER BY questionTime ASC;";

            questionResultSet = statement.executeQuery(sql);

            normalUserView.getNoAnswerListPanel().removeAll();

            normalUserView.getConstraints().weighty = 1;

            // Use a Boolean to Control Panel Color
            boolean noAnsweredPanelPainted = false;

            while (questionResultSet.next()) {
                if (questionResultSet.getString(7) == null) {
                    JPanel questionItemPanel = new JPanel(new GridLayout(3, 1));

                    questionItemPanel.setPreferredSize(new Dimension(600, 100));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    if (!noAnsweredPanelPainted) {
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
                            QuestionDetailView questionDetailView = new QuestionDetailView();
                            QuestionDetailController questionDetailController = new QuestionDetailController(question, questionDetailView);
                        }
                    });

                    normalUserView.getNoAnswerListPanel().add(questionItemPanel, normalUserView.getConstraints(), 0);

                    normalUserView.getConstraints().weighty = 0;

                    noAnsweredPanelPainted = !noAnsweredPanelPainted;
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
