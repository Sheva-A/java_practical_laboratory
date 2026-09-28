package model;

public class ProductNoThis {

    String name;
    double price;
    int quantity;

    public ProductNoThis(String name, double price, int quantity) {
        name = name;
        price = price;
        quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductNoThis{name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}
