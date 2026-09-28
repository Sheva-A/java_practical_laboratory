package app;

import domain.Cart;
import domain.CartItem;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.add(new CartItem("Навушники", 1500.00, 1));
        cart.add(new CartItem("Чохол", 350.00, 2));
        CartItem cable = new CartItem("Кабель USB-C", 200.00);
        cart.add(cable);
        print(cart);

        header("Спроби порушити стан ззовні");

        List<CartItem> items = cart.getItems();
        items.clear();
        items.add(new CartItem("Чужий товар", 9999.00));
        result("змінити перелік товарів ззовні", "на кошик не впливає, Разом: " + money(cart.getTotal()));

        tryAction("додати товар з ціною -10", () -> cart.add(new CartItem("Зарядка", -10.00, 1)));
        tryAction("додати товар з порожньою назвою", () -> cart.add(new CartItem(" ", 100.00)));
        tryAction("додати товар з кількістю 0", () -> cart.add(new CartItem("Скло", 150.00, 0)));
        tryAction("встановити кількість -3 для кабелю", () -> cable.setQuantity(-3));
        tryAction("додати кабель у той самий кошик вдруге", () -> cart.add(cable));

        Cart other = new Cart();
        tryAction("додати кабель в інший кошик", () -> other.add(cable));

        result("cable.attachTo(other)", "ПОМИЛКА КОМПІЛЯЦІЇ (attachTo(Cart) is not public in CartItem; cannot be accessed from outside package)");
        result("cable.price = 1", "ПОМИЛКА КОМПІЛЯЦІЇ (price has private access in CartItem)");
        result("cart.items", "ПОМИЛКА КОМПІЛЯЦІЇ (items has private access in Cart)");
        System.out.println("Разом після всіх спроб: " + money(cart.getTotal()));

        header("Контрольовані зміни");

        cable.setQuantity(3);
        System.out.println("кількість кабелю змінено на 3");
        cart.remove(2);
        System.out.println("товар #2 видалено з кошика");
        print(cart);
    }

    private static void print(Cart cart) {
        System.out.println("Кошик #" + cart.getId());
        for (CartItem item : cart.getItems()) {
            System.out.println("  + " + item);
        }
        System.out.println("Разом: " + money(cart.getTotal()));
    }

    private static void tryAction(String description, Runnable action) {
        try {
            action.run();
            result(description, "виконано");
        } catch (IllegalArgumentException | IllegalStateException e) {
            result(description, "відхилено: " + e.getMessage());
        }
    }

    private static void result(String description, String outcome) {
        System.out.printf("%-42s -> %s%n", description, outcome);
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    private static String money(double value) {
        return String.format(Locale.ROOT, "%.2f", value);
    }
}
