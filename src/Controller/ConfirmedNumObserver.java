package Controller;

import View.UpdateInfoView;

public class ConfirmedNumObserver extends Observer {
    private Subject subject;

    public ConfirmedNumObserver(Subject subject) {
        this.subject = subject;

        this.subject.attach(this);
    }

    @Override
    public void update(UpdateInfoView updateInfoView) {
        updateInfoView.getConfirmedNumField().setText(String.valueOf(subject.getState().getConfirmedNum()));
    }
}
