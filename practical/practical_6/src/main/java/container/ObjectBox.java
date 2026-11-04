package container;

// Контейнер на Object, приймає значення будь-якого типу
public class ObjectBox {

    private Object value;

    public void put(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}
