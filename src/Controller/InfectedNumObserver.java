package Controller;

import View.UpdateInfoView;

public class InfectedNumObserver extends Observer {
    private Subject subject;

    public InfectedNumObserver(Subject subject) {
        this.subject = subject;

        this.subject.attach(this);
    }

    @Override
    public void update(UpdateInfoView updateInfoView) {
        updateInfoView.getInfectedNumField().setText(String.valueOf(subject.getState().getInfectedNum()));
    }
}
