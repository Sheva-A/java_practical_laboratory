package model;

public class Motorcycle extends Transport {

    public String label = "мотоцикл (поле Motorcycle)";

    private final boolean hasSidecar;

    public Motorcycle(String model, int maxSpeed, boolean hasSidecar) {
        super(model, maxSpeed);
        this.hasSidecar = hasSidecar;
        System.out.println("  -> [Motorcycle] конструктор: hasSidecar=" + hasSidecar);
    }

    @Override
    public String move() {
        return super.move() + "; мотоцикл їде на 2 колесах"
                + (hasSidecar ? " з коляскою" : " без коляски");
    }

    @Override
    public String describe() {
        return "похідний (Motorcycle)";
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }
}
