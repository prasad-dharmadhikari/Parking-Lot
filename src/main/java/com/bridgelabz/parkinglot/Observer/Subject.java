package com.bridgelabz.parkinglot.Observer;

public interface Subject {
    public void register(Observer o);
    public void unRegister(Observer o);
    public void notifyObservers();
}
