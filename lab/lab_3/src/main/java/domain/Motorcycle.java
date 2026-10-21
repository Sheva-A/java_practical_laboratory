package domain;

public final class Motorcycle extends Transport {
    private final boolean withPassenger;

    public Motorcycle(String name, double costPerKm, boolean withPassenger) {
        super(name, costPerKm);
        this.withPassenger = withPassenger;
    }

    @Override
    public double tripCost(double distance) {
        return super.tripCost(distance) * (withPassenger ? 1.2 : 1.0);
    }
}
