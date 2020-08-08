package Controller;

import Model.Instruction;
import Model.User;
import View.EditTipView;
import View.TipDetailView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TipDetailController {
    private Connection connection;
    private PreparedStatement statement;

    private Instruction tip;
    private User user;
    private TipDetailView tipDetailView;

    public TipDetailController(Instruction tip, User user, TipDetailView tipDetailView) {
        this.tip = tip;
        this.user = user;
        this.tipDetailView = tipDetailView;

        init();
    }

    private void init() {
        tipDetailView.getTitleLabel().setText("Title: " + tip.getTitle());
        tipDetailView.getTypeLabel().setText("Type: " + tip.getType());
        tipDetailView.getPublisherLabel().setText("Published By: " + tip.getPublisher() + " < " + tip.getEmail() + " >");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            tipDetailView.getTimeLabel().setText("Published Time: " + sdf.format(sdf.parse(tip.getTime().toString())));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        tipDetailView.getContentArea().setText(tip.getContent());

        if (!user.getClass().getSimpleName().equals("Admin")) {
            tipDetailView.getEditButton().setVisible(false);
            tipDetailView.getDeleteButton().setVisible(false);
        }

        tipDetailView.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipDetailView.getTipFrame().dispose();
            }
        });

        tipDetailView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTipView editTipView = new EditTipView();
                EditTipController editTipController = new EditTipController(tip, user, editTipView);

                tipDetailView.getTipFrame().dispose();
            }
        });

        tipDetailView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTip();
            }
        });
    }

    private void deleteTip() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete? ");

        if (dialogResult == JOptionPane.YES_OPTION) {
            connectDB();

            if (tip.getEmail().equals(user.getEmail())) {
                String sql = "DELETE From tips WHERE id = ?";

                try {
                    statement = connection.prepareStatement(sql);

                    statement.setString(1, tip.getId());

                    int resultSet = statement.executeUpdate();

                    if (resultSet > 0) {
                        JOptionPane.showMessageDialog(null, "Tip Deleted! ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Tip Delete Failed! ");
                    }

                    tipDetailView.getTipFrame().dispose();
                } catch (Exception ep) {
                    ep.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, You cannot Delete other Admin's Tip! ");
            }
        }
    }

    private void connectDB() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_pandemic?useSSL=false&serverTimezone=UTC";
        String name = "root";
        String pwd = "19951124";

        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url, name, pwd);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver cannot be Found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
