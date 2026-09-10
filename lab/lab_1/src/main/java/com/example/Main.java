package com.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Конвертер одиниць ===");
        System.out.println("1) Площа");

        // Меню вибору величини
        int choice = 0;
        while (choice != 1) {
            System.out.print("Вибір: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice != 1) {
                    System.out.println("-> Помилка: введіть 1. Спробуйте ще раз.");
                }
            } else {
                System.out.println("-> Помилка: введіть число. Спробуйте ще раз.");
                scanner.nextLine(); // пропустити рядок з некоректним вводом
            }
        }

        System.out.println("Одиниці: cm2 (см²)  m2 (м²)  a (ар)  ha (га)  acre (акр)");

        // Вибір напрямку конвертації
        String fromUnit = readUnit(scanner, "З (cm2/m2/a/ha/acre): ");
        String toUnit   = readUnit(scanner, "У (cm2/m2/a/ha/acre): ");

        // Основний цикл: вводимо значення
        boolean running = true;
        while (running) {
            System.out.print("Значення (0 — вихід): ");

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (value == 0) {
                    System.out.println("-> Вихід. Дякуємо за використання!");
                    running = false;
                } else {
                    double result = convert(value, fromUnit, toUnit);
                    System.out.printf("-> %.6g %s = %.6g %s%n", value, fromUnit, result, toUnit);
                }
            } else {
                System.out.println("-> Помилка: введіть число. Спробуйте ще раз.");
                scanner.nextLine(); // пропустити рядок з некоректним вводом
            }
        }

        scanner.close();
    }

    // Допоміжний метод: зчитати коректну одиницю вимірювання
    static String readUnit(Scanner scanner, String prompt) {
        String unit = "";
        while (!isValidUnit(unit)) {
            System.out.print(prompt);
            unit = scanner.next().trim().toLowerCase();
            if (!isValidUnit(unit)) {
                System.out.println("-> Помилка: невідома одиниця. Спробуйте ще раз.");
            }
        }
        return unit;
    }


    // Перевірка: чи одиниця входить до допустимого набору
    static boolean isValidUnit(String unit) {
        return unit.equals("cm2")
            || unit.equals("m2")
            || unit.equals("a")
            || unit.equals("ha")
            || unit.equals("acre");
    }

    // Конвертація: спочатку у базову одиницю (м²), потім у цільову
    static double convert(double value, String from, String to) {
        double inM2 = toSquareMeters(value, from);
        return fromSquareMeters(inM2, to);
    }

    // Перетворення В м²
    static double toSquareMeters(double value, String unit) {
        switch (unit) {
            case "cm2":  return value * 0.0001;
            case "m2":   return value;
            case "a":    return value * 100.0;
            case "ha":   return value * 10_000.0;
            case "acre": return value * 4046.8564224;
            default:     return 0;
        }
    }

    // Перетворення З м²
    static double fromSquareMeters(double m2, String unit) {
        switch (unit) {
            case "cm2":  return m2 / 0.0001;
            case "m2":   return m2;
            case "a":    return m2 / 100.0;
            case "ha":   return m2 / 10_000.0;
            case "acre": return m2 / 4046.8564224;
            default:     return 0;
        }
    }
}
