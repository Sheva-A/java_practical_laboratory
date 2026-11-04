package app;

import container.Box;
import container.ObjectBox;
import model.Title;
import model.TitleNoEquals;
import model.TitleOnlyEquals;
import service.ListUtil;
import service.TitleCollections;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println("Практична робота 6. Варіант 0: тайтл стрімінгового сервісу Title (id, назва, рік), ключ рівності id");
        experiment1Generics();
        experiment2Collections();
        experiment3EqualsHashCode();
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1Generics() {
        header("ДОСЛІД 1. Узагальнений контейнер і контейнер на Object");

        Box<Title> box = new Box<>();
        box.put(new Title(1, "Відьмак", 2019));
        Title fromBox = box.get();
        System.out.println("  Box<Title> box;  box.put(new Title(...));  Title t = box.get();");
        System.out.println("    t -> " + fromBox);

        System.out.println();
        ObjectBox objBox = new ObjectBox();
        objBox.put(new Title(2, "Дюна", 2021));
        Title fromObjBox = (Title) objBox.get();
        System.out.println("  ObjectBox objBox;  objBox.put(new Title(...));  Title t = (Title) objBox.get();");
        System.out.println("    t -> " + fromObjBox);

        System.out.println();
        System.out.println("  objBox.put(\"abc\");  Title t = (Title) objBox.get();");
        objBox.put("abc");
        try {
            Title wrong = (Title) objBox.get();
            System.out.println("    t -> " + wrong);
        } catch (ClassCastException e) {
            e.printStackTrace(System.out);
        }

        System.out.println();
        List<Title> titles = List.of(new Title(1, "Відьмак", 2019), new Title(2, "Дюна", 2021));
        List<String> names = List.of("Відьмак", "Дюна");
        Title firstTitle = ListUtil.first(titles);
        String firstName = ListUtil.first(names);
        System.out.println("  Узагальнений метод <T> T first(List<T> list):");
        System.out.println("    first(titles) -> " + firstTitle);
        System.out.println("    first(names)  -> " + firstName);

        System.out.println();
        List<Integer> ints = List.of(10, 20, 30);
        List<Number> nums = List.of(1, 2.5, 4L);
        System.out.println("  total(List<? extends Number> list):");
        System.out.println("    total(ints) -> " + ListUtil.total(ints));
        System.out.println("    total(nums) -> " + ListUtil.total(nums));
    }

    private static void experiment2Collections() {
        header("ДОСЛІД 2. ArrayList, LinkedList і HashSet на 100 000 об'єктів");

        for (int run = 1; run <= 3; run++) {
            List<Title> arrayList = new ArrayList<>();
            List<Title> linkedList = new LinkedList<>();
            Set<Title> hashSet = new HashSet<>();
            TitleCollections.fill(arrayList);
            TitleCollections.fill(linkedList);
            TitleCollections.fill(hashSet);

            long arrayInsert = TitleCollections.measureInsert(arrayList);
            long arrayContains = TitleCollections.measureContains(arrayList);
            long linkedInsert = TitleCollections.measureInsert(linkedList);
            long linkedContains = TitleCollections.measureContains(linkedList);
            long setContains = TitleCollections.measureContains(hashSet);

            System.out.println("  Замір " + run + ":");
            System.out.printf("    ArrayList   insert x1000: %4d мс    contains x1000: %4d мс%n", arrayInsert, arrayContains);
            System.out.printf("    LinkedList  insert x1000: %4d мс    contains x1000: %4d мс%n", linkedInsert, linkedContains);
            System.out.printf("    HashSet                              contains x1000: %4d мс%n", setContains);
        }

        System.out.println();
        List<Title> titles = new ArrayList<>();
        TitleCollections.fill(titles);
        System.out.println("  Видалення з ArrayList тайтлів з id % 25000 == 0 у циклі for-each, size = " + titles.size());
        try {
            TitleCollections.removeForEach(titles);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("    size після for-each -> " + titles.size());

        System.out.println();
        titles = new ArrayList<>();
        TitleCollections.fill(titles);
        System.out.println("  Те саме видалення через Iterator, size = " + titles.size());
        TitleCollections.removeWithIterator(titles);
        System.out.println("    size після Iterator -> " + titles.size());
    }

    private static void experiment3EqualsHashCode() {
        header("ДОСЛІД 3. equals і hashCode у HashSet");

        checkSet("без equals і hashCode:",
                new TitleNoEquals(1, "Відьмак", 2019),
                new TitleNoEquals(1, "Відьмак", 2019),
                new TitleNoEquals(1, "Відьмак", 2019));
        checkSet("лише equals:",
                new TitleOnlyEquals(1, "Відьмак", 2019),
                new TitleOnlyEquals(1, "Відьмак", 2019),
                new TitleOnlyEquals(1, "Відьмак", 2019));
        checkSet("equals і hashCode:",
                new Title(1, "Відьмак", 2019),
                new Title(1, "Відьмак", 2019),
                new Title(1, "Відьмак", 2019));

        System.out.println();
        Title title = new Title(1, "Відьмак", 2019);
        Set<Title> titles = new HashSet<>();
        titles.add(title);
        System.out.println("  Зміна id в об'єкта, що вже лежить у HashSet:");
        System.out.println("    до зміни: contains(title) -> " + titles.contains(title));
        title.setId(99);
        System.out.println("    title.setId(99);");
        System.out.println("    contains(title)              -> " + titles.contains(title));
        System.out.println("    contains(new Title(99, ...)) -> " + titles.contains(new Title(99, "Відьмак", 2019)));
        System.out.println("    contains(new Title(1, ...))  -> " + titles.contains(new Title(1, "Відьмак", 2019)));
        System.out.println("    size = " + titles.size() + ", titles -> " + titles);
    }

    // Два об'єкти з однаковим id додаються в HashSet, третій перевіряється через contains
    private static <T> void checkSet(String label, T a, T b, T c) {
        Set<T> set = new HashSet<>();
        set.add(a);
        set.add(b);
        System.out.printf("  %-24s size = %d, contains = %b%n", label, set.size(), set.contains(c));
        System.out.println("    a.equals(b) = " + a.equals(b) + ", a.hashCode() = " + a.hashCode() + ", b.hashCode() = " + b.hashCode());
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
