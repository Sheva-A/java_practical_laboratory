package model;

import java.util.Objects;

// Тайтл стрімінгового сервісу, ключ id. Поля final, сетерів немає
public final class Title {

    private final String id;
    private final String name;
    private final String genre;
    private final int year;
    private final double rating;

    public Title(String id, String name, String genre, int year, double rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    // Розбір рядка формату id;назва;жанр;рік;рейтинг
    public static Title parse(String line) {
        String[] parts = line.split(";");
        return new Title(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    // Рівність за ключем id: повтор рядка дає рівний об'єкт
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Title other = (Title) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (" + year + "), рейтинг " + rating;
    }
}
