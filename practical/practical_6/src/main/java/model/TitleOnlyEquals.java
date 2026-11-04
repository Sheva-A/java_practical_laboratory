package model;

// Клас перевизначає лише equals за ключем id, hashCode успадковано від Object
public class TitleOnlyEquals {

    private final int id;
    private final String name;
    private final int year;

    public TitleOnlyEquals(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TitleOnlyEquals other = (TitleOnlyEquals) o;
        return id == other.id;
    }
}
