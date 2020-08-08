package Controller;

import Model.Instruction;
import Model.User;
import View.EditRumorView;
import View.RumorDetailView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RumorDetailController {
    private Connection connection;
    private PreparedStatement statement;

    private Instruction rumor;
    private User user;
    private RumorDetailView rumorDetailView;

    public RumorDetailController(Instruction rumor, User user, RumorDetailView rumorDetailView) {
        this.rumor = rumor;
        this.user = user;
        this.rumorDetailView = rumorDetailView;

        init();
    }

    private void init() {
        rumorDetailView.getTitleLabel().setText("Title: " + rumor.getTitle());
        rumorDetailView.getTypeLabel().setText("Type: " + rumor.getType());
        rumorDetailView.getPublisherLabel().setText("Published By: " + rumor.getPublisher() + " < " + rumor.getEmail() + " >");

        rumorDetailView.getContentArea().setText(rumor.getContent());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            rumorDetailView.getTimeLabel().setText("Published Time: " + sdf.format(sdf.parse(rumor.getTime().toString())));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        if (!user.getClass().getSimpleName().equals("Admin")) {
            rumorDetailView.getEditButton().setVisible(false);
            rumorDetailView.getDeleteButton().setVisible(false);
        }

        rumorDetailView.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rumorDetailView.getRumorFrame().dispose();
            }
        });

        rumorDetailView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditRumorView editRumorView = new EditRumorView();
                EditRumorController editRumorController = new EditRumorController(rumor, user, editRumorView);

                rumorDetailView.getRumorFrame().dispose();
            }
        });

        rumorDetailView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRumor();
            }
        });
    }

    private void deleteRumor() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete? ");

        if (dialogResult == JOptionPane.YES_OPTION) {
            connectDB();

            if (rumor.getEmail().equals(user.getEmail())) {
                String sql = "DELETE From rumors WHERE id = ?";

                try {
                    statement = connection.prepareStatement(sql);

                    statement.setString(1, rumor.getId());

                    int resultSet = statement.executeUpdate();

                    if (resultSet > 0) {
                        JOptionPane.showMessageDialog(null, "Rumor Deleted! ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Rumor Delete Failed! ");
                    }

                    rumorDetailView.getRumorFrame().dispose();
                } catch (Exception ep) {
                    ep.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, You cannot Delete other Admin's Rumor! ");
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
