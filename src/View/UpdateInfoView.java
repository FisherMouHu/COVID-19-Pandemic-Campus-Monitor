package View;

import javax.swing.*;
import java.awt.*;

public class UpdateInfoView {
    private JFrame updateInfoFrame;
    private JPanel schoolPanel;
    private JPanel confirmedNumPanel;
    private JPanel infectedNumPanel;
    private JPanel curedNumPanel;
    private JPanel deathNumPanel;
    private JLabel schoolLabel;
    private JLabel confirmedNumLabel;
    private JLabel infectedNumLabel;
    private JLabel curedNumLabel;
    private JLabel deathNumLabel;
    private JTextField confirmedNumField;
    private JTextField infectedNumField;
    private JTextField curedNumField;
    private JTextField deathNumField;
    private JComboBox<String> schoolComboBox;
    private JButton updateButton;
    private JButton cancelButton;

    public UpdateInfoView() {
        updateInfoFrame = new JFrame("Update COVID-19 Info");

        updateInfoFrame.getContentPane().setLayout(new FlowLayout());

        schoolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmedNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infectedNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        curedNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deathNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        schoolPanel.setPreferredSize(new Dimension(400, 30));
        confirmedNumPanel.setPreferredSize(new Dimension(400, 30));
        infectedNumPanel.setPreferredSize(new Dimension(400, 30));
        curedNumPanel.setPreferredSize(new Dimension(400, 30));
        deathNumPanel.setPreferredSize(new Dimension(400, 30));

        schoolLabel = new JLabel("Select a School: ");
        confirmedNumLabel = new JLabel("Confirmed Infected: ");
        infectedNumLabel = new JLabel("Active Infected: ");
        curedNumLabel = new JLabel("Cured: ");
        deathNumLabel = new JLabel("Death: ");

        confirmedNumField = new JTextField();
        confirmedNumField.setColumns(20);
        confirmedNumField.setEditable(false);

        infectedNumField = new JTextField();
        infectedNumField.setColumns(22);

        curedNumField = new JTextField();
        curedNumField.setColumns(27);

        deathNumField = new JTextField();
        deathNumField.setColumns(27);

        String[] schools = new String[] {"-- Select a School --", "College of Arts and Sciences", "Leavey School of Business", "School of Engineering", "School of Education and Counseling Psychology", "School of Law", "Jesuit School of Theology"};

        schoolComboBox = new JComboBox<>(schools);
        schoolComboBox.setPreferredSize(new Dimension(280, 30));

        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(90, 35));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(90, 35));

        updateInfoFrame.add(schoolPanel);
        updateInfoFrame.add(confirmedNumPanel);
        updateInfoFrame.add(infectedNumPanel);
        updateInfoFrame.add(curedNumPanel);
        updateInfoFrame.add(deathNumPanel);
        updateInfoFrame.add(updateButton);
        updateInfoFrame.add(cancelButton);

        schoolPanel.add(schoolLabel);
        schoolPanel.add(schoolComboBox);

        confirmedNumPanel.add(confirmedNumLabel);
        confirmedNumPanel.add(confirmedNumField);

        infectedNumPanel.add(infectedNumLabel);
        infectedNumPanel.add(infectedNumField);

        curedNumPanel.add(curedNumLabel);
        curedNumPanel.add(curedNumField);

        deathNumPanel.add(deathNumLabel);
        deathNumPanel.add(deathNumField);

        updateInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateInfoFrame.setSize(400, 245);
        updateInfoFrame.setLocationRelativeTo(null);
        updateInfoFrame.setResizable(false);
        updateInfoFrame.setVisible(true);
    }

    public JFrame getUpdateInfoFrame() {
        return updateInfoFrame;
    }

    public JTextField getConfirmedNumField() {
        return confirmedNumField;
    }

    public JTextField getInfectedNumField() {
        return infectedNumField;
    }

    public JTextField getCuredNumField() {
        return curedNumField;
    }

    public JTextField getDeathNumField() {
        return deathNumField;
    }

    public JComboBox<String> getSchoolComboBox() {
        return schoolComboBox;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
