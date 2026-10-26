package service;

import cost.CostCalculator;
import delivery.Delivery;

import java.util.Locale;

public class DeliveryService {
    private final Delivery delivery;
    private final CostCalculator costCalculator;

    // Обидві залежності передаються ззовні через конструктор
    public DeliveryService(Delivery delivery, CostCalculator costCalculator) {
        this.delivery = delivery;
        this.costCalculator = costCalculator;
    }

    public void deliver(String orderNumber, double weight) {
        System.out.println("  -> " + delivery.deliver(orderNumber));
        System.out.printf(Locale.ROOT,
                "  -> Вартість доставки (%.1f кг): %.2f грн%n",
                weight, costCalculator.calculate(weight));
    }
}
