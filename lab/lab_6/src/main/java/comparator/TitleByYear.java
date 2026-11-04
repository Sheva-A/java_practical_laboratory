package comparator;

import model.Title;

import java.util.Comparator;

// Критерій сортування тайтлів: рік виходу
public class TitleByYear implements Comparator<Title> {

    @Override
    public int compare(Title a, Title b) {
        return Integer.compare(a.getYear(), b.getYear());
    }
}
