package Controller;

import View.UpdateInfoView;

public class DeathNumObserver extends Observer {
    private Subject subject;

    public DeathNumObserver(Subject subject) {
        this.subject = subject;

        this.subject.attach(this);
    }

    @Override
    public void update(UpdateInfoView updateInfoView) {
        updateInfoView.getDeathNumField().setText(String.valueOf(subject.getState().getDeathNum()));
    }
}
