package domain;

public final class Car extends Transport {
    private final double parkingFee;

    public Car(String name, double costPerKm, double parkingFee) {
        super(name, costPerKm);
        if (parkingFee < 0) {
            throw new IllegalArgumentException(
                    "плата за паркування не може бути від'ємною");
        }
        this.parkingFee = parkingFee;
    }

    @Override
    public double tripCost(double distance) {
        return super.tripCost(distance) + parkingFee;
    }
}
