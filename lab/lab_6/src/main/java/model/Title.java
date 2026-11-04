package model;

// Тайтл стрімінгового сервісу, ключ id. Поля final, сетерів немає
public final class Title implements Keyed<String> {

    private final String id;
    private final String name;
    private final String type;
    private final int year;
    private final double rating;

    public Title(String id, String name, String type, int year, double rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
        this.rating = rating;
    }

    // Розбір рядка формату id;назва;тип;рік;рейтинг
    public static Title parse(String line) {
        String[] parts = line.split(";");
        return new Title(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
    }

    @Override
    public String getKey() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return name + ", " + year + ", рейтинг " + rating;
    }
}
