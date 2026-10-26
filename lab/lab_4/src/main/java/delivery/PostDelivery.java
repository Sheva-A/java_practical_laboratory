package delivery;

public final class PostDelivery extends Delivery implements Trackable {

    public PostDelivery() {
        super("пошта", 3);
    }

    @Override
    protected String route() {
        return "доставка у відділення";
    }

    @Override
    public String track(String orderNumber) {
        return "UP-" + orderNumber + ", посилка в сортувальному центрі";
    }
}
