package exception;

// Некоректні дані в рядку файлу
public class InvalidGradeException extends Exception {

    public InvalidGradeException(String message) {
        super(message);
    }

    public InvalidGradeException(String message, Throwable cause) {
        super(message, cause);
    }
}
