package cost;

public final class WeightCost implements CostCalculator {
    private final double basePrice;
    private final double pricePerKg;

    public WeightCost(double basePrice, double pricePerKg) {
        this.basePrice = basePrice;
        this.pricePerKg = pricePerKg;
    }

    @Override
    public double calculate(double weight) {
        return basePrice + pricePerKg * weight;
    }
}
