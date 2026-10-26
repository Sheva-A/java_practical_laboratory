package model;

public class Lamp extends Device implements Switchable, Adjustable {

    private boolean on;
    private int level;

    public Lamp(String name) {
        super(name);
    }

    @Override
    public String work() {
        if (on) {
            return "Лампа \"" + name + "\" світить з яскравістю " + level + "%";
        }
        return "Лампа \"" + name + "\" вимкнена";
    }

    @Override
    public void turnOn() {
        on = true;
    }

    @Override
    public void turnOff() {
        on = false;
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public String describe() {
        return Switchable.super.describe() + "; " + Adjustable.super.describe();
    }
}
