package model;

public interface Switchable {

    void turnOn();

    void turnOff();

    boolean isOn();

    default String describe() {
        return "Switchable: " + (isOn() ? "увімкнено" : "вимкнено");
    }
}
