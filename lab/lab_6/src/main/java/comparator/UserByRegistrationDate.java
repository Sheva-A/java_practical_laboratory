package comparator;

import model.User;

import java.util.Comparator;

// Критерій сортування користувачів: дата реєстрації
public class UserByRegistrationDate implements Comparator<User> {

    @Override
    public int compare(User a, User b) {
        return a.getRegistrationDate().compareTo(b.getRegistrationDate());
    }
}
