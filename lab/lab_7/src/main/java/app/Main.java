package app;

import model.Title;
import service.RecordFile;
import service.TitleReport;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        if (args.length != 2) {
            System.out.println("Використання: java -jar report.jar <файл з даними> <ключ запису>");
            return;
        }
        String fileName = args[0];
        String key = args[1];

        // Рядки перетворюються на об'єкти, повтори відкидає distinct() за equals і hashCode
        List<String> lines = RecordFile.readLines(fileName);
        List<Title> titles = lines.stream()
                .map(Title::parse)
                .distinct()
                .toList();
        System.out.println("Рядків у файлі: " + lines.size() + ", унікальних записів: " + titles.size());

        System.out.println("Жанри (кількість, середній рейтинг):");
        for (Map.Entry<String, DoubleSummaryStatistics> entry : TitleReport.statsByGenre(titles).entrySet()) {
            DoubleSummaryStatistics stats = entry.getValue();
            System.out.printf(Locale.ROOT, "  %-12s %3d  %.2f%n", entry.getKey(), stats.getCount(), stats.getAverage());
        }

        System.out.println("Топ-5 за рейтингом:");
        for (Title title : TitleReport.top5(titles)) {
            System.out.printf(Locale.ROOT, "  %.1f  %s  %s%n", title.getRating(), title.getId(), title.getName());
        }

        String found = TitleReport.findById(titles, key)
                .map(Title::toString)
                .orElse("не знайдено");
        System.out.println("Пошук " + key + ": " + found);
    }
}
