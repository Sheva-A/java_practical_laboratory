package app;

import model.Title;
import service.TitleService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Практична робота 7. Варіант 0: тайтл стрімінгового сервісу Title (id, назва, рік)");

        List<Title> titles = List.of(
                new Title(1, "Відьмак", 2019),
                new Title(2, "Дюна", 2021),
                new Title(3, "Матриця", 1999),
                new Title(4, "Інтерстеллар", 2014),
                new Title(5, "Дуже дивні справи", 2016),
                new Title(6, "Темний лицар", 2008),
                new Title(7, "Оппенгеймер", 2023),
                new Title(8, "Шерлок", 2010));

        experiment1Sorting(titles);
        experiment2Stream(titles);
        experiment3Optional(titles);
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1Sorting(List<Title> titles) {
        header("ДОСЛІД 1. Сортування за роком трьома способами");
        printList("Початковий список:", titles);

        List<Title> byAnonymous = new ArrayList<>(titles);
        byAnonymous.sort(new Comparator<>() {
            @Override
            public int compare(Title a, Title b) {
                return Integer.compare(a.getYear(), b.getYear());
            }
        });
        printList("Анонімний клас new Comparator<>() {...}:", byAnonymous);

        List<Title> byLambda = new ArrayList<>(titles);
        byLambda.sort((a, b) -> Integer.compare(a.getYear(), b.getYear()));
        printList("Лямбда-вираз (a, b) -> Integer.compare(a.getYear(), b.getYear()):", byLambda);

        List<Title> byComparing = new ArrayList<>(titles);
        byComparing.sort(Comparator.comparingInt(Title::getYear));
        printList("Comparator.comparingInt(Title::getYear):", byComparing);

        System.out.println();
        System.out.println("  Порядок у трьох списках однаковий: "
                + (byAnonymous.equals(byLambda) && byLambda.equals(byComparing)));
    }

    private static void experiment2Stream(List<Title> titles) {
        header("ДОСЛІД 2. Цикл for-each і Stream API: назви тайтлів, що вийшли після 2015 року");

        List<String> loop = TitleService.namesAfter2015Loop(titles);
        List<String> stream = TitleService.namesAfter2015Stream(titles);
        System.out.println("  цикл:  " + loop);
        System.out.println("  потік: " + stream);
        System.out.println("  loop.equals(stream) -> " + loop.equals(stream));

        System.out.println();
        System.out.println("  Конвеєр з peek, збережений у змінну Stream<String> pipeline:");
        Stream<String> pipeline = titles.stream()
                .peek(t -> System.out.println("    peek " + t))
                .filter(t -> t.getYear() > 2015)
                .map(Title::getName);
        System.out.println("    pipeline.toList() -> " + pipeline.toList());
    }

    private static void experiment3Optional(List<Title> titles) {
        header("ДОСЛІД 3. findFirst і Optional");

        search(titles, "Пошук без результату: тайтл, що вийшов до 1950 року", t -> t.getYear() < 1950);
        System.out.println();
        search(titles, "Пошук за умовою досліду 2: тайтл, що вийшов після 2015 року", t -> t.getYear() > 2015);
    }

    // Пошук першого тайтлу за умовою, результат отримується через get() і через map(...).orElse(...)
    private static void search(List<Title> titles, String label, Predicate<Title> condition) {
        Optional<Title> found = titles.stream()
                .filter(condition)
                .findFirst();
        System.out.println("  " + label);
        System.out.println("    findFirst() -> " + found);

        System.out.print("    get() -> ");
        try {
            System.out.println(found.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace(System.out);
        }

        String name = found.map(Title::getName).orElse("не знайдено");
        System.out.println("    map(Title::getName).orElse(\"не знайдено\") -> " + name);
    }

    private static void printList(String label, List<Title> list) {
        System.out.println();
        System.out.println("  " + label);
        for (Title t : list) {
            System.out.println("    " + t);
        }
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
