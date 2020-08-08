package Controller;

import View.UpdateInfoView;

public class CuredNumObserver extends Observer {
    private Subject subject;

    public CuredNumObserver(Subject subject) {
        this.subject = subject;

        this.subject.attach(this);
    }

    @Override
    public void update(UpdateInfoView updateInfoView) {
        updateInfoView.getCuredNumField().setText(String.valueOf(subject.getState().getCuredNum()));
    }
}
