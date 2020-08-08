package Controller;

import Model.Info;
import View.UpdateInfoView;

import java.util.ArrayList;
import java.util.List;

public class SchoolSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    private Info info;

    public Info getState() {
        return info;
    }

    public void setState(Info info, UpdateInfoView updateInfoView) {
        this.info = info;

        notifyObservers(updateInfoView);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(UpdateInfoView updateInfoView) {
        for (Observer observer: observers) {
            observer.update(updateInfoView);
        }
    }
}
