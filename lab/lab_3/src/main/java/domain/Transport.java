package domain;

public abstract class Transport {
    private final String name;
    private final double costPerKm;

    protected Transport(String name, double costPerKm) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "назва не може бути порожньою");
        }
        if (costPerKm <= 0) {
            throw new IllegalArgumentException(
                    "вартість кілометра має бути більшою за 0");
        }
        this.name = name;
        this.costPerKm = costPerKm;
    }

    public String getName() {
        return name;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    // Домовленість: distance >= 0, результат >= 0
    public double tripCost(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException(
                    "відстань не може бути від'ємною");
        }
        return distance * costPerKm;
    }
}
