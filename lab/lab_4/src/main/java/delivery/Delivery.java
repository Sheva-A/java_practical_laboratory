package delivery;

public abstract class Delivery {
    private final String name;
    private final int days;

    protected Delivery(String name, int days) {
        this.name = name;
        this.days = days;
    }

    // Спільна поведінка всіх способів доставки
    public String deliver(String orderNumber) {
        return "Замовлення " + orderNumber + ": " + name + ", "
                + route() + ", строк " + days + " дн.";
    }

    protected abstract String route();
}
