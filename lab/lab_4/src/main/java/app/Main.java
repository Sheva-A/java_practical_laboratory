package app;

import cost.CostCalculator;
import cost.FixedCost;
import cost.WeightCost;
import delivery.CourierDelivery;
import delivery.LockerDelivery;
import delivery.PickupDelivery;
import delivery.PostDelivery;
import delivery.Trackable;
import service.DeliveryService;

public class Main {

    public static void main(String[] args) {
        // Точка збирання: усі об'єкти створюються і з'єднуються тут
        CourierDelivery courier = new CourierDelivery();
        PostDelivery post = new PostDelivery();
        PickupDelivery pickup = new PickupDelivery();

        CostCalculator byWeight = new WeightCost(50, 20);
        CostCalculator fixed = new FixedCost(120);
        CostCalculator free = new FixedCost(0);

        System.out.println("Конфігурація 1: кур'єр + вартість за вагою");
        new DeliveryService(courier, byWeight).deliver("A-101", 2.5);
        printTracking(courier, "A-101");

        System.out.println();
        System.out.println("Конфігурація 2: кур'єр + фіксована вартість");
        new DeliveryService(courier, fixed).deliver("A-102", 2.5);
        printTracking(courier, "A-102");

        System.out.println();
        System.out.println("Конфігурація 3: пошта + вартість за вагою");
        new DeliveryService(post, byWeight).deliver("A-103", 2.5);
        printTracking(post, "A-103");

        System.out.println();
        System.out.println("Конфігурація 4: самовивіз + безкоштовно");
        new DeliveryService(pickup, free).deliver("A-104", 2.5);

        // Нова реалізація, DeliveryService не змінювався
        LockerDelivery locker = new LockerDelivery();

        System.out.println();
        System.out.println("Конфігурація 5: поштомат + фіксована вартість");
        new DeliveryService(locker, fixed).deliver("A-105", 2.5);
        printTracking(locker, "A-105");
    }

    private static void printTracking(Trackable trackable, String orderNumber) {
        System.out.println("  -> Відстеження: " + trackable.track(orderNumber));
    }
}
