package model;

// Клас не перевизначає equals і hashCode
public class TitleNoEquals {

    private final int id;
    private final String name;
    private final int year;

    public TitleNoEquals(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
