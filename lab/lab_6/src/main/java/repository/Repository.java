package repository;

import model.Keyed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Узагальнене сховище записів будь-якого типу з ключем
public class Repository<K, T extends Keyed<K>> implements Iterable<T> {

    // LinkedHashMap: пошук за ключем без перебору і порядок записів як у файлі
    private final Map<K, T> records = new LinkedHashMap<>();

    // Повтор ключа не замінює запис, що вже є у сховищі
    public boolean add(T record) {
        return records.putIfAbsent(record.getKey(), record) == null;
    }

    // null, якщо запису з таким ключем немає
    public T findByKey(K key) {
        return records.get(key);
    }

    public int size() {
        return records.size();
    }

    // Нова відсортована копія, сховище при цьому не змінюється
    public List<T> sorted(Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>(records.values());
        result.sort(comparator);
        return result;
    }

    // Обхід через подання лише для читання, remove() через ітератор заборонено
    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableCollection(records.values()).iterator();
    }
}
