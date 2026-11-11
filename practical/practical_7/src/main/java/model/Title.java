package model;

// Тайтл стрімінгового сервісу з практичної роботи 6, без equals і hashCode
public class Title {

    private final int id;
    private final String name;
    private final int year;

    public Title(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Title{id=" + id + ", name='" + name + "', year=" + year + "}";
    }
}
