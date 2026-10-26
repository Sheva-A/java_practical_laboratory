package model;

public class Heater extends Device {

    public Heater(String name) {
        super(name);
    }

    @Override
    public String work() {
        return "Обігрівач \"" + name + "\" гріє приміщення";
    }
}
