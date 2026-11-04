package service;

import model.Title;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TitleCollections {

    public static final int SIZE = 100_000;
    public static final int OPERATIONS = 1_000;

    public static void fill(Collection<Title> collection) {
        for (int i = 0; i < SIZE; i++) {
            collection.add(new Title(i, "Тайтл " + i, 2000 + i % 25));
        }
    }

    // 1000 вставок у середину списку, час у мілісекундах
    public static long measureInsert(List<Title> list) {
        long t0 = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            list.add(list.size() / 2, new Title(SIZE + i, "Новий тайтл " + i, 2026));
        }
        return (System.nanoTime() - t0) / 1_000_000;
    }

    // 1000 викликів contains, час у мілісекундах
    public static long measureContains(Collection<Title> collection) {
        long t0 = System.nanoTime();
        for (int i = 0; i < OPERATIONS; i++) {
            collection.contains(new Title(i * 100, "", 0));
        }
        return (System.nanoTime() - t0) / 1_000_000;
    }

    // Видалення прямо в циклі for-each
    public static void removeForEach(List<Title> list) {
        for (Title t : list) {
            if (t.getId() % 25_000 == 0) {
                list.remove(t);
            }
        }
    }

    // Видалення через Iterator
    public static void removeWithIterator(List<Title> list) {
        Iterator<Title> iterator = list.iterator();
        while (iterator.hasNext()) {
            Title t = iterator.next();
            if (t.getId() % 25_000 == 0) {
                iterator.remove();
            }
        }
    }
}
