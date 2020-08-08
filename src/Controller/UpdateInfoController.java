package Controller;

import Model.Info;
import View.UpdateInfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateInfoController {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet infoResultSet;

    private Info info;
    private UpdateInfoView updateInfoView;

    // Create a Subject
    // Use Observer Pattern to Assure that the Change of School will Change other 4 Numbers ( Confirmed, Infected, Cured & Death )
    private Subject subject;

    public UpdateInfoController(UpdateInfoView updateInfoView) {
        this.updateInfoView = updateInfoView;

        init();
    }

    private void init() {
        updateInfoView.getSchoolComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                getSchoolInfo(e);
            }
        });

        updateInfoView.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSchoolInfo();
            }
        });

        updateInfoView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInfoView.getUpdateInfoFrame().dispose();
            }
        });
    }

    private void getSchoolInfo(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (updateInfoView.getSchoolComboBox().getSelectedIndex() != 0) {
                connectDB();

                try {
                    String sql = "SELECT * FROM infos WHERE school = ?;";

                    statement = connection.prepareStatement(sql);

                    statement.setString(1, updateInfoView.getSchoolComboBox().getSelectedItem().toString());

                    infoResultSet = statement.executeQuery();

                    if (infoResultSet.next()) {
                        info = new Info(infoResultSet.getString(2), infoResultSet.getInt(3), infoResultSet.getInt(4), infoResultSet.getInt(5), infoResultSet.getInt(6));
                    }

                    // Observer ! ! !
                    subject = new SchoolSubject();

                    // Create Observers
                    new ConfirmedNumObserver(subject);
                    new InfectedNumObserver(subject);
                    new CuredNumObserver(subject);
                    new DeathNumObserver(subject);

                    subject.setState(info, updateInfoView);

                } catch (Exception ep) {
                    ep.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Please Select a School to Update COVID-19 Info! ");
            }
        }
    }

    private void updateSchoolInfo() {
        if (updateInfoView.getSchoolComboBox().getSelectedIndex() != 0) {
            connectDB();

            String sql = "UPDATE infos SET confirmedNum = ?, infectedNum = ?, curedNum = ?, deathNum = ? WHERE school = ?";

            try {
                statement = connection.prepareStatement(sql);

                int confirmedNum = Integer.parseInt(updateInfoView.getInfectedNumField().getText()) + Integer.parseInt(updateInfoView.getCuredNumField().getText()) + Integer.parseInt(updateInfoView.getDeathNumField().getText());

                statement.setInt(1, confirmedNum);
                statement.setInt(2, Integer.parseInt(updateInfoView.getInfectedNumField().getText()));
                statement.setInt(3, Integer.parseInt(updateInfoView.getCuredNumField().getText()));
                statement.setInt(4, Integer.parseInt(updateInfoView.getDeathNumField().getText()));
                statement.setString(5, updateInfoView.getSchoolComboBox().getSelectedItem().toString());

                int resultSet = statement.executeUpdate();

                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Info Updated! ");
                } else {
                    JOptionPane.showMessageDialog(null, "Info Update Failed! ");
                }

                updateInfoView.getUpdateInfoFrame().dispose();
            } catch (Exception ep) {
                ep.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No School Selected, Please Select One! ");
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
