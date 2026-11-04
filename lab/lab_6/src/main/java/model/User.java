package model;

import java.time.LocalDate;

// Користувач стрімінгового сервісу, ключ логін. Поля final, сетерів немає
public final class User implements Keyed<String> {

    private final String login;
    private final String fullName;
    private final LocalDate registrationDate;

    public User(String login, String fullName, LocalDate registrationDate) {
        this.login = login;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
    }

    // Розбір рядка формату логін;ПІБ;дата_реєстрації
    public static User parse(String line) {
        String[] parts = line.split(";");
        return new User(parts[0], parts[1], LocalDate.parse(parts[2]));
    }

    @Override
    public String getKey() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return fullName + ", " + registrationDate;
    }
}
