package Controller;

import Model.Instruction;
import View.NotificationDetailView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NotificationDetailController {
    private Instruction notification;
    private NotificationDetailView notificationDetailView;

    public NotificationDetailController(Instruction notification, NotificationDetailView notificationDetailView) {
        this.notification = notification;
        this.notificationDetailView = notificationDetailView;

        init();
    }

    private void init() {
        notificationDetailView.getTitleLabel().setText("Title: " + notification.getTitle());
        notificationDetailView.getTypeLabel().setText("Type: " + notification.getType());
        notificationDetailView.getPublisherLabel().setText("Published By: " + notification.getPublisher() + " < " + notification.getEmail() + " >");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            notificationDetailView.getTimeLabel().setText("Published Time: " + sdf.format(sdf.parse(notification.getTime().toString())));
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        notificationDetailView.getContentArea().setText(notification.getContent());

        notificationDetailView.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationDetailView.getNotificationFrame().dispose();
            }
        });
    }
}
