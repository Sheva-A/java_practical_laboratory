package domain;

import java.util.ArrayList;
import java.util.List;

public final class Cart {

    private static int nextId = 1;

    private final int id;
    private final List<CartItem> items = new ArrayList<>();

    public Cart() {
        this.id = nextId++;
    }

    public void add(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("товар не може бути null");
        }
        item.attachTo(this);
        items.add(item);
    }

    public boolean remove(int itemId) {
        for (CartItem item : items) {
            if (item.getId() == itemId) {
                item.detach();
                return items.remove(item);
            }
        }
        return false;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }
}
