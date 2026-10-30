package app;

import exception.InvalidGradeException;
import service.GradeFile;
import service.GradeParser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Практична робота 5. Варіант 0: оцінки студентів, InvalidGradeException");
        experiment1CheckedUnchecked();
        experiment2CustomException();
        experiment3Files();
        System.out.println("\nУсі досліди виконано успішно.");
    }

    private static void experiment1CheckedUnchecked() {
        header("ДОСЛІД 1. Перевірювані та неперевірювані винятки");

        System.out.println("  GradeFile.open(\"grades.txt\"), файл існує:");
        GradeFile.open("grades.txt");

        System.out.println();
        System.out.println("  GradeFile.open(\"missing.txt\"), файлу немає:");
        GradeFile.open("missing.txt");

        System.out.println();
        System.out.println("  GradeFile.openWithThrows(\"missing.txt\"), виняток обробляє Main:");
        try {
            GradeFile.openWithThrows("missing.txt");
        } catch (IOException e) {
            System.out.println("    Main перехопив: " + e);
        }

        System.out.println();
        int[] grades = {87, 92, 78};
        System.out.println("  int[] grades = {87, 92, 78};  grades[3]");
        try {
            System.out.println("    grades[3] -> " + grades[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Під час виконання: " + e);
        }
    }

    private static void experiment2CustomException() {
        header("ДОСЛІД 2. Власний виняток InvalidGradeException");

        System.out.println("  throw new InvalidGradeException(e.getMessage());");
        try {
            GradeParser.parseWithoutCause("abc");
        } catch (InvalidGradeException e) {
            e.printStackTrace(System.out);
            System.out.println("  e.getCause() -> " + e.getCause());
        }

        System.out.println();
        System.out.println("  throw new InvalidGradeException(\"Некоректна оцінка\", e);");
        try {
            GradeParser.parseWithCause("abc");
        } catch (InvalidGradeException e) {
            e.printStackTrace(System.out);
            System.out.println("  e.getCause() -> " + e.getCause());
        }
    }

    private static void experiment3Files() throws IOException {
        header("ДОСЛІД 3. Запис і читання файлу з кирилицею");

        String[] lines = {"Коваленко Петро;87", "Бондар Олена;92", "Мельник Іван;78"};

        System.out.println("  BufferedWriter без close() -> grades_no_close.txt, читання UTF-8:");
        GradeFile.writeWithoutClose("grades_no_close.txt", lines);
        GradeFile.read("grades_no_close.txt", StandardCharsets.UTF_8);

        System.out.println();
        System.out.println("  BufferedWriter у try-with-resources -> grades_closed.txt, читання UTF-8:");
        GradeFile.writeWithResources("grades_closed.txt", lines);
        GradeFile.read("grades_closed.txt", StandardCharsets.UTF_8);

        System.out.println();
        System.out.println("  Читання grades_closed.txt як windows-1251:");
        GradeFile.read("grades_closed.txt", Charset.forName("windows-1251"));
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }
}
