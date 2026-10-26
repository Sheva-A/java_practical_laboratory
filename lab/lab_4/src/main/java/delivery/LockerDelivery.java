package delivery;

public final class LockerDelivery extends Delivery implements Trackable {

    public LockerDelivery() {
        super("поштомат", 2);
    }

    @Override
    protected String route() {
        return "доставка в поштомат";
    }

    @Override
    public String track(String orderNumber) {
        return "LK-" + orderNumber + ", посилка очікує в поштоматі";
    }
}
