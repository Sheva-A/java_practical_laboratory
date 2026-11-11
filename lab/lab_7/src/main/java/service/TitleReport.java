package service;

import model.Title;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TitleReport {

    // Кількість і середній рейтинг у кожному жанрі, TreeMap впорядковує жанри за алфавітом
    public static Map<String, DoubleSummaryStatistics> statsByGenre(List<Title> titles) {
        return titles.stream()
                .collect(Collectors.groupingBy(Title::getGenre, TreeMap::new,
                        Collectors.summarizingDouble(Title::getRating)));
    }

    // П'ять тайтлів з найвищим рейтингом, за однакового рейтингу за id
    public static List<Title> top5(List<Title> titles) {
        return titles.stream()
                .sorted(Comparator.comparingDouble(Title::getRating).reversed()
                        .thenComparing(Title::getId))
                .limit(5)
                .toList();
    }

    // Пошук за ключем, для відсутнього ключа порожній Optional
    public static Optional<Title> findById(List<Title> titles, String id) {
        return titles.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
}
