package app;

import exception.ImportException;
import model.GradeStats;
import service.GradeImporter;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        if (args.length != 2) {
            System.out.println("Використання: java -jar importer.jar <вхідний файл> <файл звіту>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        // Помилки файлів показуються користувачу без стектрейсу, але з причиною
        try {
            GradeStats stats = GradeImporter.read(inputFile);
            GradeImporter.writeReport(stats, inputFile, outputFile);
            System.out.println("Імпортовано: " + stats.getCount() + ", відхилено: " + stats.getRejected()
                    + ". Звіт записано у " + outputFile);
        } catch (ImportException e) {
            System.out.println("Помилка: " + e.getMessage() + "; причина: " + e.getCause());
        }
    }
}
