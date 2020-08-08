package Controller;

import View.UpdateInfoView;

public abstract class Observer {
    private Subject subject;

    public abstract void update(UpdateInfoView updateInfoView);
}
