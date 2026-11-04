package app;

import comparator.TitleByYear;
import comparator.UserByRegistrationDate;
import model.Keyed;
import model.Title;
import model.User;
import repository.Repository;
import service.RecordFile;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Лабораторна робота 6. Варіант 0: стрімінговий сервіс, тайтли і користувачі");

        // Тайтли
        List<String> titleLines = RecordFile.readLines("v0_titles.txt");
        Repository<String, Title> titles = new Repository<>();
        for (String line : titleLines) {
            titles.add(Title.parse(line));
        }
        System.out.println();
        System.out.println("Тайтли: рядків у файлі " + titleLines.size() + ", записів у сховищі " + titles.size());
        find(titles, "T-1017");
        find(titles, "T-2521");
        find(titles, "T-0000");

        List<Title> titlesByYear = titles.sorted(new TitleByYear());
        System.out.println("За роком виходу (" + titlesByYear.size() + " записів):");
        for (Title title : titlesByYear) {
            System.out.println("  " + title.getYear() + " " + title.getName());
        }

        // Користувачі, те саме сховище
        List<String> userLines = RecordFile.readLines("v0_users.txt");
        Repository<String, User> users = new Repository<>();
        for (String line : userLines) {
            users.add(User.parse(line));
        }
        System.out.println();
        System.out.println("Користувачі: рядків у файлі " + userLines.size() + ", записів у сховищі " + users.size());
        find(users, "nova36");
        find(users, "rocket79");
        find(users, "guest00");

        List<User> usersByDate = users.sorted(new UserByRegistrationDate());
        System.out.println("За датою реєстрації (" + usersByDate.size() + " записів):");
        for (User user : usersByDate) {
            System.out.println("  " + user.getRegistrationDate() + " " + user.getFullName() + " (" + user.getKey() + ")");
        }

        // Обхід і захист внутрішнього стану
        System.out.println();
        System.out.println("Обхід і захист сховища тайтлів:");
        int films = 0;
        int series = 0;
        for (Title title : titles) {
            if (title.getType().equals("серіал")) {
                series++;
            } else {
                films++;
            }
        }
        System.out.println("  for-each: фільмів " + films + ", серіалів " + series);

        try {
            titles.iterator().remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("  titles.iterator().remove() -> " + e);
        }

        titlesByYear.clear();
        System.out.println("  titlesByYear.clear() -> у сховищі записів " + titles.size());
    }

    // Узагальнений пошук, працює зі сховищем будь-якого типу записів
    private static <K, T extends Keyed<K>> void find(Repository<K, T> repository, K key) {
        T record = repository.findByKey(key);
        if (record == null) {
            System.out.println("Пошук " + key + ": не знайдено");
        } else {
            System.out.println("Пошук " + key + ": " + record);
        }
    }
}
