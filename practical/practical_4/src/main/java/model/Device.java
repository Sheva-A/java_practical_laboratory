package model;

public abstract class Device {

    protected final String name;

    public Device(String name) {
        this.name = name;
    }

    public abstract String work();

    public String info() {
        return "Пристрій \"" + name + "\"";
    }
}
