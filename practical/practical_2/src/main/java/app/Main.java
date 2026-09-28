package app;

import model.AccessChecker;
import model.Product;
import model.ProductNoThis;

public class Main {

    public static void main(String[] args) {
        experiment1AccessModifiers();
        experiment2Constructors();
        experiment3This();
        experiment4Static();
        experiment5Final();
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1AccessModifiers() {
        header("ДОСЛІД 1. Модифікатори доступу");
        Product laptop = new Product("Ноутбук", 25999.99, 3, "Електроніка");

        AccessChecker.check(laptop);

        System.out.println("  Звернення з app.Main (інший пакет, не підклас):");
        System.out.println("    private   name     : ПОМИЛКА КОМПІЛЯЦІЇ (name has private access in Product)");
        System.out.println("    default   price    : ПОМИЛКА КОМПІЛЯЦІЇ (price is not public in Product; cannot be accessed from outside package)");
        System.out.println("    protected quantity : ПОМИЛКА КОМПІЛЯЦІЇ (quantity has protected access in Product)");
        System.out.println("    public    category : OK -> " + laptop.category);
        System.out.println("    private name через public-геттер getName(): OK -> " + laptop.getName());
    }

    private static void experiment2Constructors() {
        header("ДОСЛІД 2. Види конструкторів");

        System.out.println("  a) new Product()");
        Product p1 = new Product();
        System.out.println("     -> " + p1);

        System.out.println("  b) new Product(\"Смартфон\", 15499.50, 5, \"Електроніка\")");
        Product p2 = new Product("Смартфон", 15499.50, 5, "Електроніка");
        System.out.println("     -> " + p2);

        System.out.println("  c) new Product(\"Кавоварка\", 3200.00) через this(...)");
        Product p3 = new Product("Кавоварка", 3200.00);
        System.out.println("     -> " + p3);
    }

    private static void experiment3This() {
        header("ДОСЛІД 3. Ключове слово this");

        Product withThis = new Product("Мишка", 499.00, 10, "Периферія");
        System.out.println("  З this   (this.name = name): " + withThis);

        ProductNoThis withoutThis = new ProductNoThis("Мишка", 499.00, 10);
        System.out.println("  Без this (name = name)     : " + withoutThis);
        System.out.println("  Висновок: без this параметр затіняє поле, поля лишаються null / 0.0 / 0");
    }

    private static void experiment4Static() {
        header("ДОСЛІД 4. static-поле та static-метод");

        int before = Product.getCount();
        System.out.println("  Product.getCount() до створення = " + before);

        Product a = new Product("Олівець", 12.50, 100, "Канцелярія");
        Product b = new Product("Зошит", 35.00, 40, "Канцелярія");
        Product c = new Product("Гумка", 8.00, 60, "Канцелярія");

        System.out.println("  a: " + a.staticInfo());
        System.out.println("  b: " + b.staticInfo());
        System.out.println("  c: " + c.staticInfo());
        System.out.println("  Product.getCount() після створення = " + Product.getCount()
                + " (зросло на " + (Product.getCount() - before) + ")");
    }

    private static void experiment5Final() {
        header("ДОСЛІД 5. final-поле та final-змінна");

        Product monitor = new Product("Монітор", 7999.00, 2, "Електроніка");
        monitor.tryToChangeFinals();

        final int localFinal = 5;
        System.out.println("  final int localFinal = " + localFinal + ", localFinal = 6 : ПОМИЛКА КОМПІЛЯЦІЇ (cannot assign a value to final variable localFinal)");
        System.out.println("  id товару після всіх операцій не змінився: " + monitor.getId());
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
