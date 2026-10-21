package app;

import domain.Bus;
import domain.Car;
import domain.Motorcycle;
import domain.Transport;
import domain.Truck;

import java.util.Arrays;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Transport[] fleet = {
                new Car("Toyota Corolla", 3.0, 50),
                new Truck("Volvo FH", 6.0, 5),
                new Motorcycle("Honda CB500", 2.0, true)
        };
        System.out.println("Вартість поїздки на 100 км:");
        printTripCosts(fleet, 100);

        Transport[] extendedFleet = Arrays.copyOf(fleet, fleet.length + 1);
        extendedFleet[fleet.length] = new Bus("Mercedes Sprinter", 5.0, 300);
        System.out.println();
        System.out.println("Після додавання різновиду Bus (код обробки не змінено):");
        printTripCosts(extendedFleet, 100);
    }

    static void printTripCosts(Transport[] fleet, double distance) {
        for (Transport t : fleet) {
            System.out.printf(Locale.ROOT, "%-12s %-18s -> %8.2f%n",
                    t.getClass().getSimpleName(), t.getName(),
                    t.tripCost(distance));
        }
    }
}
