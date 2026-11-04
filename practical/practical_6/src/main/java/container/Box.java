package container;

// Узагальнений контейнер, тип вмісту задається параметром T
public class Box<T> {

    private T value;

    public void put(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
