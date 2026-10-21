package domain;

public final class Truck extends Transport {
    private final double cargoTons;

    public Truck(String name, double costPerKm, double cargoTons) {
        super(name, costPerKm);
        if (cargoTons < 0) {
            throw new IllegalArgumentException(
                    "маса вантажу не може бути від'ємною");
        }
        this.cargoTons = cargoTons;
    }

    @Override
    public double tripCost(double distance) {
        return super.tripCost(distance) * (1 + 0.1 * cargoTons);
    }
}
