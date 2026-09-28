package model;

public class AccessChecker {

    public static void check(Product p) {
        System.out.println("  Звернення з model.AccessChecker (той самий пакет):");
        System.out.println("    private   name     : ПОМИЛКА КОМПІЛЯЦІЇ (name has private access in Product)");
        System.out.println("    default   price    : OK -> " + p.price);
        System.out.println("    protected quantity : OK -> " + p.quantity);
        System.out.println("    public    category : OK -> " + p.category);

        p.price = p.price * 0.9;
        p.quantity = p.quantity + 1;
        System.out.println("    після зміни з AccessChecker: " + p);
    }
}
