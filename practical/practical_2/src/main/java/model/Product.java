package model;

import java.util.Locale;

public class Product {

    private String name;
    double price;
    protected int quantity;
    public String category;

    private static int count = 0;

    private final int id;

    public Product() {
        this.name = "Без назви";
        this.price = 0.0;
        this.quantity = 0;
        this.category = "Загальне";
        this.id = ++count;
        System.out.println("  [Product()] створено товар з типовими значеннями, id=" + id);
    }

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.id = ++count;
        System.out.println("  [Product(name, price, quantity, category)] створено \"" + name + "\", id=" + id);
    }

    public Product(String name, double price) {
        this(name, price, 1, "Загальне");
        System.out.println("  [Product(name, price)] -> делеговано через this(...), quantity=1 за замовчуванням");
    }

    public static int getCount() {
        return count;
    }

    public String staticInfo() {
        return String.format("name=\"%s\" (своє поле), count=%d (спільне static-поле)", name, count);
    }

    public void tryToChangeFinals() {
        final int maxQuantity = 100;
        System.out.println("  final-поле id = " + id + ", локальна final maxQuantity = " + maxQuantity);
        System.out.println("  this.id = 999      : ПОМИЛКА КОМПІЛЯЦІЇ (cannot assign a value to final variable id)");
        System.out.println("  maxQuantity = 200  : ПОМИЛКА КОМПІЛЯЦІЇ (cannot assign a value to final variable maxQuantity)");

        int notFinal = maxQuantity;
        notFinal = 200;
        System.out.println("  звичайна локальна змінна notFinal змінена на " + notFinal + ", без помилок");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "Product{id=%d, name='%s', price=%.2f, quantity=%d, category='%s'}",
                id, name, price, quantity, category);
    }
}
