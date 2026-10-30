package service;

import exception.InvalidGradeException;

public class GradeParser {

    // Передається лише текст повідомлення, причина не зберігається
    public static int parseWithoutCause(String text) throws InvalidGradeException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new InvalidGradeException(e.getMessage());
        }
    }

    // NumberFormatException передається як причина
    public static int parseWithCause(String text) throws InvalidGradeException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new InvalidGradeException("Некоректна оцінка", e);
        }
    }
}
