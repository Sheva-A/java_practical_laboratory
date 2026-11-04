package model;

import java.util.Objects;

// Тайтл стрімінгового сервісу, перевизначено equals і hashCode за ключем id
public class Title {

    private int id;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Title other = (Title) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Title{id=" + id + ", name='" + name + "', year=" + year + "}";
    }
}
