package model;

public interface Adjustable {

    void setLevel(int level);

    int getLevel();

    default String describe() {
        return "Adjustable: рівень " + getLevel() + "%";
    }
}
