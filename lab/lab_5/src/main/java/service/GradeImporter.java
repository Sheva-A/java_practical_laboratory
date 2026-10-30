package service;

import exception.ImportException;
import exception.InvalidGradeException;
import model.GradeRecord;
import model.GradeStats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class GradeImporter {

    // Файл читається потоково, по одному рядку
    public static GradeStats read(String inputFile) throws ImportException {
        GradeStats stats = new GradeStats();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
            reader.readLine(); // заголовок
            int lineNumber = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    stats.add(GradeRecord.parse(line));
                } catch (InvalidGradeException e) {
                    // Пошкоджений рядок відхиляється, імпорт триває
                    stats.reject();
                    String reason = e.getMessage();
                    if (e.getCause() != null) {
                        reason += "; причина: " + e.getCause();
                    }
                    System.out.println("Рядок " + lineNumber + ": " + reason);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ImportException("файл " + inputFile + " не знайдено", e);
        } catch (IOException e) {
            throw new ImportException("не вдалося прочитати файл " + inputFile, e);
        }
        return stats;
    }

    public static void writeReport(GradeStats stats, String inputFile, String outputFile) throws ImportException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
            writer.write("Звіт імпорту оцінок");
            writer.newLine();
            writer.write("Вхідний файл: " + inputFile);
            writer.newLine();
            writer.write("Кількість записів: " + stats.getCount());
            writer.newLine();
            writer.write("Відхилено рядків: " + stats.getRejected());
            writer.newLine();
            writer.write(String.format(Locale.ROOT, "Середній бал: %.2f", stats.getAverage()));
            writer.newLine();
            writer.write("Мінімальний бал: " + stats.getMin());
            writer.newLine();
            writer.write("Максимальний бал: " + stats.getMax());
            writer.newLine();
        } catch (IOException e) {
            throw new ImportException("не вдалося записати звіт у " + outputFile, e);
        }
    }
}
