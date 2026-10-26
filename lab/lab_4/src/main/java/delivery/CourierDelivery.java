package delivery;

public final class CourierDelivery extends Delivery implements Trackable {

    public CourierDelivery() {
        super("кур'єр", 1);
    }

    @Override
    protected String route() {
        return "доставка до дверей";
    }

    @Override
    public String track(String orderNumber) {
        return "CR-" + orderNumber + ", кур'єр прямує до отримувача";
    }
}
