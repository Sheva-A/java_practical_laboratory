package service;

import model.Title;

import java.util.ArrayList;
import java.util.List;

public class TitleService {

    // Назви тайтлів, що вийшли після 2015 року, через цикл for-each
    public static List<String> namesAfter2015Loop(List<Title> titles) {
        List<String> names = new ArrayList<>();
        for (Title t : titles) {
            if (t.getYear() > 2015) {
                names.add(t.getName());
            }
        }
        return names;
    }

    // Те саме одним виразом Stream API
    public static List<String> namesAfter2015Stream(List<Title> titles) {
        return titles.stream()
                .filter(t -> t.getYear() > 2015)
                .map(Title::getName)
                .toList();
    }
}
