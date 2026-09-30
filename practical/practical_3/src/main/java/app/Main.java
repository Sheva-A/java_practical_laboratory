package app;

import model.Car;
import model.Motorcycle;
import model.Transport;

public class Main {

    public static void main(String[] args) {
        System.out.println("Практична робота 3. Варіант 1: Transport -> Car, Motorcycle");
        experiment1Override();
        experiment2Constructors();
        experiment3FieldsAndMethods();
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1Override() {
        header("ДОСЛІД 1. Перевизначення методу з @Override і виклик super");

        Transport transport = new Transport("Базовий транспорт", 60);
        Car car = new Car("Toyota Corolla", 180, 4);
        Motorcycle moto = new Motorcycle("Yamaha MT-07", 210, false);

        System.out.println();
        System.out.println("  Transport.move() : " + transport.move());
        System.out.println("  Car.move()       : " + car.move());
        System.out.println("  Motorcycle.move(): " + moto.move());
        System.out.println("  Частина до \";\" у Car і Motorcycle отримана з батьківської версії через super.move()");
    }

    private static void experiment2Constructors() {
        header("ДОСЛІД 2. Порядок виклику конструкторів у ієрархії");

        System.out.println("  Transport b = new Car(\"Honda Civic\", 200, 4);");
        Transport b = new Car("Honda Civic", 200, 4);
        System.out.println("  b.move(): " + b.move());

        System.out.println();
        System.out.println("  Transport m = new Motorcycle(\"Ural\", 105, true);");
        Transport m = new Motorcycle("Ural", 105, true);
        System.out.println("  m.move(): " + m.move());

        System.out.println();
        System.out.println("  Без super(model, maxSpeed) у конструкторі Car: ПОМИЛКА КОМПІЛЯЦІЇ");
        System.out.println("    (constructor Transport in class Transport cannot be applied to given types)");

        // Car wrong = new Transport("Базовий транспорт", 60);
        System.out.println("  Car d = new Transport(...): ПОМИЛКА КОМПІЛЯЦІЇ");
        System.out.println("    (incompatible types: Transport cannot be converted to Car)");

        System.out.println();
        System.out.println("  Спроба обійти перевірку явним приведенням: Car d = (Car) new Transport(...)");
        try {
            Car d = (Car) new Transport("Базовий транспорт", 60);
            System.out.println("  " + d.getDoors());
        } catch (ClassCastException e) {
            System.out.println("  ClassCastException під час виконання: об'єкт Transport не є Car");
        }
    }

    private static void experiment3FieldsAndMethods() {
        header("ДОСЛІД 3. Поле за типом посилання, метод за типом об'єкта");

        Transport ref = new Car("Skoda Octavia", 220, 5);
        Car carRef = (Car) ref;

        System.out.println();
        System.out.println("  Transport ref = new Car(...);  Car carRef = (Car) ref;  (той самий об'єкт)");
        System.out.println("  ref.label         -> " + ref.label);
        System.out.println("  carRef.label      -> " + carRef.label);
        System.out.println("  ref.describe()    -> " + ref.describe());
        System.out.println("  carRef.describe() -> " + carRef.describe());
        System.out.println("  Усередині Car: " + carRef.bothLabels());

        System.out.println();
        System.out.println("  Transport motoRef = new Motorcycle(...);");
        Transport motoRef = new Motorcycle("Honda CBR600RR", 250, false);
        System.out.println("  motoRef.label      -> " + motoRef.label);
        System.out.println("  motoRef.describe() -> " + motoRef.describe());
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
