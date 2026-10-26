package app;

import model.Adjustable;
import model.Device;
import model.Heater;
import model.Lamp;
import model.Switchable;
import service.ConsoleLogger;
import service.DeviceService;
import service.DeviceServiceWithNew;
import service.MemoryLogger;

public class Main {

    public static void main(String[] args) {
        System.out.println("Практична робота 4. Варіант 1: Device -> Lamp, Heater; Switchable, Adjustable; журнал подій");
        experiment1AbstractClass();
        experiment2Interfaces();
        experiment3Dependency();
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1AbstractClass() {
        header("ДОСЛІД 1. Абстрактний клас і його похідні");

        Device heater = new Heater("Масляний обігрівач");
        Device lamp = new Lamp("Настільна лампа");
        System.out.println("  Device heater = new Heater(...);  Device lamp = new Lamp(...);");
        System.out.println("  heater.info() -> " + heater.info());
        System.out.println("  heater.work() -> " + heater.work());
        System.out.println("  lamp.info()   -> " + lamp.info());
        System.out.println("  lamp.work()   -> " + lamp.work());
        System.out.println("  info() успадковано з Device, work() реалізовано в кожному похідному класі");
    }

    private static void experiment2Interfaces() {
        header("ДОСЛІД 2. Один об'єкт через посилання двох інтерфейсів");

        Lamp lamp = new Lamp("Торшер");

        Switchable s = lamp;
        s.turnOn();
        System.out.println("  Switchable s = lamp;");
        System.out.println("  s.turnOn();  s.isOn() -> " + s.isOn());
        System.out.println("  Через s доступні лише turnOn(), turnOff(), isOn(), describe()");

        System.out.println();
        Adjustable a = lamp;
        a.setLevel(70);
        System.out.println("  Adjustable a = lamp;");
        System.out.println("  a.setLevel(70);  a.getLevel() -> " + a.getLevel());
        System.out.println("  Через a доступні лише setLevel(), getLevel(), describe()");

        System.out.println();
        System.out.println("  lamp.work() -> " + lamp.work());
        System.out.println("  s і a посилаються на той самий об'єкт lamp");

        System.out.println();
        System.out.println("  Конфлікт describe() розв'язано в Lamp через Switchable.super і Adjustable.super:");
        System.out.println("  lamp.describe() -> " + lamp.describe());
    }

    private static void experiment3Dependency() {
        header("ДОСЛІД 3. Залежність сервісу: new усередині і передача через конструктор");

        Device heater = new Heater("Конвектор");

        System.out.println("  new DeviceServiceWithNew().start(heater);");
        new DeviceServiceWithNew().start(heater);

        System.out.println();
        System.out.println("  new DeviceService(new ConsoleLogger()).start(heater);");
        new DeviceService(new ConsoleLogger()).start(heater);

        System.out.println();
        MemoryLogger memory = new MemoryLogger();
        System.out.println("  MemoryLogger memory = new MemoryLogger();");
        System.out.println("  new DeviceService(memory).start(heater);");
        new DeviceService(memory).start(heater);
        System.out.println("  memory.getEntries() -> " + memory.getEntries());
        System.out.println("  Клас DeviceService при цьому не змінювався");
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
