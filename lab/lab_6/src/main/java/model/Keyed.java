package model;

// Запис, який має ключ для пошуку у сховищі
public interface Keyed<K> {

    K getKey();
}
