package cost;

public final class FixedCost implements CostCalculator {
    private final double price;

    public FixedCost(double price) {
        this.price = price;
    }

    @Override
    public double calculate(double weight) {
        return price;
    }
}
