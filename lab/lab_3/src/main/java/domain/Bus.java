package domain;

public final class Bus extends Transport {
    private final double driverFee;

    public Bus(String name, double costPerKm, double driverFee) {
        super(name, costPerKm);
        if (driverFee < 0) {
            throw new IllegalArgumentException(
                    "оплата водія не може бути від'ємною");
        }
        this.driverFee = driverFee;
    }

    @Override
    public double tripCost(double distance) {
        return super.tripCost(distance) + driverFee;
    }
}
