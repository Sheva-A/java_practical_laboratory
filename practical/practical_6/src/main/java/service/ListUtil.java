package service;

import java.util.List;

public class ListUtil {

    // Узагальнений метод, тип T визначається за списком, який передано
    public static <T> T first(List<T> list) {
        return list.get(0);
    }

    // List<? extends Number> приймає список Number і будь-якого його підтипу
    public static double total(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }
}
