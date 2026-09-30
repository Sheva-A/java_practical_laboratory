package model;

public class Car extends Transport {

    public String label = "автомобіль (поле Car)";

    private final int doors;

    public Car(String model, int maxSpeed, int doors) {
        super(model, maxSpeed);
        // Якщо прибрати рядок вище, компілятор видасть помилку:
        // constructor Transport in class Transport cannot be applied to given types;
        //   required: String,int
        //   found:    no arguments
        this.doors = doors;
        System.out.println("  -> [Car] конструктор: doors=" + doors);
    }

    @Override
    public String move() {
        return super.move()
                + "; автомобіль їде дорогою на 4 колесах, дверей: " + doors;
    }

    @Override
    public String describe() {
        return "похідний (Car)";
    }

    public String bothLabels() {
        return "this.label = \"" + this.label + "\", super.label = \"" + super.label + "\"";
    }

    public int getDoors() {
        return doors;
    }
}
