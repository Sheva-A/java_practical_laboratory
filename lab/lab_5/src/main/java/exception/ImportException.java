package exception;

// Помилка читання вхідного або запису вихідного файлу
public class ImportException extends Exception {

    public ImportException(String message, Throwable cause) {
        super(message, cause);
    }
}
