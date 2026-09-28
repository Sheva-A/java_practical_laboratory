package domain;

import java.util.Locale;

public final class CartItem {

    private static int nextId = 1;

    private final int id;
    private final String name;
    private final double price;
    private int quantity;
    private Cart cart;

    public CartItem(String name, double price, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("назва не може бути порожньою");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("ціна має бути більшою за 0");
        }
        checkQuantity(quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = nextId++;
    }

    public CartItem(String name, double price) {
        this(name, price, 1);
    }

    public void setQuantity(int quantity) {
        checkQuantity(quantity);
        this.quantity = quantity;
    }

    private static void checkQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("кількість має бути більшою за 0");
        }
    }

    void attachTo(Cart cart) {
        if (this.cart != null) {
            throw new IllegalStateException("товар #" + id + " уже належить кошику");
        }
        this.cart = cart;
    }

    void detach() {
        this.cart = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "#%d %-15s %8.2f x %d", id, name, price, quantity);
    }
}
