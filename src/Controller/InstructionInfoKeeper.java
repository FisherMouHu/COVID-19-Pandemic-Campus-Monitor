package Controller;

import Model.Instruction;
import Model.User;
import View.NotificationDetailView;
import View.RumorDetailView;
import View.TipDetailView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class InstructionInfoKeeper {
    public JPanel getNotificationInfo(SimpleDateFormat sdf, boolean panelPainted, Instruction notification) {
        JPanel notificationItemPanel = new JPanel(new GridLayout(3, 1));

        notificationItemPanel.setPreferredSize(new Dimension(600, 100));

        if (!panelPainted) {
            notificationItemPanel.setBackground(Color.white);
        }
        else {
            notificationItemPanel.setBackground(Color.lightGray);
        }

        try {
            JLabel notificationTitleLabel = new JLabel("Title: " + notification.getTitle());
            JLabel notificationPublisherLabel = new JLabel("Published by: " + notification.getPublisher() + " < " + notification.getEmail() + " >");
            JLabel notificationTimeLabel = new JLabel("Published Time: " + sdf.format(sdf.parse(notification.getTime().toString())));

            notificationItemPanel.add(notificationTitleLabel);
            notificationItemPanel.add(notificationPublisherLabel);
            notificationItemPanel.add(notificationTimeLabel);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        notificationItemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NotificationDetailView notificationDetailView = new NotificationDetailView();
                NotificationDetailController notificationDetailController = new NotificationDetailController(notification, notificationDetailView);
            }
        });

        return notificationItemPanel;
    }

    public JPanel getRumorInfo(SimpleDateFormat sdf, boolean panelPainted, Instruction rumor, User user) {
        JPanel rumorItemPanel = new JPanel(new GridLayout(3, 1));

        rumorItemPanel.setPreferredSize(new Dimension(600, 100));

        if (!panelPainted) {
            rumorItemPanel.setBackground(Color.white);
        } else {
            rumorItemPanel.setBackground(Color.lightGray);
        }

        try {
            JLabel rumorTitleLabel = new JLabel("Title: " + rumor.getTitle());
            JLabel rumorPublisherLabel = new JLabel("Published by: " + rumor.getPublisher() + " < " + rumor.getEmail() + " >");
            JLabel rumorTimeLabel = new JLabel("Published Time: " + sdf.format(sdf.parse(rumor.getTime().toString())));

            rumorItemPanel.add(rumorTitleLabel);
            rumorItemPanel.add(rumorPublisherLabel);
            rumorItemPanel.add(rumorTimeLabel);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        rumorItemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RumorDetailView rumorDetailView = new RumorDetailView();
                RumorDetailController rumorDetailController = new RumorDetailController(rumor, user, rumorDetailView);
            }
        });

        return rumorItemPanel;
    }

    public JPanel getTipInfo(SimpleDateFormat sdf, boolean panelPainted, Instruction tip, User user) {
        JPanel tipItemPanel = new JPanel(new GridLayout(3, 1));

        tipItemPanel.setPreferredSize(new Dimension(600, 100));

        if (!panelPainted) {
            tipItemPanel.setBackground(Color.white);
        }
        else {
            tipItemPanel.setBackground(Color.lightGray);
        }

        try {
            JLabel tipTitleLabel = new JLabel("Title: " + tip.getTitle());
            JLabel tipPublisherLabel = new JLabel("Published by: " + tip.getPublisher() + " < " + tip.getEmail() + " >");
            JLabel tipTimeLabel = new JLabel("Published Time: " + sdf.format(sdf.parse(tip.getTime().toString())));

            tipItemPanel.add(tipTitleLabel);
            tipItemPanel.add(tipPublisherLabel);
            tipItemPanel.add(tipTimeLabel);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        tipItemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TipDetailView tipDetailView = new TipDetailView();
                TipDetailController tipDetailController = new TipDetailController(tip, user, tipDetailView);
            }
        });

        return tipItemPanel;
    }
}
