package model;

public class Transport {

    public String label = "транспорт (поле Transport)";

    protected String model;
    protected int maxSpeed;

    public Transport(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        System.out.println("  -> [Transport] конструктор: model=\"" + model + "\", maxSpeed=" + maxSpeed);
    }

    public String move() {
        return "Транспорт \"" + model + "\" рухається зі швидкістю до " + maxSpeed + " км/год";
    }

    public String describe() {
        return "базовий (Transport)";
    }

    public String getModel() {
        return model;
    }
}
