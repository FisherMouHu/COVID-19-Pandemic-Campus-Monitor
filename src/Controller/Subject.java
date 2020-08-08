package Controller;

import Model.Info;
import View.UpdateInfoView;

public interface Subject {
    public Info getState();

    public void setState(Info info, UpdateInfoView updateInfoView);

    public void attach(Observer observer);

    public void notifyObservers(UpdateInfoView updateInfoView);
}
