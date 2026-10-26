package delivery;

public final class PickupDelivery extends Delivery {

    public PickupDelivery() {
        super("самовивіз", 2);
    }

    @Override
    protected String route() {
        return "отримання в магазині";
    }
}
